package com.gsoft.nimblechalenge.data.datasource.remote

import android.util.Log
import com.gsoft.nimblechalenge.util.Constants.KEY_SHARED_PREFERENCE
import com.gsoft.nimblechalenge.util.SharePreferencesManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
        private val prefs: SharePreferencesManager
) : Interceptor {

    private lateinit var token: String

    private lateinit var response : Response

    override fun intercept(chain: Interceptor.Chain): Response {
        token = prefs.getString(KEY_SHARED_PREFERENCE, "")!!
        Log.d("TOKEN", token)

        var request = chain.request()

        if(request.header("Authorization") == null && token.isNotEmpty()){
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        }

        response = chain.proceed(request)

        return response
    }
}