package com.manugarcia010.weatherapp.ui.weekforecast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.manugarcia010.usecases.GetWeatherForecastByCoord
import com.manugarcia010.weatherapp.LiveDataTestUtil
import com.manugarcia010.weatherapp.MainCoroutineRule
import com.manugarcia010.weatherapp.data.FakeWeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
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

    //Since the example is so simple, the test deos not make much sense, but it's just an example
    @Test
    fun loadWeatherData_success() {
        // Make the repository return errors
        weatherRepository.setReturnError(false)

        // Load weather data
        viewModel.loadWeatherData()

        // Then progress indicator is hidden
        assertThat(LiveDataTestUtil.getValue(viewModel.dataLoading)).isFalse()

        // And the list of items is not null
        assertThat(LiveDataTestUtil.getValue(viewModel.items)).isNotNull()
    }

    @Test
    fun loadWeatherData_error() {
        // Make the repository return errors
        weatherRepository.setReturnError(true)

        // Load data
        viewModel.loadWeatherData()

        // Then progress indicator is hidden
        assertThat(LiveDataTestUtil.getValue(viewModel.dataLoading)).isFalse()

        // And the list of items is empty
        assertThat(LiveDataTestUtil.getValue(viewModel.items)).isEmpty()

        // todo: using TDD improve the error handling
    }

    @Test
    fun loadWeatherDataFromRepository_loadingTogglesAndDataLoaded() {
        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        // Given an initialized TasksViewModel with initialized weather data
        // When data is requested
        // Trigger loading of tasks
        viewModel.loadWeatherData()

        // Then progress indicator is shown
        assertThat(LiveDataTestUtil.getValue(viewModel.dataLoading)).isTrue()

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden
        assertThat(LiveDataTestUtil.getValue(viewModel.dataLoading)).isFalse()

        // And data correctly loaded
        assertThat(LiveDataTestUtil.getValue(viewModel.items)).isNotNull()
    }
}