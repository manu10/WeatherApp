package com.manugarcia010.data

import com.manugarcia010.domain.Response
import com.manugarcia010.domain.model.Coord
import com.manugarcia010.domain.model.WeatherForecast

interface WeatherRepository {
    suspend fun getWeatherForecast(coord: Coord): Response<WeatherForecast>

    companion object {
        const val THREE_HOURS_MILLIS = 10800000
    }
}