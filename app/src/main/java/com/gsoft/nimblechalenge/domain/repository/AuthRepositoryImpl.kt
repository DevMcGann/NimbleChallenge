package com.gsoft.nimblechalenge.domain.repository

import com.gsoft.nimblechalenge.data.datasource.remote.NimbleAuthApi
import com.gsoft.nimblechalenge.data.model.LogoutRequestBody
import com.gsoft.nimblechalenge.data.model.RefreshTokenRequestBody
import com.gsoft.nimblechalenge.data.model.ResetMeta
import com.gsoft.nimblechalenge.data.model.ResetPasswordRequestBody
import com.gsoft.nimblechalenge.data.model.ResetPasswordResponse
import com.gsoft.nimblechalenge.data.model.TokenRequestBody
import com.gsoft.nimblechalenge.data.model.TokenResponse
import com.gsoft.nimblechalenge.data.model.User
import com.gsoft.nimblechalenge.data.repository.AuthRepository
import com.gsoft.nimblechalenge.util.Constants
import com.gsoft.nimblechalenge.util.Constants.KEY_EMAIL
import com.gsoft.nimblechalenge.util.Constants.KEY_REFRESH_TOKEN
import com.gsoft.nimblechalenge.util.Constants.KEY_TOKEN
import com.gsoft.nimblechalenge.util.SharePreferencesManager
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    val authApi: NimbleAuthApi,
    val sharePreferencesManager: SharePreferencesManager
): AuthRepository {


    override suspend fun login(email: String, password: String) : TokenResponse? {
        return try {
            val tokenBody = TokenRequestBody(email = email, password = password)
            val response = authApi.login(tokenBody)
            if (response.isSuccessful) {
                val token = response.body()?.data?.attributes?.access_token
                sharePreferencesManager.setString(KEY_TOKEN, token)
                sharePreferencesManager.setString(KEY_REFRESH_TOKEN, response.body()?.data?.attributes?.refresh_token)
                sharePreferencesManager.setString(KEY_EMAIL, email)
            }

            if(response.code() == 400) {
                return TokenResponse(
                    data = null,
                    error = "Wrong Credentials!"
                )
            }else{
                response.body()
            }

        }catch (e: Exception){
            null
        }
    }

    override suspend fun refreshToken() : Boolean{
        return try {
            val refreshTokenBody = RefreshTokenRequestBody(
                grant_type = Constants.GRANT_TYPE_REFRESH_TOKEN,
                client_id = Constants.CLIENT_ID,
                client_secret = Constants.CLIENT_SECRET,
                refresh_token = sharePreferencesManager.getString(KEY_REFRESH_TOKEN, null)
            )
            val response = authApi.refreshToken(refreshTokenBody)
            if(response.isSuccessful){
                sharePreferencesManager.setString(KEY_TOKEN, response.body()?.data?.attributes?.access_token)
                sharePreferencesManager.setString(KEY_REFRESH_TOKEN, response.body()?.data?.attributes?.refresh_token)
                true
            }else{
                false
            }

        }catch (e: Exception){
            false
        }
    }

    override suspend fun isLoggedIn(): Boolean {
        return sharePreferencesManager.getString(KEY_TOKEN, null) != null
    }

    override suspend fun logout() {
        sharePreferencesManager.cleanData()
        try {
            if(sharePreferencesManager.getString(KEY_TOKEN, null) != null){
                val logoutRequestBody = LogoutRequestBody(
                    token = sharePreferencesManager.getString(KEY_TOKEN, null)!!
                )
                authApi.logout(logoutRequestBody)
            }
        }catch (e: Exception){
           return
        }

    }

    override suspend fun createUser(email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun passwordReset(email: String): ResetPasswordResponse {
        return try {
            val response: ResetPasswordResponse
            val resetBody = ResetPasswordRequestBody(
                user = User(email = email)
            )
            val result = authApi.resetPassword(resetBody).body()
            response = when (result?.meta != null){
                true -> {
                    ResetPasswordResponse(
                        meta = result?.meta,
                    )
                }

                false -> {
                    ResetPasswordResponse(
                        errors = result?.errors
                    )
                }
            }

            response

        }catch (e: Exception){
            ResetPasswordResponse(
                meta = null,
                errors = null
            )
        }
    }
}