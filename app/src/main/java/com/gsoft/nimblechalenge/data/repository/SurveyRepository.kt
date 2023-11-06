package com.gsoft.nimblechalenge.data.repository

import com.gsoft.nimblechalenge.util.MyResource

interface SurveyRepository {
    suspend fun getSurvey(): MyResource<String>
}