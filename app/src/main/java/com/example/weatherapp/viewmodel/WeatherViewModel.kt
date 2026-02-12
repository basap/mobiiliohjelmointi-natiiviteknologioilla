package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    data class WeatherUiState(
        val city: String = "",
        val weather: WeatherResponse? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )

    var uiState = androidx.compose.runtime.mutableStateOf(WeatherUiState())
        private set

    fun onCityChange(newCity: String) {
        uiState.value = uiState.value.copy(city = newCity)
    }

    fun fetchWeather() {

        if (uiState.value.city.isBlank()) {
            uiState.value = uiState.value.copy(error = "Syötä kaupungin nimi")
            return
        }

        viewModelScope.launch {
            uiState.value = uiState.value.copy(isLoading = true, error = null)

            try {
                val response = RetrofitInstance.weatherApi.getWeatherByCity(
                    city = uiState.value.city,
                    apiKey = BuildConfig.OPENWEATHER_API_KEY
                )

                uiState.value = uiState.value.copy(
                    weather = response,
                    isLoading = false
                )
            } catch (e: Exception) {
                uiState.value = uiState.value.copy(
                    error = "Sään haku epäonnistui",
                    isLoading = false
                )
            }
        }
    }
}
