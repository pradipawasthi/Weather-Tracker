package com.pradip.weatherTracker.ui.home.homeWidget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pradip.domain.models.ConditionDataModel
import com.pradip.domain.models.CurrentDataModel
import com.pradip.domain.models.LocationDataModel
import com.pradip.domain.models.WeatherDataModel
import com.pradip.weatherTracker.R
import com.pradip.weatherTracker.ui.theme.SearchTextColor
import com.pradip.weatherTracker.ui.theme.TextColorBlack
import com.pradip.weatherTracker.ui.theme.Typography


@Composable
fun WeatherDetailsSection(city: WeatherDataModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().background(Color.White),
    ) {

        AsyncImage(
            modifier = Modifier
                .size(124.dp)
                .aspectRatio(1f),
            model = city.current.condition.icon,
            placeholder = painterResource(R.drawable.ic_place_holder),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = city.location.name,
                fontSize = 30.sp,
                style = Typography.wtSemiBoldTitle,
                color = TextColorBlack,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_vector),
                contentDescription = null,
                modifier = Modifier.size(21.dp),
            )
        }


        Text(
            text = "${city.current.tempDegreeC}°",
            fontSize = 70.sp,
            lineHeight = 105.sp,
            fontWeight = FontWeight.Bold,
            style = Typography.wtTitle1,
            color = TextColorBlack,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Weather Stats
        WeatherStatsCard(city)
    }
}
@PreviewLightDark
@Composable
fun WeatherDetailsSectionPreview(){
    WeatherDetailsSection(
        city =   WeatherDataModel(
            location = LocationDataModel(
                name = "Mumbai",
                country = "India"
            ),
            current = CurrentDataModel(
                condition = ConditionDataModel("Sunny", "cdn.weatherapi.com/weather/64x64/day/113.png"),
                humidity = 23,
                uv = 1.4f,
                feelsLikeDegreeC = 1.2f,
                tempDegreeC = 5.6f
            )

        ),
    )
}