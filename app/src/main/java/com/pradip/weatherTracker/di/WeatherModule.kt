package com.pradip.weatherTracker.di

import com.pradip.data.remote.repository.WeatherRepositoryImpl
import com.pradip.data.remote.service.WeatherService
import com.pradip.data.util.BASE_API_URL
import com.pradip.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    @Singleton
    fun provideWeatherService(retrofitBuilder: Retrofit.Builder): WeatherService =
        retrofitBuilder.baseUrl(BASE_API_URL).build().create(WeatherService::class.java)

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherService: WeatherService): WeatherRepository =
        WeatherRepositoryImpl(weatherService)
}