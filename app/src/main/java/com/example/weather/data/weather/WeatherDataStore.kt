package com.example.weather.data.weather

import com.example.weather.data.util.call
import com.example.weather.data.util.mapToDomain
import com.example.weather.data.weather.model.HourlyForecastResponse
import com.example.weather.data.weather.model.SevenDayForecastResponse
import com.example.weather.data.weather.remote.WeatherApiClient
import com.example.weather.domain.util.Resource
import com.example.weather.util.addBearerToken
import com.example.weather.util.toQuery
import kotlinx.coroutines.flow.Flow

class WeatherDataStore(private val webService: WeatherApiClient): WeatherRepository {

    override suspend fun getHourlyForecast(apiKey: String, token: String, cityName: String): Flow<Resource<List<HourlyForecastResponse>>> {
        return webService.getHourlyForeCast(apiKey, token.addBearerToken(), cityName.toQuery()).call().mapToDomain { it }
    }

    override suspend fun getSevenDayForecast(
        apiKey: String,
        token: String,
        cityName: String
    ): Flow<Resource<List<SevenDayForecastResponse>>> {
        return webService.getSevenDayForecast(apiKey, token.addBearerToken(), cityName.toQuery()).call().mapToDomain { it }
    }
}