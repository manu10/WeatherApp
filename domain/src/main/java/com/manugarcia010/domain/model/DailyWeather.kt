package com.manugarcia010.domain.model

data class DailyWeather (

    val dt : Int,
    val sunrise : Int,
    val sunset : Int,
    val temp : Temp,
    val feels_like : FeelsLike,
    val pressure : Int,
    val humidity : Int,
    val weather : List<Weather>,
    val speed : Double,
    val deg : Int,
    val clouds : Int
)