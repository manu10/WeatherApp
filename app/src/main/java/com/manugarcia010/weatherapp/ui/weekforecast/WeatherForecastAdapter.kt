package com.manugarcia010.weatherapp.ui.weekforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manugarcia010.weatherapp.databinding.DailyWeatherItemBinding
import com.manugarcia010.weatherapp.ui.model.DailyWeather
import io.reactivex.subjects.PublishSubject
class WeatherForecastAdapter(private val viewModel: WeekForecastViewModel) :
    ListAdapter<DailyWeather, WeatherForecastAdapter.ViewHolder>(DailyWeatherDiffCallback()) {

    val dailyWeatherClickSubject: PublishSubject<DailyWeather> = PublishSubject.create<DailyWeather>() // todo

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    class ViewHolder private constructor(val binding: DailyWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: WeekForecastViewModel, item: DailyWeather) {
            /*todo:
               RxView.clicks(binding.root)
                .takeUntil(RxView.detaches(parent))
                .map { binding }
                .subscribe { dailyWeatherClickSubject.onNext(binding.dailyWeather) }*/
            binding.dailyWeatherViewModel = viewModel
            binding.dailyWeather = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DailyWeatherItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}
/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class DailyWeatherDiffCallback : DiffUtil.ItemCallback<DailyWeather>() {
    override fun areItemsTheSame(oldItem: DailyWeather, newItem: DailyWeather): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DailyWeather, newItem: DailyWeather): Boolean {
        return oldItem == newItem
    }
}
