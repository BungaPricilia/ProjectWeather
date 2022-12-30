package com.example.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.base.BaseViewModel
import com.example.weather.data.weather.model.HourlyForecastResponse
import com.example.weather.data.weather.model.SevenDayForecastResponse
import com.example.weather.domain.util.Resource
import com.example.weather.domain.weather.WeatherUseCase
import com.example.weather.util.collectResult
import io.reactivex.disposables.CompositeDisposable

class WeatherViewModel(
    private val useCase: WeatherUseCase,
    disposable: CompositeDisposable
): BaseViewModel(disposable) {

    private val _listHourlyForecast: MutableLiveData<Resource<List<HourlyForecastResponse>>> = MutableLiveData()
    val listHourlyForecast: LiveData<Resource<List<HourlyForecastResponse>>> get() = _listHourlyForecast

    private val _listSevenDayForecast: MutableLiveData<Resource<List<SevenDayForecastResponse>>> = MutableLiveData()
    val listSevenDayForecast: LiveData<Resource<List<SevenDayForecastResponse>>> get() = _listSevenDayForecast

    fun getHourlyForecast(apiKey: String, token: String, cityName: String) {
        _listHourlyForecast.value = Resource.Loading()
        viewModelScope.collectResult(_listHourlyForecast) {
            useCase.getHourlyForecast(apiKey, token, cityName)
        }
    }

    fun getSevenDayForecast(apiKey: String, token: String, cityName: String) {
        _listSevenDayForecast.value = Resource.Loading()
        viewModelScope.collectResult(_listSevenDayForecast) {
            useCase.getSevenDayForecast(apiKey, token, cityName)
        }
    }
}