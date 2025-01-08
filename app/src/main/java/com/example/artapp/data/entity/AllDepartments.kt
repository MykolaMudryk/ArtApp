package com.example.artapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.artapp.data.local.Converters

@Entity(tableName = "all_departments")
data class AllDepartments(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
    ,
    @TypeConverters(Converters::class)
    val departments: List<Department>
)
