package com.manugarcia010.weatherapp.framework.datasource.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.manugarcia010.weatherapp.framework.datasource.database.entity.WeatherEntity
import java.util.*

class WeatherEntityConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<WeatherEntity> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<WeatherEntity>>() {}.type

        return gson.fromJson<List<WeatherEntity>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<WeatherEntity>): String {
        return gson.toJson(someObjects)
    }
}
