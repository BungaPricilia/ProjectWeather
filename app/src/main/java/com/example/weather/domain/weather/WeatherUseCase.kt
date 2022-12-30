package com.example.weather.domain.weather

import com.example.weather.data.weather.model.HourlyForecastResponse
import com.example.weather.data.weather.model.SevenDayForecastResponse
import com.example.weather.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherUseCase {
    suspend fun getHourlyForecast(apiKey: String, token: String, cityName: String): Flow<Resource<List<HourlyForecastResponse>>>
    suspend fun getSevenDayForecast(apiKey: String, token: String, cityName: String): Flow<Resource<List<SevenDayForecastResponse>>>
}