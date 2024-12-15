package com.pradip.weatherTracker.ui.home

import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pradip.domain.models.WeatherDataModel
import com.pradip.weatherTracker.ui.home.homeWidget.NoCitySelectedView
import com.pradip.weatherTracker.ui.home.homeWidget.SearchResultCard
import com.pradip.weatherTracker.ui.home.homeWidget.WeatherDetailsSection
import com.pradip.weatherTracker.ui.home.homeWidget.WeatherSearchBar


@Composable
fun HomeScreen() {
    val keyboardController = LocalSoftwareKeyboardController.current

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
            Spacer(modifier = Modifier.height(16.dp))
            WeatherSearchBar(
                searchText = { searchQuery

                },
                onSearchTextChanged = { newText ->
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
                            if (selectedCity != null) {
                                keyboardController?.hide()
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
                                errorBody = "Please Search for a City",
                                modifier = Modifier
                            )
                        }

                    }
                    is WeatherUiState.Error -> {
                        val errorMessage = (weatherState as WeatherUiState.Error).message
                        NoCitySelectedView(
                            errorMessage = errorMessage,
                            errorBody = "",
                            modifier = Modifier
                        )
                    }
                    else-> {
                        NoCitySelectedView(
                            errorMessage = "No City Selected",
                            errorBody = "Please Search for a City",
                            modifier = Modifier
                        )
                    }
                }


            }
        }
    }
}



@Composable
@PreviewLightDark
fun WeatherAppPreview() {
    HomeScreen()
}