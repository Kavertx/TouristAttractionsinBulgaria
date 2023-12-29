package com.example.touristattractionsinbulgaria.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

@Dao
interface DistrictDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(district: District)

    @Query("SELECT districtName FROM district ORDER BY districtName ASC")
    fun getAllDistrictNames(): List<String>
    @Query("SELECT * FROM district WHERE districtName= :districtName")
    fun getDistrict(districtName: String): District

    @Query("SELECT id FROM district WHERE districtName= :districtName")
    fun getDistrictId(districtName: String): Int

    @Query("SELECT id FROM district")
    fun  getAllDistrictIds(): List<Int>
}