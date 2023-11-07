package com.gsoft.nimblechalenge.di

import com.gsoft.nimblechalenge.data.datasource.remote.NimbleAuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthClientDependencies {

    companion object {
        private const val apiUrl =  "https://survey-api.nimblehq.co/api/v1/"
    }


    @Provides
    @Singleton
    @Named("auth")
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }



    @Singleton
    @Provides
    @Named("auth")
    fun provideRetrofit(@Named("auth")okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(apiUrl)
        .client(okHttpClient)
        .build()



    @Singleton
    @Provides
    @Named("auth")
    fun provideAuthService(@Named("auth")retrofit: Retrofit): NimbleAuthApi = retrofit.create(NimbleAuthApi::class.java)


}