package com.gsoft.nimblechalenge.domain.repository

import com.gsoft.nimblechalenge.data.datasource.remote.NimbleAuthApi
import com.gsoft.nimblechalenge.data.model.LogoutRequestBody
import com.gsoft.nimblechalenge.data.model.RefreshTokenRequestBody
import com.gsoft.nimblechalenge.data.model.TokenRequestBody
import com.gsoft.nimblechalenge.data.model.TokenResponse
import com.gsoft.nimblechalenge.data.repository.AuthRepository
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
            response.body()
        }catch (e: Exception){
            null
        }
    }

    override suspend fun refreshToken(token: String) {
        sharePreferencesManager.getString(KEY_REFRESH_TOKEN, null)
            ?.let {
                val tokenBody = RefreshTokenRequestBody(
                    refresh_token = it
                )
                authApi.refreshToken(tokenBody)
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

    override suspend fun passwordReset(email: String): Boolean {
        TODO("Not yet implemented")
    }
}