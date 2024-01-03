package com.example.touristattractionsinbulgaria.data.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.touristattractionsinbulgaria.data.Attraction
import com.example.touristattractionsinbulgaria.data.Image

data class AttractionWithImages(
    @Embedded val attraction: Attraction,
    @Relation(
        parentColumn = "id",
        entityColumn = "attractionId"
    )
    val images: List<Image>
)