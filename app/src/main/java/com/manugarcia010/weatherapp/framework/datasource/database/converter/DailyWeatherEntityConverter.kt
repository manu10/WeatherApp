package com.manugarcia010.weatherapp.framework.datasource.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.manugarcia010.weatherapp.framework.datasource.database.entity.DailyWeatherEntity
import java.util.*

class DailyWeatherEntityConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<DailyWeatherEntity> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<DailyWeatherEntity>>() {}.type

        return gson.fromJson<List<DailyWeatherEntity>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<DailyWeatherEntity>): String {
        return gson.toJson(someObjects)
    }
}
