package com.gsoft.nimblechalenge.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "survey")
data class SurveyAttributesDB(
    @PrimaryKey
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

