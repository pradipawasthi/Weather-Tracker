package com.pradip.domain.models

data class WeatherDataModel(
    val location: LocationDataModel,
    val current: CurrentDataModel,
)

data class LocationDataModel(
    val name: String,
    val country: String
)

data class CurrentDataModel(
    val condition: ConditionDataModel,
    val humidity: Int,
    val uv: Float,
    val feelsLikeDegreeC: Float,
    val tempDegreeC: Float,

    )
data class ConditionDataModel(
    val text: String,
    val icon: String
)