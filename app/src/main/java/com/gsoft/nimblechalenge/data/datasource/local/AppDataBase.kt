package com.gsoft.nimblechalenge.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gsoft.nimblechalenge.data.datasource.local.dao.SurveyDao
import com.gsoft.nimblechalenge.data.datasource.local.entity.SurveyAttributesDB


@Database(entities = [
    SurveyAttributesDB::class],
    version = 2, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun surveyDao(): SurveyDao
}