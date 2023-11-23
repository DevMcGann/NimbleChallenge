package com.gsoft.nimblechalenge.domain.repository

import com.gsoft.nimblechalenge.data.datasource.local.dao.SurveyDao
import com.gsoft.nimblechalenge.data.datasource.local.entity.SurveyAttributesDB
import com.gsoft.nimblechalenge.data.datasource.remote.NimbleApi
import com.gsoft.nimblechalenge.data.mapper.DataMapper
import com.gsoft.nimblechalenge.data.model.SurveyAttributes
import com.gsoft.nimblechalenge.data.model.SurveyResponse
import com.gsoft.nimblechalenge.data.repository.SurveyRepository
import com.gsoft.nimblechalenge.domain.model.Survey
import com.gsoft.nimblechalenge.util.MyResource
import com.gsoft.nimblechalenge.util.NetworkUtils
import com.gsoft.nimblechalenge.util.Resource
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class SurveyRepositoryImpl @Inject constructor(
    @Named("api") private val api: NimbleApi,
    private val surveyDao: SurveyDao,
    private val networkUtils: NetworkUtils
): SurveyRepository {

    override suspend fun getSurveysFromDao(): List<SurveyAttributesDB?> {
        return surveyDao.getSurveys()
    }

    override suspend fun getSurveys(page: Int): Resource<List<Survey?>> {
       return  try {
            if (networkUtils.isNetworkConnected()){
                getSurveysFromApi(page)
            }else{
                getSurveysOffline()
            }
        } catch (e: Exception) {
            Resource.error(message = e.message.toString(), data = null)
        }
    }

    private suspend fun getSurveysFromApi(page:Int):Resource<List<Survey?>>{
        return  try {
            val response = api.getSurvey(page)
            if (response.isSuccessful) {
                if(response.body() != null) {
                    response.body()?.data?.map {
                        val id = it.id
                        val surveyToDb = DataMapper.ApiToDb(it.attributes, id)
                        surveyDao.upsert(surveyToDb!!)
                    }
                }
                val surveys = DataMapper.DbToDomain(getSurveysFromDao())
                Resource.success(data = surveys)
            } else {
                Resource.error(message = "Request failed", data = null)
            }

        } catch (e: Exception) {
            Resource.error(message = e.message.toString(), data = null)
        }
    }

    private suspend fun getSurveysOffline(): Resource<List<Survey?>> {
        return try {
            val surveys = getSurveysFromDao()
            Resource.success(DataMapper.DbToDomain(surveys))
        } catch (e: Exception) {
            Resource.error(message = e.message.toString(), data = null)
        }
    }

    override suspend fun saveSurvey(surveyAttributes: SurveyAttributes, id:String) {
        DataMapper.ApiToDb(surveyAttributes, id)?.let { surveyDao.upsert(it) }
    }

}