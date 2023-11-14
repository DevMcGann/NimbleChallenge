package com.gsoft.nimblechalenge.presentation.home

import com.gsoft.nimblechalenge.data.model.SurveyResponse
import com.gsoft.nimblechalenge.domain.model.SurveyDomain

data class HomeScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val surveyData : SurveyResponse? = null,
    val surveys : List<SurveyDomain>? = null,
    val page : Int = 1,
    val isRefreshing : Boolean = false
)
