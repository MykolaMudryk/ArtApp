package com.example.artapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "department_objects")
data class DepartmentObjects(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Primary key для Room

    val departmentId: Int, // Ідентифікатор департаменту для локального зберігання у Room

    @SerializedName("total")
    val total: Int,

    @SerializedName("objectIDs")
    val objectIDs: List<Int>
)
