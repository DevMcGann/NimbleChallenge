package com.gsoft.nimblechalenge.presentation.home

import com.gsoft.nimblechalenge.data.model.SurveyResponse
import com.gsoft.nimblechalenge.domain.model.Survey

data class HomeScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val surveyData : SurveyResponse? = null,
    val surveys : List<Survey?> = emptyList(),
    val currentPage : Int = 1,
    val isPullingToRefresh : Boolean = false
)
