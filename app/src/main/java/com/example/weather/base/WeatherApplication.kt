package com.example.weather.base

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.weather.di.networkModule
import com.example.weather.di.repositoryModule
import com.example.weather.di.useCaseModule
import com.example.weather.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.NONE

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(NONE)
            androidContext(this@WeatherApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}