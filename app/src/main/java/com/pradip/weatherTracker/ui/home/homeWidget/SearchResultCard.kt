package com.pradip.weatherTracker.ui.home.homeWidget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pradip.domain.models.ConditionDataModel
import com.pradip.domain.models.CurrentDataModel
import com.pradip.domain.models.LocationDataModel
import com.pradip.domain.models.WeatherDataModel
import com.pradip.weatherTracker.R
import com.pradip.weatherTracker.ui.theme.SearchBarColor
import com.pradip.weatherTracker.ui.theme.TextColorBlack
import com.pradip.weatherTracker.ui.theme.Typography


@Composable
fun SearchResultCard(
    city: WeatherDataModel,
    onCitySelect: (WeatherDataModel) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(contentColor = SearchBarColor, containerColor = SearchBarColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = { onCitySelect(city) }
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // City and Temperature
            Column {
                Text(
                    text = city.location.name,
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = Typography.wtSemiBoldTitle,
                    color = TextColorBlack,

                )
                Text(
                    text = "${city.current.tempDegreeC}°",
                    fontSize = 60.sp,
                    lineHeight = 90.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = Typography.wtSemiBoldTitle,
                    color = TextColorBlack,
                )
            }
            Spacer(modifier = Modifier.width(12.dp))

            AsyncImage(
                modifier = Modifier
                    .size(84.dp)
                    .aspectRatio(1f),
                model = city.current.condition.icon,
                placeholder = painterResource(R.drawable.ic_place_holder),
                contentDescription = null,
            )
        }
    }
}
@PreviewLightDark
@Composable
fun SearchResultCardPreview(){
    SearchResultCard(
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
        onCitySelect = {}
    )
}