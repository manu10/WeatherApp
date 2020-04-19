package com.manugarcia010.domain.model

data class WeatherForecast (

    var lastModified : Long,
    val city : City,
    val cod : Int,
    val cnt : Int,
    val list : List<DomainDailyWeather>
)