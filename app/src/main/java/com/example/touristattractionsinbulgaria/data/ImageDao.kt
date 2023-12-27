package com.example.touristattractionsinbulgaria.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(image: Image)

    @Query("SELECT * FROM image WHERE attractionId= :attractionId")
    fun getAllImagesForAttraction(attractionId: Int): List<Image>
    @Query("SELECT * FROM image WHERE imageUrl= :imageUrl")
    fun getImage(imageUrl: String): Image
}