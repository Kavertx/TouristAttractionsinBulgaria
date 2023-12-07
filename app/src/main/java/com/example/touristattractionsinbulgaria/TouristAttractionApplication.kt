package com.example.touristattractionsinbulgaria

import android.app.Application
import com.example.touristattractionsinbulgaria.data.RoomDb

class TouristAttractionApplication: Application() {
    val database: RoomDb  by lazy { RoomDb.getDatabase(this)}
}