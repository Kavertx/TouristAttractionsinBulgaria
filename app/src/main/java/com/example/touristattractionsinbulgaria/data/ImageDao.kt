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

    @Query("SELECT * " +
            "FROM image " +
            "WHERE attractionId= :attractionId")
    fun getAllImagesForAttraction(attractionId: Int): List<Image>
    @Query("SELECT * " +
            "FROM image " +
            "WHERE imageUrl= :imageUrl")
    fun getImage(imageUrl: String): Image


    @Query("SELECT id " +
            "FROM image")
    fun getAllImageIds(): List<Int>
}