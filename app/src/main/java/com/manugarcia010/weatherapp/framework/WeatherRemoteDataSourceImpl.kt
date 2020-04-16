package com.manugarcia010.weatherapp.framework

import com.manugarcia010.data.WeatherRemoteDataSource
import com.manugarcia010.domain.Response
import com.manugarcia010.domain.model.Coord
import com.manugarcia010.domain.model.WeatherForecast
import com.manugarcia010.weatherapp.framework.service.WeatherDataAccessService
import io.reactivex.Single

class WeatherRemoteDataSourceImpl (private val weatherService: WeatherDataAccessService) : WeatherRemoteDataSource {
    override suspend fun getWeatherForecast(coord: Coord): Response<WeatherForecast> {
        return try {
            Response.Success(weatherService.fetchWeatherForecast(coord.lat,coord.lon))
        } catch (e: Exception){
            Response.Error(e)
        }
    }
}