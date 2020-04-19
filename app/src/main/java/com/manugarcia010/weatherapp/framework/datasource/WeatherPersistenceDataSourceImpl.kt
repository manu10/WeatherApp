package com.manugarcia010.weatherapp.framework.datasource

import android.util.Log
import com.manugarcia010.data.WeatherPersistenceDataSource
import com.manugarcia010.domain.Response
import com.manugarcia010.domain.model.*
import com.manugarcia010.weatherapp.framework.datasource.database.WeatherForecastDao
import com.manugarcia010.weatherapp.framework.datasource.database.entity.*

class WeatherPersistenceDataSourceImpl (private val weatherForecastDao: WeatherForecastDao) :
    WeatherPersistenceDataSource {

    override suspend fun getWeatherForecast(coord: Coord): Response<WeatherForecast> {
        return try{
            Response.Success(weatherForecastDao.loadWeatherForecast(coord.lat,coord.lon).toWeatherForecastModel())
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun saveWeather(weatherForecast: WeatherForecast) {
        weatherForecastDao.saveWithTimestamp(WeatherForecastEntity.fromDomainModel(weatherForecast))
    }
}

private fun WeatherForecastEntity.toWeatherForecastModel() : WeatherForecast{
    return WeatherForecast(
        createdAt,
        city.toDomainCity(),
        cod,
        cnt,
        list.map { it.toDomainDailyWeather() }
    )
}

private fun DailyWeatherEntity.toDomainDailyWeather() =
    DomainDailyWeather(
        dt,
        sunrise,
        sunset,
        temp.toDomainTemp(),
        feels_like.toDomainFeelsLike(),
        pressure,
        humidity,
        weather.map { it.toDomainWeather() },
        speed,
        deg,
        clouds
    )

private fun FeelsLikeEntity.toDomainFeelsLike() = FeelsLike(day, night, eve, morn)

private fun TempEntity.toDomainTemp() = Temp(day, min, max, night, eve, morn)

private fun CityEntity.toDomainCity() =
    City(
        id,
        name,
        coord.toDomainCoord(),
        country,
        population,
        timezone
    )

private fun CoordEntity.toDomainCoord() = Coord(lat, lon)

private fun WeatherEntity.toDomainWeather() =
    Weather(
        id,
        main,
        description,
        icon
    )
