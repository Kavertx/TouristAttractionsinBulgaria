package com.example.touristattractionsinbulgaria.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(foreignKeys = arrayOf(ForeignKey(entity = Attraction::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("attractionId"),
    onDelete = ForeignKey.CASCADE)))
data class Image(
    @PrimaryKey(autoGenerate = true) val id: Int =0,
    @ColumnInfo val imageUrl: String,
    @ColumnInfo val attractionId: Int
)
