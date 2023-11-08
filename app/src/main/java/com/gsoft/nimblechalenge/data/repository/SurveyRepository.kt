package com.gsoft.nimblechalenge.data.repository

import com.gsoft.nimblechalenge.data.model.SurveyResponse
import com.gsoft.nimblechalenge.util.MyResource

interface SurveyRepository {
    suspend fun getSurveyList(): MyResource<SurveyResponse?>

}