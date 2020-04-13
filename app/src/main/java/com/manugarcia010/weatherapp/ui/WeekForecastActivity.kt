package com.manugarcia010.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manugarcia010.domain.model.Coord
import com.manugarcia010.usecases.GetWeatherForecastByCoord
import com.manugarcia010.weatherapp.R
import com.manugarcia010.weatherapp.databinding.ActivityWeekForecastBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class WeekForecastActivity : AppCompatActivity() {

    //This is just to test the DI is setup correctly
    @Inject lateinit var  getWeatherForecastByCoord: GetWeatherForecastByCoord

    private lateinit var binding: ActivityWeekForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_week_forecast)
    }
}
