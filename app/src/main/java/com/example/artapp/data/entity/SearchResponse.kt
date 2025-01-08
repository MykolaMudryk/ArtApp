package com.example.artapp.data.entity

data class SearchResponse(
    val total: Int,             // Загальна кількість доступних об'єктів
    val objectIDs: List<Int>    // Список Object ID для об'єктів
)
