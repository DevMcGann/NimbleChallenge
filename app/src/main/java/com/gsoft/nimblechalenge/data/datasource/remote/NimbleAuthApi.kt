package com.gsoft.nimblechalenge.data.datasource.remote

import com.gsoft.nimblechalenge.data.model.LogoutRequestBody
import com.gsoft.nimblechalenge.data.model.RefreshTokenRequestBody
import com.gsoft.nimblechalenge.data.model.TokenRequestBody
import com.gsoft.nimblechalenge.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NimbleAuthApi {


    @POST("auth/token")
    fun login(@Body request: TokenRequestBody): Response<TokenResponse>


    @POST("auth/token")
    fun refreshToken(@Body requestBody: RefreshTokenRequestBody): Response<TokenResponse>


    @POST("auth/revoke")
    fun logout(@Body requestBody: LogoutRequestBody): Response<Unit>





}






