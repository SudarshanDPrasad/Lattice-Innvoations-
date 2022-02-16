package com.application.latticeinnovations.di

import android.content.Context
import androidx.room.Room
import com.application.latticeinnovations.model.local.NewsDao
import com.application.latticeinnovations.model.local.NewsRoomDataBase
import com.application.latticeinnovations.model.remote.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module class to to make the SingleTon Object
 */
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

    @Singleton
    @Provides
    fun ProvidesRoomDataBase(@ApplicationContext context: Context): NewsRoomDataBase {
        val builder = Room.databaseBuilder(
            context,
            NewsRoomDataBase::class.java,
            "news_db"
        )
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Singleton
    @Provides
    fun ProvidesDatatoDb(db: NewsRoomDataBase) : NewsDao {
        return db.getDao()
    }
}