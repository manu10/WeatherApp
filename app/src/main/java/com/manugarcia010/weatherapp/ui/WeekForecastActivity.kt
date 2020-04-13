package com.manugarcia010.weatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.manugarcia010.weatherapp.R
import com.manugarcia010.weatherapp.databinding.ActivityWeekForecastBinding

class WeekForecastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeekForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week_forecast)
    }
}
