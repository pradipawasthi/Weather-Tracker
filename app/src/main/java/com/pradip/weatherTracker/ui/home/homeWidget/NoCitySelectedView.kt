package com.pradip.weatherTracker.ui.home.homeWidget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pradip.weatherTracker.ui.theme.TextColorBlack
import com.pradip.weatherTracker.ui.theme.Typography

@Composable
fun NoCitySelectedView(errorMessage : String, errorBody: String, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
        ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = errorMessage,
                fontWeight = FontWeight.Bold,
                color = TextColorBlack,
                fontSize = 30.sp,
                style = Typography.wtMedium,

            )

            Spacer(modifier = Modifier.height(16.dp))
            if (errorBody.isNotBlank()) {
                Text(
                    text = errorBody,
                    fontSize = 15.sp,
                    color = TextColorBlack,
                    style = Typography.wtBoldLabel,
                )
            }
        }
    }
}