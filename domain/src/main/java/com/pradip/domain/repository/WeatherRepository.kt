package com.pradip.domain.repository

import com.pradip.domain.Resource
import com.pradip.domain.models.WeatherDataModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeather(location: String): Flow<Resource<WeatherDataModel?>>

}