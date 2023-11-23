package com.gsoft.nimblechalenge.data.mapper

import com.gsoft.nimblechalenge.data.datasource.local.entity.SurveyAttributesDB
import com.gsoft.nimblechalenge.data.model.SurveyAttributes
import com.gsoft.nimblechalenge.domain.model.Survey

object DataMapper {
    fun ApiToDb(surveyAttributes: SurveyAttributes?, id:String) =
        surveyAttributes?.let {
            SurveyAttributesDB(
                id = id,
                title = it.title,
                description = surveyAttributes.description,
                thank_email_above_threshold = surveyAttributes.thank_email_above_threshold,
                thank_email_below_threshold = surveyAttributes.thank_email_below_threshold,
                is_active = surveyAttributes.is_active,
                cover_image_url = surveyAttributes.cover_image_url,
                created_at = surveyAttributes.created_at,
                active_at = surveyAttributes.active_at,
                inactive_at = surveyAttributes.inactive_at,
                survey_type = surveyAttributes.survey_type
            )
        }


    fun DbToDomain(surveyAttributesDB: SurveyAttributesDB?) =
        surveyAttributesDB?.let {
            Survey(
                id = it.id,
                title = surveyAttributesDB.title,
                description = surveyAttributesDB.description,
                thank_email_above_threshold = surveyAttributesDB.thank_email_above_threshold,
                thank_email_below_threshold = surveyAttributesDB.thank_email_below_threshold,
                is_active = surveyAttributesDB.is_active,
                cover_image_url = surveyAttributesDB.cover_image_url,
                created_at = surveyAttributesDB.created_at,
                active_at = surveyAttributesDB.active_at,
                inactive_at = surveyAttributesDB.inactive_at,
                survey_type = surveyAttributesDB.survey_type
            )
        }

    fun ApiToDb(list: List<SurveyAttributes>, id:String) =
        list.map { DataMapper.ApiToDb(it, id) }

    fun DbToDomain(list: List<SurveyAttributesDB?>) =
        list.map { DbToDomain(it) }
}