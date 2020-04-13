package com.manugarcia010.weatherapp.framework.di.viewmodel

import androidx.lifecycle.ViewModel
import com.manugarcia010.weatherapp.ui.weekforecast.WeekForecastViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WeekForecastViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WeekForecastViewModel::class)
    abstract fun bindWeekForecastViewModel(weekForecastViewModel: WeekForecastViewModel): ViewModel
}
