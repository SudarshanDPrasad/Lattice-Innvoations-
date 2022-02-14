package com.application.latticeinnovations.di

import com.application.latticeinnovations.model.remote.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Singleton
    @Provides
    fun ProvidesApiService(): ApiClient {
        val builder = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return builder.create(ApiClient::class.java)
    }
}