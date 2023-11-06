package com.gsoft.nimblechalenge.presentation.login

import com.gsoft.nimblechalenge.data.model.TokenResponse

data class LoginScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val message: String = "",
    val isAuth: Boolean = false,
    val loginData : TokenResponse? = null
)

