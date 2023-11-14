package com.gsoft.nimblechalenge.domain.repository

import com.gsoft.nimblechalenge.util.LocalOrRemoteDataAccess
import com.gsoft.nimblechalenge.data.DataMapper
import com.gsoft.nimblechalenge.data.datasource.local.dao.SurveyDao
import com.gsoft.nimblechalenge.data.datasource.local.entity.SurveyAttributesDB
import com.gsoft.nimblechalenge.data.datasource.remote.NimbleApi
import com.gsoft.nimblechalenge.data.model.SurveyAttributes
import com.gsoft.nimblechalenge.data.model.SurveyResponse
import com.gsoft.nimblechalenge.data.repository.SurveyRepository
import com.gsoft.nimblechalenge.domain.model.SurveyDomain
import com.gsoft.nimblechalenge.util.MyResource
import com.gsoft.nimblechalenge.util.NetworkUtils
import javax.inject.Inject
import javax.inject.Named

class SurveyRepositoryImpl @Inject constructor(
    @Named("api") private val api: NimbleApi,
    private val dao: SurveyDao,
    private val networkUtils: NetworkUtils
): SurveyRepository {
    override suspend fun getSurveyList(): MyResource<SurveyResponse?> {
        return try {
            val response = api.getSurvey()
            if (response.isSuccessful) {
                val id = response.body()?.data?.first()?.id
                response.body()?.data?.map {
                    it.copy(attributes = it.attributes.copy(id = id!!))
                }
                MyResource.Success(response.body())
            } else {
                MyResource.Failure(Exception("Request failed with code ${response.code()}"))
            }
        } catch (e: Exception) {
            MyResource.Failure(e)
        }
    }

    override suspend fun getSurveys( page : Int) :  MyResource<List<SurveyDomain>?> {
       return  LocalOrRemoteDataAccess(
            fetchFromLocal = { dao.getSurveys()?.map{ DataMapper.DbtoDomain(it) } },
            fetchFromRemote = { api.getSurvey() },
            saveRemoteData = { surveyResponse ->
                surveyResponse.data?.map {
                    val surveyAttributes = SurveyAttributes(
                        id = it.attributes.id,
                        title = it.attributes.title,
                        description = it.attributes.description,
                        thank_email_above_threshold = it.attributes.thank_email_above_threshold,
                        thank_email_below_threshold = it.attributes.thank_email_below_threshold,
                        is_active = it.attributes.is_active,
                        cover_image_url = it.attributes.cover_image_url,
                        created_at = it.attributes.created_at,
                        active_at = it.attributes.active_at,
                        inactive_at = it.attributes.inactive_at,
                        survey_type = it.attributes.survey_type
                    )
                    //save
                    saveSurvey(DataMapper.ApiToDb(surveyAttributes))
                }
                             },
            shouldFetchFromRemote = true//networkUtils.isNetworkConnected()
        )
    }

    override fun saveSurvey(survey: SurveyAttributesDB) {
        dao.insert(survey)
    }

}