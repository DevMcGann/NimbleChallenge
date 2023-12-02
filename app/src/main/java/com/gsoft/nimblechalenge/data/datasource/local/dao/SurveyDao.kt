package com.gsoft.nimblechalenge.data.datasource.local.dao

import androidx.room.Dao
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
        if (getById(survey.id) != null) {
            update(survey)
        } else {
            insert(survey)
        }
    }

    @Transaction
    fun upsert(list: List<SurveyAttributesDB>) {
        for (survey in list) {
            upsert(survey)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(survey: SurveyAttributesDB)

    @Update
    fun update(survey: SurveyAttributesDB)

    @Query("SELECT * FROM survey WHERE Id = :id LIMIT 1")
    fun getById(id: String): SurveyAttributesDB?

    @Query("DELETE FROM survey")
    fun clearSurveys()

    @Query("""
        SELECT * FROM survey
        """)
    fun getSurveys(): List<SurveyAttributesDB>

}