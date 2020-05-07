package com.manugarcia010.weatherapp.ui.weekforecast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.manugarcia010.usecases.GetWeatherForecastByCoord
import com.manugarcia010.weatherapp.LiveDataTestUtil
import com.manugarcia010.weatherapp.MainCoroutineRule
import com.manugarcia010.weatherapp.R
import com.manugarcia010.weatherapp.data.FakeWeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeekForecastViewModelTest {

    private lateinit var weatherRepository: FakeWeatherRepository

    // Subject to be tested
    private lateinit var viewModel : WeekForecastViewModel

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        weatherRepository = FakeWeatherRepository()
        viewModel = WeekForecastViewModel(
            GetWeatherForecastByCoord(weatherRepository)
        )
    }

    @Test
    fun loadTasks_success() {
        // Make the repository return errors
        weatherRepository.setReturnError(false)

        // Load tasks
        viewModel.loadWeatherData()

        // Then progress indicator is hidden
        assertThat(LiveDataTestUtil.getValue(viewModel.dataLoading)).isFalse()

        // And the list of items is not null
        assertThat(LiveDataTestUtil.getValue(viewModel.items)).isNotNull()
    }

    @Test
    fun loadTasks_error() {
        // Make the repository return errors
        weatherRepository.setReturnError(true)

        // Load tasks
        viewModel.loadWeatherData()

        // Then progress indicator is hidden
        assertThat(LiveDataTestUtil.getValue(viewModel.dataLoading)).isFalse()

        // And the list of items is empty
        assertThat(LiveDataTestUtil.getValue(viewModel.items)).isEmpty()

        // todo: using TDD improve the error handling
    }
}