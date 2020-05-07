package com.manugarcia010.weatherapp.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manugarcia010.weatherapp.ui.extensions.parentActivity
import com.manugarcia010.weatherapp.ui.model.DailyWeather
import com.manugarcia010.weatherapp.ui.weekforecast.WeatherForecastAdapter


@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<DailyWeather>) {
    (listView.adapter as WeatherForecastAdapter).submitList(items)
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.parentActivity
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value ->
            view.visibility = value ?: View.VISIBLE
        })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.parentActivity
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

@BindingAdapter("android:src")
fun setImageUrl(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}