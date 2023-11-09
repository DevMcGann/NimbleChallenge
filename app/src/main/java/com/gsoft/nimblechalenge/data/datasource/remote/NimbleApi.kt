package com.gsoft.nimblechalenge.data.datasource.remote

import com.gsoft.nimblechalenge.data.model.SurveyResponse
import com.gsoft.nimblechalenge.util.MyResource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface NimbleApi {

    @GET("surveys")
    suspend fun getSurvey(
        @Query("page[number]") pageNumber: Int = 1,
        @Query("page[size]") pageSize: Int = 3
    ): Response<SurveyResponse>

}