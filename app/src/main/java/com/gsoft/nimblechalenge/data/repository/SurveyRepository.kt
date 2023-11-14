package com.gsoft.nimblechalenge.data.repository

import com.gsoft.nimblechalenge.data.datasource.local.entity.SurveyAttributesDB
import com.gsoft.nimblechalenge.data.model.SurveyResponse
import com.gsoft.nimblechalenge.domain.model.SurveyDomain
import com.gsoft.nimblechalenge.util.MyResource

interface SurveyRepository {
    suspend fun getSurveyList(): MyResource<SurveyResponse?>
    suspend fun getSurveys(page : Int) :  MyResource<List<SurveyDomain>?>
    fun saveSurvey(survey: SurveyAttributesDB)

}