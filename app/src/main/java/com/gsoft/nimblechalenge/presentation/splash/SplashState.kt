package com.gsoft.nimblechalenge.presentation.splash

import com.gsoft.nimblechalenge.data.model.TokenResponse

data class SplashState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isError : Boolean = false,
    val isAuth: Boolean = false,
    val goToLogin : Boolean = false,
    val goToHome : Boolean = false,
    val goToNoConn : Boolean = false
)
