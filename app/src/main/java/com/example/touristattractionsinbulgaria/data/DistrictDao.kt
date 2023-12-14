package com.example.touristattractionsinbulgaria.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DistrictDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(district: District)

    @Query("SELECT districtName FROM district ORDER BY districtName ASC")
    fun getAllDistricts(): List<District>
    @Query("SELECT * FROM district WHERE districtName= :districtName")
    fun getDistrict(districtName: String): District

    @Query("SELECT id FROM district WHERE districtName= :districtName")
    fun getDistrictId(districtName: String): Int
}