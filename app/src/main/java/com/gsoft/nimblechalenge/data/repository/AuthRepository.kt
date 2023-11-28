package com.gsoft.nimblechalenge.data.repository


import com.gsoft.nimblechalenge.data.model.ResetPasswordResponse
import com.gsoft.nimblechalenge.data.model.TokenResponse

interface AuthRepository  {
    suspend fun login(email: String, password: String) : TokenResponse?
    suspend fun refreshToken() : Boolean
    suspend fun isLoggedIn(): Boolean
    suspend fun logout()
    suspend fun createUser(email: String, password: String) : Boolean
    suspend fun passwordReset(email: String) : ResetPasswordResponse
}