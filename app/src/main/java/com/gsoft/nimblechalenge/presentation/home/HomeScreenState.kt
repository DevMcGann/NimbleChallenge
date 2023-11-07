package com.gsoft.nimblechalenge.presentation.home

data class HomeScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
)
