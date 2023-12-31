package com.example.touristattractionsinbulgaria.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.touristattractionsinbulgaria.data.models.AttractionWithImages

@Dao
interface AttractionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(attraction: Attraction)

    @Query(
        "SELECT * " +
                "FROM attraction " +
                "ORDER BY attractionName"
    )
    suspend fun getAllAttractions(): List<Attraction>

    @Query(
        "SELECT * " +
                "FROM attraction " +
                "WHERE attractionName= :attractionName"
    )
    fun getAttraction(attractionName: String): Attraction

    @Query(
        "SELECT id " +
                "FROM attraction " +
                "WHERE attractionName = :attractionName"
    )
    suspend fun getAttractionId(attractionName: String): Int

    @Query(
        "SELECT id " +
                "FROM attraction"
    )
    suspend fun getAllAttractionIds(): List<Int>

    @Query(
        "SELECT * " +
                "FROM attraction " +
                "WHERE attractionDistrictId= :id " +
                "ORDER BY attractionName"
    )
    suspend fun getAttractionsWithDistrictId(id: Int): List<AttractionWithImages>

    @Query(
        "SELECT * " +
                "FROM attraction " +
                "ORDER BY attractionName"
    )
    suspend fun getAllAttractionsWithImages(): List<AttractionWithImages>

    @Query(
        "SELECT * " +
                "FROM attraction " +
                "WHERE id=:id"
    )
    suspend fun getOneAttractionWithImages(id: Int): AttractionWithImages
}