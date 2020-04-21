package com.manugarcia010.weatherapp.framework.service

import com.manugarcia010.domain.model.WeatherForecast
import com.manugarcia010.weatherapp.BuildConfig
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDataAccessService {

    @GET("forecast/daily")
    suspend fun fetchWeatherForecast(@Query("lat") lat: Double,
                                     @Query("lon") lon: Double,
                                     @Query("cnt") cnt: Int = DAYS_IN_WEEK,
                                     @Query("appid") appid: String = BuildConfig.API_KEY
    ): WeatherForecast

    companion object {
        const val DAYS_IN_WEEK: Int = 7
    }
}