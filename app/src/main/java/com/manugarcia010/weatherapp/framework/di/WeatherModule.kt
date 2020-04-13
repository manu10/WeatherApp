package com.manugarcia010.weatherapp.framework.di

import com.manugarcia010.data.WeatherRemoteDataSource
import com.manugarcia010.data.WeatherRepository
import com.manugarcia010.usecases.GetWeatherForecastByCoord
import com.manugarcia010.weatherapp.framework.WeatherRemoteDataSourceImpl
import com.manugarcia010.weatherapp.framework.service.WeatherDataAccessService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class WeatherModule {

    @Provides
    internal fun providesWeatherDataAccessService(retrofit: Retrofit): WeatherDataAccessService {
        return retrofit.create<WeatherDataAccessService>(WeatherDataAccessService::class.java)
    }

    @Provides
    fun provideWeatherRemoteDataSource(weatherDataAccessService: WeatherDataAccessService): WeatherRemoteDataSource
            = WeatherRemoteDataSourceImpl(weatherDataAccessService)

    @Provides
    fun provideWeatherRepository(weatherRemoteDataSource: WeatherRemoteDataSource): WeatherRepository
            = WeatherRepository(weatherRemoteDataSource)

    @Provides
    fun provideGetWeatherForecastByCoord(weatherRepository: WeatherRepository): GetWeatherForecastByCoord
            = GetWeatherForecastByCoord(weatherRepository)

}