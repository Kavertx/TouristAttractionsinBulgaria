package com.example.touristattractionsinbulgaria.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Attraction::class, District::class, Image::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDb : RoomDatabase() {

    abstract fun attractionDao(): AttractionDao
    abstract fun districtDao(): DistrictDao
    abstract fun imageDao(): ImageDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDb? = null

        fun getDatabase(context: Context): RoomDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java,
                    "tourist_attraction_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}