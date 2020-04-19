package com.manugarcia010.weatherapp.framework.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.manugarcia010.weatherapp.framework.datasource.database.converter.DailyWeatherEntityConverter
import com.manugarcia010.weatherapp.framework.datasource.database.converter.WeatherEntityConverter
import com.manugarcia010.weatherapp.framework.datasource.database.entity.WeatherForecastEntity

@Database(entities = [WeatherForecastEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    DailyWeatherEntityConverter::class,
    WeatherEntityConverter::class
)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun getWeatherForecastDao(): WeatherForecastDao
}
