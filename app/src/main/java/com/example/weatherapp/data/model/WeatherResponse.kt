package com.example.weatherapp.data.model

data class WeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double
)

data class Wind(
    val speed: Double,
    val deg: Int
)

data class Weather(
    val description: String
)
