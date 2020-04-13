package com.manugarcia010.weatherapp.framework.di

import com.manugarcia010.weatherapp.ui.WeekForecastActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [WeatherModule::class])
    abstract fun bindWeekForecastActivity(): WeekForecastActivity  // Add bindings for other sub-components here

}