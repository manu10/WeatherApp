package com.manugarcia010.data

import com.manugarcia010.data.WeatherRepository.Companion.THREE_HOURS_MILLIS
import com.manugarcia010.domain.Response
import com.manugarcia010.domain.model.*

class DefaultWeatherRepository (private val weatherPersistenceDataSource: WeatherPersistenceDataSource,
                                private val weatherRemoteDataSource: WeatherRemoteDataSource) :
    WeatherRepository {
    override suspend fun getWeatherForecast(coord: Coord): Response<WeatherForecast> {
        var response = weatherPersistenceDataSource.getWeatherForecast(coord)
        if (response is Response.Success){
            if (response.data.isFreshData())
                return response
            else
                response = response.copy(freshData = false)
        }
        val remoteResponse = weatherRemoteDataSource.getWeatherForecast(coord)
        if (remoteResponse is Response.Success){
            weatherPersistenceDataSource.saveWeather(remoteResponse.data)
            return remoteResponse
        }
        return response
    }

}

private fun WeatherForecast.isFreshData() =
    lastModified > System.currentTimeMillis() - THREE_HOURS_MILLIS


interface WeatherRemoteDataSource {
    suspend fun getWeatherForecast(coord: Coord): Response<WeatherForecast>
}

interface WeatherPersistenceDataSource {
    suspend fun getWeatherForecast(coord: Coord): Response<WeatherForecast>
    suspend fun saveWeather(weatherForecast: WeatherForecast)
}