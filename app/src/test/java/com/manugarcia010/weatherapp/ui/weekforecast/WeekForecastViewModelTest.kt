package com.manugarcia010.weatherapp.ui.weekforecast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.manugarcia010.data.WeatherRepository
import com.manugarcia010.domain.Response
import com.manugarcia010.domain.model.*
import com.manugarcia010.usecases.GetWeatherForecastByCoord
import com.manugarcia010.weatherapp.LiveDataTestUtil
import com.manugarcia010.weatherapp.MainCoroutineRule
import com.manugarcia010.weatherapp.data.FakeWeatherRepository
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.joda.time.DateTimeZone
import org.joda.time.tz.UTCProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class WeekForecastViewModelTest {

    // Subject to be tested
    private lateinit var viewModel : WeekForecastViewModel

    @Mock
    lateinit var weatherRepository: WeatherRepository

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // this initialization is done to fix a problem with joda time lib (https://github.com/dlew/joda-time-android/issues/148)
        DateTimeZone.setProvider(UTCProvider())
        MockitoAnnotations.initMocks(this)
        viewModel = WeekForecastViewModel(
            GetWeatherForecastByCoord(weatherRepository)
        )
    }

    //Since the example is so simple, the test deos not make much sense, but it's just an example
    @Test
    fun loadWeatherData_success() = runBlockingTest{
        // Make the repository return a correct value
        Mockito.`when`(weatherRepository.getWeatherForecast(any())).thenReturn(
            Response.Success(FakeWeatherRepository.generateFakeWeatherForecast()))

        // Load weather data
        viewModel.loadWeatherData()

        // Then progress indicator is hidden
        assertThat(LiveDataTestUtil.getValue(viewModel.dataLoading)).isFalse()

        // And the list of items is not null
        assertThat(LiveDataTestUtil.getValue(viewModel.items)).isNotEmpty()
    }

    @Test
    fun loadWeatherData_error() = runBlockingTest {
        // Make the repository return errors
        Mockito.`when`(weatherRepository.getWeatherForecast(any())).thenReturn(Response.Error(Exception("One fake exception")))

        // Load data
        viewModel.loadWeatherData()

        // Then progress indicator is hidden
        assertThat(LiveDataTestUtil.getValue(viewModel.dataLoading)).isFalse()

        // And the list of items is empty
        assertThat(LiveDataTestUtil.getValue(viewModel.items)).isEmpty()

        // todo: using TDD improve the error handling
    }

    @Test
    fun loadWeatherDataFromRepository_loadingTogglesAndDataLoaded() = runBlockingTest {
        // Make the repository return a correct value
        Mockito.`when`(weatherRepository.getWeatherForecast(any())).thenReturn(
            Response.Success(FakeWeatherRepository.generateFakeWeatherForecast()))

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