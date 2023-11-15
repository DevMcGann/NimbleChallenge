package com.gsoft.nimblechalenge.di


import com.gsoft.nimblechalenge.data.datasource.local.dao.SurveyDao
import com.gsoft.nimblechalenge.data.datasource.remote.NimbleApi
import com.gsoft.nimblechalenge.data.datasource.remote.NimbleAuthApi
import com.gsoft.nimblechalenge.data.repository.AuthRepository
import com.gsoft.nimblechalenge.data.repository.SurveyRepository
import com.gsoft.nimblechalenge.domain.repository.AuthRepositoryImpl
import com.gsoft.nimblechalenge.domain.repository.SurveyRepositoryImpl
import com.gsoft.nimblechalenge.util.SharePreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    @Named("api")
    fun provideApiRepository(
        @Named("api")api: NimbleApi,
        surveyDao: SurveyDao
    ): SurveyRepository {
        return SurveyRepositoryImpl(
            api = api,
            surveyDao = surveyDao
        )
    }


    @Provides
    @Singleton
    @Named("auth")
    fun provideAuthRepository(
        @Named("auth")api: NimbleAuthApi,
        sharedPreference : SharePreferencesManager
    ): AuthRepository {
        return AuthRepositoryImpl(
            authApi = api,
            sharePreferencesManager = sharedPreference
        )
    }


}