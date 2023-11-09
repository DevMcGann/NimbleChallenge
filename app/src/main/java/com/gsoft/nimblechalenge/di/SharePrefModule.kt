package com.gsoft.nimblechalenge.di

import android.app.Application
import com.gsoft.nimblechalenge.util.SharePreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class SharePrefModule {

    @Provides
    @Singleton
    fun provideSharePreference(application: Application): SharePreferencesManager {
        return SharePreferencesManager(application)
    }

}