package com.example.touristattractionsinbulgaria.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO: Find a way to attach district to attraction in order to be able to sort them
@Entity
data class Attraction(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo val imageUrls: List<String>,
    @ColumnInfo val attractionName: String,
    @ColumnInfo val description: String,
    @ColumnInfo val attractionDistrict: String
)
