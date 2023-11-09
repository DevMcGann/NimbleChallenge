package com.gsoft.nimblechalenge.domain.repository

import com.gsoft.nimblechalenge.data.datasource.remote.NimbleApi
import com.gsoft.nimblechalenge.data.model.SurveyResponse
import com.gsoft.nimblechalenge.data.repository.SurveyRepository
import com.gsoft.nimblechalenge.util.MyResource
import javax.inject.Inject
import javax.inject.Named

class SurveyRepositoryImpl @Inject constructor(
    @Named("api") private val api: NimbleApi
): SurveyRepository {
    override suspend fun getSurveyList(): MyResource<SurveyResponse?> {
        return try {
            val response = api.getSurvey()
            if (response.isSuccessful) {
                MyResource.Success(response.body())
            } else {
                MyResource.Failure(Exception("Request failed with code ${response.code()}"))
            }
        } catch (e: Exception) {
            MyResource.Failure(e)
        }
    }

}