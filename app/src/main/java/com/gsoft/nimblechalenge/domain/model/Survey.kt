package com.gsoft.nimblechalenge.domain.model

data class Survey(
    val id: String,
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
