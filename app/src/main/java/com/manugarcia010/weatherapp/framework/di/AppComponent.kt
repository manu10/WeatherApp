package com.manugarcia010.weatherapp.framework.di
import com.manugarcia010.weatherapp.App
import com.manugarcia010.weatherapp.ui.WeekForecastActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidSupportInjectionModule::class),
    (AppModule::class),
    (ActivitiesModule::class)/*,
    (WeatherModule::class)*/]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder
        fun build(): AppComponent
    }
    fun inject(app: App)
}