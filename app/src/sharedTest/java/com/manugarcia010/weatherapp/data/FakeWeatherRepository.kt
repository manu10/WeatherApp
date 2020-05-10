package com.manugarcia010.weatherapp.data

import com.manugarcia010.data.WeatherRepository
import com.manugarcia010.domain.Response
import com.manugarcia010.domain.model.*
import java.lang.Exception

class FakeWeatherRepository : WeatherRepository {
    companion object {
        fun generateFakeWeatherForecast() = WeatherForecast(
            0,
            City(1,"fake city name", Coord(0.0,0.0),"fake country",0,0),
            0,
            0,
            listOf(
                DomainDailyWeather(0,0,0, Temp(0.0,0.0,0.0,0.0,0.0,0.0),
                    FeelsLike(0.0,0.0,0.0,0.0),0,0,listOf(Weather(0,"main","description","icon")),
                    0.0,0,0)
            )
        )
    }

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }
    override suspend fun getWeatherForecast(coord: Coord): Response<WeatherForecast> {
        return if (shouldReturnError)
            Response.Error(Exception("Error getting forecast"))
        else Response.Success(generateFakeWeatherForecast())
    }

}