package com.example.artapp.data.local

import androidx.room.TypeConverter
import com.example.artapp.data.entity.Department
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromDepartmentList(departmentList: List<Department>?): String? {
        return Gson().toJson(departmentList)
    }

    @TypeConverter
    fun toDepartmentList(departmentListJson: String?): List<Department>? {
        val type = object : TypeToken<List<Department>>() {}.type
        return Gson().fromJson(departmentListJson, type)
    }

    @TypeConverter
    fun fromList(list: List<Int>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(data: String?): List<Int>? {
        if (data == null) return emptyList()
        val type = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(data, type)
    }
}
