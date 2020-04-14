package com.manugarcia010.weatherapp.ui.model

import com.manugarcia010.domain.model.DomainDailyWeather
import org.joda.time.DateTime

data class DailyWeather(
    val date: DateTime,
    val weatherTitle: String,
    val weatherDescription: String,
    val imageUrl: String)

fun DomainDailyWeather.toPresentationDailyWeather() =
    DailyWeather(DateTime(dt*1000L), weather.first().main, weather.first().description, "example" ) //fixme imageUrl