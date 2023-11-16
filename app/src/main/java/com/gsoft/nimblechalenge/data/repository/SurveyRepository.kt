package com.gsoft.nimblechalenge.data.repository

import com.gsoft.nimblechalenge.data.datasource.local.entity.SurveyAttributesDB
import com.gsoft.nimblechalenge.data.model.SurveyAttributes
import com.gsoft.nimblechalenge.data.model.SurveyResponse
import com.gsoft.nimblechalenge.domain.model.Survey
import com.gsoft.nimblechalenge.util.MyResource
import retrofit2.Response

interface SurveyRepository {
    suspend fun getSurveysFromDao(): List<SurveyAttributesDB?>

    suspend fun getSurveys(page:Int): MyResource<List<Survey?>>

    suspend fun saveSurvey(surveyAttributes: SurveyAttributes, id:String)

}