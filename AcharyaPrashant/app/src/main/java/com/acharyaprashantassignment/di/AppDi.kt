package com.acharyaprashantassignment.di

import android.content.Context
import com.acharyaprashantassignment.features.data.ApiService
import com.acharyaprashantassignment.features.data.HttpClientFactory
import com.acharyaprashantassignment.features.data.NetworkFactory
import com.acharyaprashantassignment.features.data.NetworkFactoryImpl
import com.acharyaprashantassignment.features.data.RetrofitFactory
import com.acharyaprashantassignment.features.data.repos.APIImpl
import com.acharyaprashantassignment.features.domain.repos.ApiListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppDi {

    @Singleton
    @Provides
    fun providesOkHttpClientFactory(
        @ApplicationContext context: Context
    ): HttpClientFactory{
        return HttpClientFactory(context)
    }

    @Singleton
    @Provides
    fun providesRetrofitFactory(httpClientFactory: HttpClientFactory): RetrofitFactory {
        return RetrofitFactory(httpClientFactory)
    }

    @Singleton
    @Provides
    fun providesNetworkFactory(retrofitFactory: RetrofitFactory): NetworkFactory {
        return NetworkFactoryImpl(retrofitFactory)
    }

    @Singleton
    @Provides
    fun providesApiService(networkFactory: NetworkFactory):ApiService {
        return networkFactory.getApiService(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesRepo(apiService: ApiService): ApiListener {
        return APIImpl(apiService)
    }
}