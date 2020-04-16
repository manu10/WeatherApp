package com.manugarcia010.data

import com.manugarcia010.domain.Response
import com.manugarcia010.domain.model.Coord
import com.manugarcia010.domain.model.WeatherForecast

class WeatherRepository (private val weatherRemoteDataSource: WeatherRemoteDataSource) {
    suspend fun getWeatherForecast(coord: Coord): Response<WeatherForecast> =
        weatherRemoteDataSource.getWeatherForecast(coord)

}

interface WeatherRemoteDataSource {
    suspend fun getWeatherForecast(coord: Coord): Response<WeatherForecast>
}
