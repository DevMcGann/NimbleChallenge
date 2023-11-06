package com.gsoft.nimblechalenge.domain.repository

import com.gsoft.nimblechalenge.data.datasource.remote.NimbleApi
import com.gsoft.nimblechalenge.data.repository.SurveyRepository
import com.gsoft.nimblechalenge.util.MyResource
import javax.inject.Inject
import javax.inject.Named

class SurveyRepositoryImpl @Inject constructor(
    @Named("api") val api: NimbleApi
): SurveyRepository {
    override suspend fun getSurvey(): MyResource<String> {
        TODO("Not yet implemented")
    }

}