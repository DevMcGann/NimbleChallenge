package com.gsoft.nimblechalenge.data.datasource.remote

import com.gsoft.nimblechalenge.util.MyResource
import retrofit2.http.Headers
import retrofit2.http.POST

interface NimbleApi {

    @POST("surveys?page[number]=1&page[size]=3")
    @Headers(
        "Content-Type : application/json",
        "Accept : application/json"
    )
    suspend fun getSurvey(): MyResource<String>

}