package com.example.touristattractionsinbulgaria.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = District::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("attractionDistrictId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Attraction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val attractionName: String,
    @ColumnInfo val description: String,
    @ColumnInfo val attractionDistrictId: Int
)
