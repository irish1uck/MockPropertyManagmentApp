package com.example.mockpropertymanagmentapp.data.network

import android.content.SharedPreferences
import android.widget.ShareActionProvider
import com.example.mockpropertymanagmentapp.app.Config
import com.example.mockpropertymanagmentapp.helpers.SessionManager.Companion.TOKEN
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object ApiMiddleman {
    private const val REQUEST_TIMEOUT: Long = 20
    private var okHttpClient: OkHttpClient? = null
    val client: OkHttpClient
        get() {
            if (okHttpClient == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                var httpBuilder = OkHttpClient.Builder()
                httpBuilder.connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .addInterceptor(object : Interceptor {
                        override fun intercept(chain: Interceptor.Chain): Response {
                            val original = chain!!.request()
                            val requestBuilder = original.newBuilder()
                                .addHeader(
                                    "Authorization",
                                    "Bearer $TOKEN"
                                )
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Accept", "application/json")
                            val request = requestBuilder.build()
                            return chain.proceed(request)
                        }
                    })
                okHttpClient = httpBuilder.build()
            }
            return okHttpClient!!
        }
}