package com.manugarcia010.weatherapp.framework

import com.manugarcia010.data.WeatherRemoteDataSource
import com.manugarcia010.domain.Response
import com.manugarcia010.domain.model.Coord
import com.manugarcia010.domain.model.WeatherForecast
import com.manugarcia010.weatherapp.framework.service.WeatherDataAccessService
import io.reactivex.Single

class WeatherRemoteDataSourceImpl (private val weatherService: WeatherDataAccessService) : WeatherRemoteDataSource {
    override fun getWeatherForecast(coord: Coord): Single<Response<WeatherForecast>> {
      return weatherService.fetchWeatherForecast(coord.lat,coord.lon)
            .map { Response.Success(it) as Response<WeatherForecast> }
            .onErrorReturn { Response.Error(it) }
    }
}