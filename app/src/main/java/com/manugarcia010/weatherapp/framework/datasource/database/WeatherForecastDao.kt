package com.manugarcia010.weatherapp.framework.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manugarcia010.data.WeatherRepository.Companion.THREE_HOURS_MILLIS
import com.manugarcia010.weatherapp.framework.datasource.database.entity.WeatherForecastEntity

@Dao
interface WeatherForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(weatherForecastEntity: WeatherForecastEntity)

    suspend fun saveWithTimestamp(weatherForecastEntity: WeatherForecastEntity) {
        save(weatherForecastEntity.apply{
            createdAt = System.currentTimeMillis()
            modifiedAt = System.currentTimeMillis()
        })
    }
    @Query("SELECT * FROM weather_forecast WHERE abs(abs(city_coord_lat) - abs(:lat)) < 0.5 AND abs(abs(city_coord_lon) - abs(:lon)) < 0.5 ORDER BY created_at DESC LIMIT 1")
    suspend fun loadWeatherForecast(lat: Double, lon: Double): WeatherForecastEntity

}