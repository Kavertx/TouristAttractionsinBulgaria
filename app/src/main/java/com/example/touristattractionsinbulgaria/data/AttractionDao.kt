package com.example.touristattractionsinbulgaria.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AttractionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(attraction: Attraction)
    @Query("SELECT * " +
            "FROM attraction " +
            "ORDER BY attractionName")
    fun getAllAttractions(): Flow<List<Attraction>>
    @Query("SELECT * " +
            "FROM attraction " +
            "WHERE attractionName= :attractionName")
    fun getAttraction(attractionName: String): Attraction
    @Query("SELECT id " +
            "FROM attraction " +
            "WHERE attractionName = :attractionName")
    fun getAttractionId(attractionName: String): Int

    @Query("SELECT id " +
            "FROM attraction")
    fun  getAllAttractionIds(): List<Int>

    @Query("SELECT * " +
            "FROM attraction " +
            "WHERE attractionDistrictId= :id " +
            "ORDER BY attractionName")
    fun getAttractionsWithDistrictId(id: Int): List<Attraction>
}