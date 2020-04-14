package com.manugarcia010.weatherapp.ui.weekforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.manugarcia010.weatherapp.R
import com.manugarcia010.weatherapp.databinding.DailyWeatherItemBinding
import com.manugarcia010.weatherapp.ui.model.DailyWeather
import io.reactivex.subjects.PublishSubject
import kotlin.properties.Delegates

class WeatherForecastAdapter : RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder>() {

    var dailyWeatherList: List<DailyWeather> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    val dailyWeatherClickSubject: PublishSubject<DailyWeather> = PublishSubject.create<DailyWeather>() // todo

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: DailyWeatherItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.daily_weather_item, parent, false
        )
        /*todo:
           RxView.clicks(binding.root)
            .takeUntil(RxView.detaches(parent))
            .map { binding }
            .subscribe { dailyWeatherClickSubject.onNext(binding.dailyWeather) }*/
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount() = dailyWeatherList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dailyWeatherList[position])
    }

    class ViewHolder(private val binding: DailyWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val viewModel = DailyWeatherViewModel()

        fun bind(dailyWeather: DailyWeather) {
            viewModel.bind(dailyWeather)
            binding.dailyWeather = dailyWeather
            binding.dailyWeatherViewModel = viewModel
        }
    }
}
