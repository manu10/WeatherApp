package com.manugarcia010.weatherapp.framework.datasource.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.manugarcia010.domain.model.DomainDailyWeather
import com.manugarcia010.domain.model.FeelsLike
import com.manugarcia010.domain.model.Temp
import com.manugarcia010.domain.model.Weather
import com.manugarcia010.weatherapp.framework.datasource.database.converter.WeatherEntityConverter

@Entity(tableName = "dailyweather")
data class DailyWeatherEntity (
    @PrimaryKey(autoGenerate = true)  val dbId: Long = 0,
    val dt : Int,
    val sunrise : Int,
    val sunset : Int,
    @Embedded(prefix = "temp_")
    val temp : TempEntity,
    @Embedded(prefix = "feelslike_")
    val feels_like : FeelsLikeEntity,
    val pressure : Int,
    val humidity : Int,
    @TypeConverters(WeatherEntityConverter::class)
    val weather : List<WeatherEntity>,
    val speed : Double,
    val deg : Int,
    val clouds : Int
) {
    companion object {
        fun fromDomainModel(dailyWeather: DomainDailyWeather) : DailyWeatherEntity {
            return with(dailyWeather){
                DailyWeatherEntity(
                    dt = dt,
                    sunrise = sunrise,
                    sunset = sunset,
                    temp = TempEntity.fromDomainModel(temp),
                    feels_like = FeelsLikeEntity.fromDomainModel(feels_like),
                    pressure = pressure,
                    humidity = humidity,
                    weather = weather.map { WeatherEntity.fromDomainModel(it) },
                    speed = speed,
                    deg = deg,
                    clouds = clouds
                )
            }
        }
    }
}

@Entity(tableName = "temp")
data class TempEntity (

    @PrimaryKey(autoGenerate = true)  val dbId: Long = 0,
    val day : Double,
    val min : Double,
    val max : Double,
    val night : Double,
    val eve : Double,
    val morn : Double
)  {
    companion object {
        fun fromDomainModel(temp: Temp) : TempEntity {
            return with(temp){
                TempEntity(
                    day = day,
                    min = min,
                    max = max,
                    night = night,
                    eve = eve,
                    morn = morn
                )
            }
        }
    }
}

@Entity(tableName = "feelslike")
data class FeelsLikeEntity (

    @PrimaryKey(autoGenerate = true)  val dbId: Long = 0,
    val day : Double,
    val night : Double,
    val eve : Double,
    val morn : Double
)  {
    companion object {
        fun fromDomainModel(feelsLike: FeelsLike) : FeelsLikeEntity {
            return with(feelsLike){
                FeelsLikeEntity(
                    day = day,
                    night = night,
                    eve = eve,
                    morn = morn)
            }
        }
    }
}

@Entity(tableName = "weather")
data class WeatherEntity (

    @PrimaryKey(autoGenerate = true)  val dbId: Long = 0,
    val id : Int,
    val main : String,
    val description : String,
    val icon : String
) {
    companion object {
        fun fromDomainModel(weather: Weather) : WeatherEntity {
            return with(weather){
                WeatherEntity(id = id,
                    main = main,
                    description = description,
                    icon = icon)
            }
        }
    }
}
