package com.gsoft.nimblechalenge.data.datasource.remote

import com.gsoft.nimblechalenge.data.model.LogoutRequestBody
import com.gsoft.nimblechalenge.data.model.RefreshTokenRequestBody
import com.gsoft.nimblechalenge.data.model.ResetPasswordRequestBody
import com.gsoft.nimblechalenge.data.model.ResetPasswordResponse
import com.gsoft.nimblechalenge.data.model.TokenRequestBody
import com.gsoft.nimblechalenge.data.model.TokenResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NimbleAuthApi {
    @POST("oauth/token")
    suspend fun login(@Body request: TokenRequestBody): Response<TokenResponse>

    @POST("oauth/token")
    suspend fun refreshToken(@Body requestBody: RefreshTokenRequestBody): Response<TokenResponse>


    @POST("oauth/revoke")
    suspend fun logout(@Body requestBody: LogoutRequestBody): Response<Unit>

    @POST("oauth/passwords")
    suspend fun resetPassword(@Body requestBody: ResetPasswordRequestBody): Response<ResetPasswordResponse>


}






