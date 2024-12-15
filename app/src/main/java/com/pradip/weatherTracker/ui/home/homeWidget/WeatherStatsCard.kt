package com.pradip.weatherTracker.ui.home.homeWidget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pradip.domain.models.WeatherDataModel
import com.pradip.weatherTracker.ui.theme.SearchBarColor
import com.pradip.weatherTracker.ui.theme.SearchTextColor
import com.pradip.weatherTracker.ui.theme.Typography
import com.pradip.weatherTracker.ui.theme.ValueColor


@Composable
fun WeatherStatsCard(city: WeatherDataModel) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = SearchBarColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp)
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherStatItem("Humidity", "${city.current.humidity}%")
            WeatherStatItem("UV", city.current.uv.toString())
            WeatherStatItem("Feels Like", "${city.current.feelsLikeDegreeC}°")
        }
    }
}

@Composable
fun WeatherStatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            fontSize = if (label.equals("Feels Like")) 8.sp else 12.sp,
            color = SearchTextColor,
            style = if (label.equals("Feels Like")) Typography.wtFeelsLikeTitle else Typography.wtStatsTitle,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = ValueColor,
            style = Typography.wtBoldLabel
        )
    }
}
