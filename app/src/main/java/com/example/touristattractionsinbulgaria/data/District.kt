package com.example.touristattractionsinbulgaria.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class District(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val districtName: String,
    @ColumnInfo val districtDescription: String,
)
