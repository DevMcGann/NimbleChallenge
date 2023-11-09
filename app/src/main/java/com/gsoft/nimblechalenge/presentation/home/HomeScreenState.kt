package com.gsoft.nimblechalenge.presentation.home

import com.gsoft.nimblechalenge.data.model.SurveyResponse

data class HomeScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val surveyData : SurveyResponse? = null
)
