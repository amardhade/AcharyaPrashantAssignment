package com.acharyaprashantassignment.features.data

import android.content.Context
import android.os.Build
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.time.Duration

class HttpClientFactory(
    private val context: Context
) {
    fun getHttpClient(): OkHttpClient {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Android-8
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .connectTimeout(Duration.ofSeconds(30))
                .readTimeout(Duration.ofSeconds(30))
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build()
        }


    }

}