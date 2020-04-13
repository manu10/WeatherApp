package com.manugarcia010.usecases

import com.manugarcia010.domain.model.Coord
import com.manugarcia010.data.WeatherRepository

class GetWeatherForecastByCoord (private val weatherRepository: WeatherRepository) {
    operator fun invoke(coord: Coord) = weatherRepository.getWeatherForecast(coord)
}
