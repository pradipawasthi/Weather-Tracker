package com.pradip.data.remote.schema

import com.google.gson.annotations.SerializedName
import com.pradip.domain.models.ConditionDataModel
import com.pradip.domain.models.CurrentDataModel
import com.pradip.domain.models.LocationDataModel
import com.pradip.domain.models.WeatherDataModel

data class WeatherDataSchema(
    @SerializedName("location")
    val location: LocationDataSchema,
    @SerializedName("current")
    val current: CurrentDataSchema, )

data class LocationDataSchema(
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String
)

data class CurrentDataSchema(
    @SerializedName("condition")
    val condition: ConditionDataSchema,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("uv")
    val uv: Float,
    @SerializedName("feelslike_c")
    val feelsLikeDegreeC: Float,
    @SerializedName("temp_c")
    val tempDegreeC: Float,

    )
data class ConditionDataSchema(
    @SerializedName("text")
    val text: String,
    @SerializedName("icon")
    val icon: String
)
internal fun LocationDataSchema.toDomain(): LocationDataModel =
    LocationDataModel(name = name, country = country)

internal fun CurrentDataSchema.toDomain(): CurrentDataModel =
    CurrentDataModel(condition = condition.toDomain(), humidity = humidity, uv = uv, feelsLikeDegreeC = feelsLikeDegreeC, tempDegreeC = tempDegreeC)


internal fun ConditionDataSchema.toDomain(): ConditionDataModel =
    ConditionDataModel(text = text, icon = "https:$icon")


internal fun WeatherDataSchema.toDomain(): WeatherDataModel =
    WeatherDataModel(location = location.toDomain(), current = current.toDomain())