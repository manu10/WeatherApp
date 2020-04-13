package com.manugarcia010.data

import com.manugarcia010.domain.Response
import com.manugarcia010.domain.model.Coord
import com.manugarcia010.domain.model.WeatherForecast
import io.reactivex.Single

class WeatherRepository (private val weatherRemoteDataSource: WeatherRemoteDataSource) {
    fun getWeatherForecast(coord: Coord): Single<Response<WeatherForecast>> =
        weatherRemoteDataSource.getWeatherForecast(coord)

}

interface WeatherRemoteDataSource {
    fun getWeatherForecast(coord: Coord): Single<Response<WeatherForecast>>
}
