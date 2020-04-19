package com.manugarcia010.weatherapp

import android.app.Activity
import android.app.Application
import androidx.room.Room
import com.manugarcia010.weatherapp.framework.datasource.database.WeatherDataBase
import com.manugarcia010.weatherapp.framework.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        val db = Room.databaseBuilder<WeatherDataBase>(
            applicationContext,
            WeatherDataBase::class.java, BuildConfig.DATABASE
        ).build()
        DaggerAppComponent
            .builder()
            .application(this)
            .database(db)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}