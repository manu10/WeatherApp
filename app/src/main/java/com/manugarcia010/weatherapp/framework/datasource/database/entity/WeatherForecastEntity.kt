package com.manugarcia010.weatherapp.framework.datasource.database.entity

import androidx.room.*
import com.manugarcia010.domain.model.City
import com.manugarcia010.domain.model.Coord
import com.manugarcia010.domain.model.WeatherForecast
import com.manugarcia010.weatherapp.framework.datasource.database.converter.DailyWeatherEntityConverter

@Entity(tableName = "weather_forecast")
data class WeatherForecastEntity (

    @PrimaryKey(autoGenerate = true)  val dbId: Long = 0,
    @ColumnInfo(name = "created_at")
    var createdAt: Long = 0,
    @ColumnInfo(name = "modified_at")
    var modifiedAt: Long = 0,
    @Embedded(prefix = "city_")
    val city : CityEntity,
    val cod : Int,
    val cnt : Int,
    @TypeConverters(DailyWeatherEntityConverter::class)
    val list : List<DailyWeatherEntity>
) {
    companion object {
        fun fromDomainModel(weatherForecast: WeatherForecast) : WeatherForecastEntity {
            return with(weatherForecast){
                WeatherForecastEntity(
                    city = CityEntity.fromDomainModel(city),
                    cod = cod,
                    cnt = cnt,
                    list = list.map { DailyWeatherEntity.fromDomainModel(it) })
            }
        }
    }
}

@Entity(tableName = "city")
data class CityEntity (
    @PrimaryKey(autoGenerate = true)  val dbId: Long = 0,
    val id : Int,
    val name : String,
    @Embedded(prefix = "coord_")
    val coord : CoordEntity,
    val country : String,
    val population : Int,
    val timezone : Int
) {
    companion object {
        fun fromDomainModel(city: City) : CityEntity {
            return with(city){
                CityEntity(
                    id = id,
                    name = name,
                    coord = CoordEntity.fromDomainModel(coord),
                    country = country,
                    population = population,
                    timezone = timezone)
            }
        }
    }
}

@Entity(tableName = "coord")
class CoordEntity (
    @PrimaryKey(autoGenerate = true)  val dbId: Long = 0,
    val lat : Double,
    val lon : Double
) {
    companion object {
        fun fromDomainModel(coord: Coord) : CoordEntity {
            return with(coord){
                CoordEntity(lat = lat, lon = lon)
            }
        }
    }
}
