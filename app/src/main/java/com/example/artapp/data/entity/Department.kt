package com.example.artapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Department(
    @PrimaryKey
    @SerializedName("departmentId")
    val departmentId: Int,

    @SerializedName(" v")
    val displayName: String
)
