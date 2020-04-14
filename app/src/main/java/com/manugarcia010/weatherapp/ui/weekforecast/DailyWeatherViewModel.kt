package com.manugarcia010.weatherapp.ui.weekforecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manugarcia010.weatherapp.ui.model.DailyWeather

class DailyWeatherViewModel : ViewModel() {
    private val date = MutableLiveData<String>()
    private val weatherTitle = MutableLiveData<String>()
    private val weatherDescription = MutableLiveData<String>()
    private val weatherImageUrl = MutableLiveData<String>()

    fun bind(dailyWeather: DailyWeather) {
        date.value = dailyWeather.date.toString() //todo: show in the view
        weatherTitle.value = dailyWeather.weatherTitle
        weatherDescription.value = dailyWeather.weatherDescription
        weatherImageUrl.value = dailyWeather.imageUrl
    }
}