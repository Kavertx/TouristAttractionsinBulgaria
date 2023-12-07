package com.example.touristattractionsinbulgaria.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface AttractionDao {
    @Insert
    suspend fun insert(attraction: Attraction)
}