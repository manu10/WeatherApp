package com.manugarcia010.weatherapp.ui.weekforecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import javax.inject.Inject

class WeekForecastActivity : AppCompatActivity() {

    @Inject lateinit var viewModeFactory: ViewModelProvider.Factory
    private lateinit var viewModel: WeekForecastViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        viewModel = ViewModelProvider(this, this.viewModeFactory).get(WeekForecastViewModel::class.java)
    }
}
