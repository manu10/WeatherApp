package com.manugarcia010.weatherapp.ui.weekforecast

import androidx.lifecycle.ViewModel
import com.manugarcia010.usecases.GetWeatherForecastByCoord
import javax.inject.Inject

class WeekForecastViewModel @Inject constructor (private val getWeatherForecastByCoord: GetWeatherForecastByCoord) : ViewModel() {

}