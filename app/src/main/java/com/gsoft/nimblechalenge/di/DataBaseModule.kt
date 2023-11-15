package com.gsoft.nimblechalenge.di

import android.app.Application
import androidx.room.Room
import com.gsoft.nimblechalenge.data.datasource.local.AppDataBase
import com.gsoft.nimblechalenge.data.datasource.local.dao.SurveyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDataBase(application: Application): AppDataBase {
        return Room.databaseBuilder(application, AppDataBase::class.java, "NimbleApp")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideSurveyDao(appDataBase: AppDataBase): SurveyDao {
        return appDataBase.surveyDao()
    }


}