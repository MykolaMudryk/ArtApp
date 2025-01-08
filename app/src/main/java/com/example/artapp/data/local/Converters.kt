package com.example.artapp.data.local


import androidx.room.TypeConverter;
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    // Конвертація списку Int у JSON-рядок
    @TypeConverter
    fun fromList(list: List<Int>?): String? {
        return Gson().toJson(list)
    }

    // Конвертація JSON-рядка у список Int
    @TypeConverter
    fun toList(data: String?): List<Int>? {
        if (data == null) return emptyList()
        val type = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(data, type)
    }
}