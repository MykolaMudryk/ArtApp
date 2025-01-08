package com.example.artapp.data.entity

import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.artapp.data.local.Converters

@Entity(tableName = "search_response")
data class SearchResponse(
    val total: Int,             // Загальна кількість доступних об'єктів
    @TypeConverters(Converters::class)
    val objectIDs: List<Int>    // Список Object ID для об'єктів
)
