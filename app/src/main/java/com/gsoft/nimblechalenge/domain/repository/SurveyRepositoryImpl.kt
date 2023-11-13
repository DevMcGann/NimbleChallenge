package com.gsoft.nimblechalenge.domain.repository

import com.gsoft.epicchallenge.util.LocalOrRemoteDataAccess
import com.gsoft.nimblechalenge.data.DataMapper
import com.gsoft.nimblechalenge.data.datasource.local.dao.SurveyDao
import com.gsoft.nimblechalenge.data.datasource.remote.NimbleApi
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

    suspend fun getSurveys() :  MyResource<SurveyDomain?> {
       return  LocalOrRemoteDataAccess(
            fetchFromLocal = { dao.getSurveys()?.map{ DataMapper.DbtoDomain(it) } },
            fetchFromRemote = { api.getSurvey() },
            saveRemoteData = { it.data?.map { DataMapper.ApiToDb(it.attributes) } },
            shouldFetchFromRemote = { networkUtils.isNetworkConnected() }
        )
    }

}