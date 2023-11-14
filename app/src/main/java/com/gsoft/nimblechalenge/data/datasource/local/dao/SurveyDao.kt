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
     fun upsert(survey: SurveyAttributesDB) {
        if (getSurveys() != null) {
            update(survey = survey)
        } else {
            insert(survey = survey)
        }
    }

    @Transaction
     fun upsert(list: List<SurveyAttributesDB>) {
        for (survey in list) {
            upsert(survey)
        }
    }

    @Delete
     fun delete(survey: SurveyAttributesDB)

    @Query("DELETE FROM surveyAttributes")
     fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(survey: SurveyAttributesDB)

    @Update
    fun update(survey: SurveyAttributesDB)

    @Query("SELECT * FROM surveyAttributes")
     fun getSurveys(): List<SurveyAttributesDB>?
}