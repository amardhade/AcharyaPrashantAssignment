package com.acharyaprashantassignment.features.data

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory(
    private val httpClientFactory: HttpClientFactory
) {

    init {
        getRetrofit()
    }

    fun getRetrofit(): Retrofit {
        return synchronized(NetworkFactory::class.java) {
            Retrofit.Builder().baseUrl("https://acharyaprashant.org/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(httpClientFactory.getHttpClient()).build()
        }

    }
}