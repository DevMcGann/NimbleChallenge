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
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class SurveyRepositoryImpl @Inject constructor(
    @Named("api") private val api: NimbleApi,
    private val surveyDao: SurveyDao
): SurveyRepository {

    override suspend fun getSurveysFromApi(page:Int) : Response<SurveyResponse> {
        return api.getSurvey(page)
    }

    override suspend fun getSurveysFromDao(): List<SurveyAttributesDB?> {
        return surveyDao.getSurveys()
    }

    override suspend fun getSurveys(): MyResource<List<Survey?>> {
       return  try {
            val response = api.getSurvey()
            if (response.isSuccessful) {
                if(response.body() != null) {
                    response.body()?.data?.map {
                        val id = it.id
                        val surveyToDb = DataMapper.ApiToDb(it.attributes, id)
                        surveyDao.upsert(surveyToDb!!)
                    }
                }
                val surveys = DataMapper.DbToDomain(getSurveysFromDao())
                MyResource.Success(surveys)
            } else {
                MyResource.Failure(Exception("Request failed with code ${response.code()}"))
            }

        } catch (e: Exception) {
            MyResource.Failure(e)
        }
    }

    override suspend fun saveSurvey(surveyAttributes: SurveyAttributes, id:String) {
        DataMapper.ApiToDb(surveyAttributes, id)?.let { surveyDao.upsert(it) }
    }

}