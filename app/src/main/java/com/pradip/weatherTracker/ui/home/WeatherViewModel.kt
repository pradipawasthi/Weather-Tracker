package com.pradip.weatherTracker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradip.data.local.LocalPreferences
import com.pradip.domain.Resource
import com.pradip.domain.models.WeatherDataModel
import com.pradip.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val localPreferences: LocalPreferences
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherUiState?>(null)
    val weatherState: StateFlow<WeatherUiState?> = _weatherState

    private val _query = MutableStateFlow<String>("")
    val query = _query.asStateFlow()

    fun setQuery(value: String) {
        _query.value = value
    }
    init {
    viewModelScope.launch {
        _query.emitAll(localPreferences.readCity.filterNotNull())
    }

    }

    @OptIn(FlowPreview::class)
    fun fetchWeather() {
        viewModelScope.launch {
            _query
                .debounce(if (!_query.value.isNullOrBlank()) 300 else 0)
                .distinctUntilChanged()
                .collect { location ->
                    if (location?.isNotBlank()== true) {
                        weatherRepository.getWeather(location).collect { resource ->
                            when (resource) {
                                is Resource.Loading -> {
                                    _weatherState.value = WeatherUiState.Loading
                                }
                                is Resource.Success -> {
                                    _weatherState.value = WeatherUiState.Success(resource.data)
                                   if (resource.data?.location?.name != null){
                                       localPreferences.saveCity(resource.data!!.location.name)
                                   }
                                }
                                is Resource.Error -> {
                                    _weatherState.value =
                                        WeatherUiState.Error(resource.message ?: "Unknown Error")
                                }
                            }
                        }
                    }
                }
        }
    }

}

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(val weather: WeatherDataModel?) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}