package com.manugarcia010.weatherapp.ui.weekforecast

import com.manugarcia010.weatherapp.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.manugarcia010.weatherapp.databinding.ActivityWeekForecastBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class WeekForecastActivity : AppCompatActivity() {

    @Inject lateinit var viewModeFactory: ViewModelProvider.Factory
    private lateinit var viewModel: WeekForecastViewModel

    private lateinit var binding: ActivityWeekForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_week_forecast)
        binding.dailyWeatherList.layoutManager = LinearLayoutManager(this)
        //todo: add swipe to refresh
        viewModel = ViewModelProvider(this, this.viewModeFactory).get(WeekForecastViewModel::class.java)
        binding.viewmodel = viewModel
    }
}
