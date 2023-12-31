package com.example.touristattractionsinbulgaria.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//TODO: Check if stored data is the same
// as the data from the latest request
// and only save entities that don't match existing records
@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(image: Image)

    @Query(
        "SELECT imageUrl " +
                "FROM Image " +
                "WHERE attractionId=:attractionId " +
                "LIMIT 1"
    )
    suspend fun getOneImageForAttraction(attractionId: Int): String

    @Query(
        "SELECT imageUrl " +
                "FROM Image " +
                "WHERE attractionId=:attractionId"
    )
    suspend fun getAllImageUrlsForAttraction(attractionId: Int): List<String>

    @Query(
        "SELECT * " +
                "FROM image " +
                "WHERE attractionId= :attractionId"
    )
    fun getAllImagesForAttraction(attractionId: Int): List<Image>

    @Query(
        "SELECT * " +
                "FROM image " +
                "WHERE imageUrl= :imageUrl"
    )
    suspend fun getImage(imageUrl: String): Image


    @Query(
        "SELECT id " +
                "FROM image"
    )
    suspend fun getAllImageIds(): List<Int>
}