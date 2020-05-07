package com.manugarcia010.weatherapp.ui.weekforecast

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manugarcia010.domain.Response
import com.manugarcia010.domain.model.Coord
import com.manugarcia010.domain.model.DomainDailyWeather
import com.manugarcia010.domain.model.WeatherForecast
import com.manugarcia010.usecases.GetWeatherForecastByCoord
import com.manugarcia010.weatherapp.ui.model.DailyWeather
import com.manugarcia010.weatherapp.ui.model.toPresentationDailyWeather
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeekForecastViewModel @Inject constructor (private val getWeatherForecastByCoord: GetWeatherForecastByCoord) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val dailyWeatherClickSubject = MutableLiveData<DomainDailyWeather>() //todo: use it to implement on item clicked
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : MutableLiveData<String> = _errorMessage
    private val _items = MutableLiveData<List<DailyWeather>>().apply { value = emptyList() }
    val items: MutableLiveData<List<DailyWeather>> = _items

    fun onRefresh() {
        loadWeatherData()
    }

    fun loadWeatherData() = viewModelScope.launch {
        showLoading()
        val weekForecast =  getWeatherForecastByCoord(Coord(-34.604122, -58.384281))
        hideProgress()
        processWeatherDataResponse(weekForecast)
    }

    private fun processWeatherDataResponse(response: Response<WeatherForecast>?) {
        when (response) {
            is Response.Success -> onWeatherDataSuccess(response)
            is Response.Error -> onWeatherDataFailure(response)
        }
    }

    private fun showLoading() {
        _dataLoading.value = true
    }

    private fun hideProgress() {
        _dataLoading.value = false
    }

    private fun onWeatherDataSuccess(response: Response.Success<WeatherForecast>) {
        _items.value = response.data.list.map { it.toPresentationDailyWeather() }
    }

    private fun onWeatherDataFailure(response: Response.Error) {
        //todo: Improve error handling
        errorMessage.value = response.exception.message
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}