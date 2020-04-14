package com.manugarcia010.weatherapp.ui.weekforecast

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manugarcia010.domain.Response
import com.manugarcia010.domain.model.Coord
import com.manugarcia010.domain.model.DomainDailyWeather
import com.manugarcia010.domain.model.WeatherForecast
import com.manugarcia010.usecases.GetWeatherForecastByCoord
import com.manugarcia010.weatherapp.ui.model.toPresentationDailyWeather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeekForecastViewModel @Inject constructor (private val getWeatherForecastByCoord: GetWeatherForecastByCoord) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val dailyWeatherClickSubject = MutableLiveData<DomainDailyWeather>() //todo: use it to implement on item clicked
    val loadingVisibility = MutableLiveData<Int>()
    val errorMessage = MutableLiveData<String>()
    val weatherListAdapter = WeatherForecastAdapter()

    init {
        loadWeatherData()
    }

    fun loadWeatherData() {
        getWeatherForecastByCoord(Coord(-34.604122, -58.384281))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { hideProgress() }
            .subscribe ({processWeatherDataResponse(it)},{})
            .addTo(compositeDisposable)
        }

    private fun processWeatherDataResponse(response: Response<WeatherForecast>?) {
        when (response) {
            is Response.Success -> onWeatherDataSuccess(response)
            is Response.Error -> onWeatherDataFailure(response)
        }
    }

    private fun showLoading() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun hideProgress() {
        loadingVisibility.value = View.GONE
    }

    private fun onWeatherDataSuccess(response: Response.Success<WeatherForecast>) {
        weatherListAdapter.dailyWeatherList = response.data.list.map { it.toPresentationDailyWeather() }
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