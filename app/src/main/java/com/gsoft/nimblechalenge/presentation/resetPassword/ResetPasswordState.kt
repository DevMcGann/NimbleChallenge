package com.gsoft.nimblechalenge.presentation.resetPassword

data class ResetPasswordState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isError : Boolean = false,
    val showNotification : Boolean = false,
    val goToLogin : Boolean = false
)
