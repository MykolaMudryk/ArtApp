package com.example.artapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "art_objects")
data class ArtObject(
    @PrimaryKey
    @SerializedName("objectID")
    val objectID: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("artistDisplayName")
    val artist: String?,
    @SerializedName("primaryImageSmall")
    val imageUrl: String?,
    @SerializedName("objectDate")
    val objectDate: String?,
    @SerializedName("medium")
    val medium: String?
)