package com.manugarcia010.weatherapp.framework.di

import com.manugarcia010.data.DefaultWeatherRepository
import com.manugarcia010.data.WeatherPersistenceDataSource
import com.manugarcia010.data.WeatherRemoteDataSource
import com.manugarcia010.data.WeatherRepository
import com.manugarcia010.usecases.GetWeatherForecastByCoord
import com.manugarcia010.weatherapp.framework.datasource.WeatherPersistenceDataSourceImpl
import com.manugarcia010.weatherapp.framework.datasource.WeatherRemoteDataSourceImpl
import com.manugarcia010.weatherapp.framework.datasource.database.WeatherForecastDao
import com.manugarcia010.weatherapp.framework.service.WeatherDataAccessService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

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
    fun provideWeatherPersistenceDataSource(weatherForecastDao: WeatherForecastDao): WeatherPersistenceDataSource
            = WeatherPersistenceDataSourceImpl(weatherForecastDao)

    @Provides
    fun provideWeatherRepository(weatherPersistenceDataSource: WeatherPersistenceDataSource, weatherRemoteDataSource: WeatherRemoteDataSource): WeatherRepository
            = DefaultWeatherRepository(weatherPersistenceDataSource, weatherRemoteDataSource)

    @Provides
    fun provideGetWeatherForecastByCoord(weatherRepository: WeatherRepository): GetWeatherForecastByCoord
            = GetWeatherForecastByCoord(weatherRepository)

}