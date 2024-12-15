package com.pradip.weatherTracker.di

import android.content.Context
import com.pradip.data.local.LocalPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideLocalPreferences(
        @ApplicationContext context: Context
    ): LocalPreferences {
        return LocalPreferences(context)
    }
}