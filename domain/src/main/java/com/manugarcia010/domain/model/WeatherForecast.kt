package com.manugarcia010.domain.model

data class WeatherForecast (

    val city : City,
    val cod : Int,
    val cnt : Int,
    val list : List<DailyWeather>
)