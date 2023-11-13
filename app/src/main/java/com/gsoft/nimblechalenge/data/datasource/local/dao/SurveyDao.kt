package com.gsoft.nimblechalenge.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.gsoft.nimblechalenge.data.datasource.local.entity.SurveyAttributesDB


@Dao
interface SurveyDao {
    @Transaction
    suspend fun upsert(survey: SurveyAttributesDB) {
        if (getSurveys() != null) {
            update(survey = survey)
        } else {
            insert(survey = survey)
        }
    }

    @Transaction
    suspend fun upsert(list: List<SurveyAttributesDB>) {
        for (survey in list) {
            upsert(survey)
        }
    }

    @Delete
    suspend fun delete(survey: SurveyAttributesDB)

    @Query("DELETE FROM surveyAttributes")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(survey: SurveyAttributesDB)

    @Update
    fun update(survey: SurveyAttributesDB)

    @Query("SELECT * FROM surveyAttributes")
    suspend fun getSurveys(): List<SurveyAttributesDB>?
}