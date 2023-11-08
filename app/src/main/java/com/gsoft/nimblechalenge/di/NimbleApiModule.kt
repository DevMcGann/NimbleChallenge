package com.gsoft.nimblechalenge.di

import com.gsoft.nimblechalenge.data.datasource.remote.NimbleApi
import com.gsoft.nimblechalenge.data.datasource.remote.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NimbleApiModule {
    companion object {
        private const val apiUrl =  "https://survey-api.nimblehq.co/api/v1/"
    }

    @Provides
    @Singleton
    @Named("api")
    fun provideOkHttpClient(
        tokenInterceptor: TokenInterceptor,
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(tokenInterceptor)
        return okHttpClientBuilder.build()
    }


    @Provides
    @Singleton
    @Named("api")
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }



    @Singleton
    @Provides
    @Named("api")
    fun provideRetrofit( @Named("api") okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(apiUrl)
        .client(okHttpClient)
        .build()


    @Singleton
    @Provides
    @Named("api")
    fun provideApiService(@Named("api") retrofit: Retrofit): NimbleApi = retrofit.create(NimbleApi::class.java)


}