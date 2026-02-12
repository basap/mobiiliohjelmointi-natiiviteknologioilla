package com.example.weatherapp.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.weatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherResultSection(
    state: WeatherViewModel.WeatherUiState
) {
    Text(text = "Kaupunki: ${state.weather?.name}")
    Text(text = "Lämpötila: ${state.weather?.main?.temp} °C")
    Text(text = "Tuntuu kuin: ${state.weather?.main?.feels_like} °C")
    Text(text = "Sää: ${state.weather?.weather?.firstOrNull()?.description}")
    Text(text = "\nYlin lämpötila tänään: ${state.weather?.main?.temp_max} °C")
    Text(text = "Alin lämpötila tänään: ${state.weather?.main?.temp_min} °C")
    Text(text = "\nTuulen nopeus: ${state.weather?.wind?.speed} m/s")
    Text(text = "Tuulen suunta: ${state.weather?.wind?.deg}°")
}
