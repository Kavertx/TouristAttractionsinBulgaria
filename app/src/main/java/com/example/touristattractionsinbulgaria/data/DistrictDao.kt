package com.example.touristattractionsinbulgaria.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.touristattractionsinbulgaria.data.models.AttractionWithImages

@Dao
interface DistrictDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(district: District)

    @Query(
        "SELECT districtName " +
                "FROM district " +
                "ORDER BY districtName"
    )
    suspend fun getAllDistrictNames(): List<String>

    @Query(
        "SELECT * " +
                "FROM district " +
                "WHERE districtName= :districtName"
    )
    suspend fun getDistrict(districtName: String): District

    @Query(
        "SELECT id " +
                "FROM district " +
                "WHERE districtName= :districtName"
    )
    suspend fun getDistrictId(districtName: String): Int

    @Query(
        "SELECT id " +
                "FROM district"
    )
    suspend fun getAllDistrictIds(): List<Int>

    @Query(
        "SELECT * " +
                "FROM District " +
                "ORDER BY districtName"
    )
    suspend fun getAllDistrictsFullInformation(): List<District>

    @Query(
        "SELECT * " +
                "FROM Attraction " +
                "WHERE attractionDistrictId=:districtId " +
                "ORDER BY attractionName"
    )
    suspend fun getAllAttractionsForDistrict(districtId: Int): List<AttractionWithImages>

    @Query("SELECT * " +
                    "FROM District " +
                    "WHERE id=:id"
    )
    suspend fun getDistrictById(id: Int): District

}