package com.example.weather.domain.weather

import com.example.weather.data.weather.WeatherRepository
import com.example.weather.data.weather.model.HourlyForecastResponse
import com.example.weather.data.weather.model.SevenDayForecastResponse
import com.example.weather.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class WeatherInteractor(private val repository: WeatherRepository): WeatherUseCase {

    override suspend fun getHourlyForecast(apiKey: String, token: String, cityName: String): Flow<Resource<List<HourlyForecastResponse>>> {
        return repository.getHourlyForecast(apiKey, token, cityName)
    }

    override suspend fun getSevenDayForecast(
        apiKey: String,
        token: String,
        cityName: String
    ): Flow<Resource<List<SevenDayForecastResponse>>> {
        return repository.getSevenDayForecast(apiKey, token, cityName)
    }
}