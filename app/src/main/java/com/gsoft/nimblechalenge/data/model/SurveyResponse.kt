package com.gsoft.nimblechalenge.data.model

data class SurveyResponse(
    val data: List<SurveyItem>? = null,
    val meta: Meta? = null
)

data class SurveyItem(
    val id: String,
    val type: String,
    val attributes: SurveyAttributes
)

data class SurveyAttributes(
    val title: String,
    val description: String,
    val thank_email_above_threshold: String?,
    val thank_email_below_threshold: String?,
    val is_active: Boolean,
    val cover_image_url: String,
    val created_at: String,
    val active_at: String,
    val inactive_at: String?,
    val survey_type: String
)

data class Meta(
    val page: Int,
    val pages: Int,
    val page_size: Int,
    val records: Int
)

