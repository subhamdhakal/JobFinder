package com.example.jobfinder.di

import android.app.Application
import android.content.Context
import com.example.jobfinder.api.ApiServiceProvider
import com.example.jobfinder.utils.AppPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideAppPreferences(application: Application): AppPreferences {
        return AppPreferences(application)
    }
//
//
    @Provides
    @Singleton
    fun provideServiceProvider(appPreferences: AppPreferences): ApiServiceProvider {
        return ApiServiceProvider(appPreferences)
    }
//
//    @Provides
//    @Singleton
//    fun provideDatabase(application: Application): DayDatabase {
//        val database: DayDatabase = Room
//            .databaseBuilder(
//                application.applicationContext,
//                DayDatabase::class.java, "day_database"
//            )
//            .allowMainThreadQueries()
//            .fallbackToDestructiveMigration()
//            .build()
//        return database
//    }
//
//    @Provides
//    @Singleton
//    fun provideDayDao(database: DayDatabase) = database.toDoDao
}