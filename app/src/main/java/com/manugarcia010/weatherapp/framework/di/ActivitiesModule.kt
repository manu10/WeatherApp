package com.manugarcia010.weatherapp.framework.di

import com.manugarcia010.weatherapp.framework.di.viewmodel.WeekForecastViewModelModule
import com.manugarcia010.weatherapp.ui.weekforecast.WeekForecastActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [WeatherModule::class, WeekForecastViewModelModule::class])
    abstract fun bindWeekForecastActivity(): WeekForecastActivity  // Add bindings for other sub-components here

}