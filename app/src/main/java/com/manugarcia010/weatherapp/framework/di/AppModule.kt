package com.manugarcia010.weatherapp.framework.di

import android.content.Context
import com.manugarcia010.weatherapp.App
import com.manugarcia010.weatherapp.BuildConfig
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class AppModule(){

    @Provides
    @Singleton
    fun provideContext(application: App) : Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    open fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(20L, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    open fun providesDispatcher(okHttpClient: OkHttpClient): Dispatcher {
        // This can be used to cancel all pending request
        return okHttpClient.dispatcher()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build()
    }

}