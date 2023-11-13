package com.gsoft.nimblechalenge.data

import com.gsoft.nimblechalenge.data.datasource.local.entity.SurveyAttributesDB
import com.gsoft.nimblechalenge.data.model.SurveyAttributes
import com.gsoft.nimblechalenge.domain.model.SurveyDomain

object DataMapper {

    fun ApiToDb(surveyAttributes: SurveyAttributes) = SurveyAttributesDB(
        id = surveyAttributes.id,
        title = surveyAttributes.title,
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

    fun DbtoDomain(surveyAttributesDB: SurveyAttributesDB) = SurveyDomain(
        id = surveyAttributesDB.id,
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

    fun listApitoDb(surveyAttributes: List<SurveyAttributes>) = surveyAttributes.map {
        ApiToDb(it)
    }

    fun listDbtoDomain(surveyAttributesDB: List<SurveyAttributesDB>) = surveyAttributesDB.map {
        DbtoDomain(it)
    }



}