package com.pradip.data.remote.repository


import com.pradip.data.remote.schema.toDomain
import com.pradip.data.remote.service.WeatherService
import com.pradip.data.util.network.backendRequestFlow
import com.pradip.domain.Resource
import com.pradip.domain.models.WeatherDataModel
import com.pradip.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(private val weatherService: WeatherService) : WeatherRepository {


        override suspend fun getWeather(location: String): Flow<Resource<WeatherDataModel>> =
            backendRequestFlow {
                weatherService.getWeather(
                    location = location, apiKey = API_SECRET_KEY
                ).body()!!.toDomain()
            }

}