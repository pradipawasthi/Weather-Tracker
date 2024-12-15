package com.pradip.weatherTracker.ui.home

import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pradip.domain.models.WeatherDataModel
import com.pradip.weatherTracker.ui.home.homeWidget.SearchResultCard
import com.pradip.weatherTracker.ui.home.homeWidget.WeatherDetailsSection
import com.pradip.weatherTracker.ui.home.homeWidget.WeatherSearchBar
import com.pradip.weatherTracker.ui.theme.TextColorBlack
import com.pradip.weatherTracker.ui.theme.Typography
import com.pradip.weatherTracker.ui.theme.SearchTextColor


@Composable
fun HomeScreen() {
    val viewModel: WeatherViewModel = hiltViewModel()
    val weatherState by viewModel.weatherState.collectAsState()

    val searchQuery by viewModel.query.collectAsStateWithLifecycle()
    var selectedCity by remember { mutableStateOf<WeatherDataModel?>(null) }
    LaunchedEffect(Unit) {
        viewModel.fetchWeather()
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Search Bar with Top Margin
            Spacer(modifier = Modifier.height(16.dp))
            WeatherSearchBar(
                searchText = { searchQuery

                },
                onSearchTextChanged = { newText ->
//                        searchResult = performSearch(newText.text)
                    viewModel.setQuery(newText)
                    if (selectedCity!= null) selectedCity = null
                },
                onClear = {
                    viewModel.setQuery("")
                    if (selectedCity!= null) selectedCity = null
                },
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                when (weatherState) {
                    is WeatherUiState.Loading -> {
                        Box(modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    is WeatherUiState.Success -> {
                        if (searchQuery.isNotBlank()) {
                            val weather = (weatherState as WeatherUiState.Success).weather
                            Spacer(modifier = Modifier.height(16.dp))
                            // Search Result or Selected City
                            if (selectedCity != null) {
                                WeatherDetailsSection(selectedCity!!)
                            } else if (weather != null) {
                                SearchResultCard(
                                    city = weather,
                                    onCitySelect = { city ->
                                        selectedCity = city
                                    }

                                )
                            }
                        }
                        else {
                            NoCitySelectedView(
                                errorMessage = "No City Selected",
                                modifier = Modifier
                            )
                        }

                    }
                    is WeatherUiState.Error -> {
                        val errorMessage = (weatherState as WeatherUiState.Error).message
                        NoCitySelectedView(
                            errorMessage = errorMessage,
                            modifier = Modifier
                        )
                    }
                    else-> {
                        NoCitySelectedView(
                            errorMessage = "No City Selected",
                            modifier = Modifier
                        )
                    }
                }


            }
        }
    }
}



@Composable
fun NoCitySelectedView(errorMessage : String, modifier: Modifier = Modifier) {
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
                text = errorMessage,//"No City Selected",
                fontWeight = FontWeight.Bold,
                color = TextColorBlack,
                fontSize = 30.sp,
                style = Typography.wtMedium,

            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Please Search for a City",
                fontSize = 15.sp,
                color = TextColorBlack,
                style = Typography.wtBoldLabel,
            )
        }
    }
}

// Preview function
@Composable
@PreviewLightDark
fun WeatherAppPreview() {
    HomeScreen()
}