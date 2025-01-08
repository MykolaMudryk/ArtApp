package com.example.artapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.artapp.data.local.Converters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "department_objects")
data class DepartmentObjects(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Primary key для Room

    val departmentId: Int, // Ідентифікатор департаменту для локального зберігання у Room

    @SerializedName("total")
    val total: Int,

    @TypeConverters(Converters::class)
    @SerializedName("objectIDs")
    val objectIDs: List<Int>
)
