package com.pradip.data.remote.service

import com.pradip.data.remote.schema.WeatherDataSchema
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("v1/current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String?,
    ): Response<WeatherDataSchema>


}