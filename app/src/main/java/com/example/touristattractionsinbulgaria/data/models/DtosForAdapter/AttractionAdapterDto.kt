package com.example.touristattractionsinbulgaria.data.models.DtosForAdapter

data class AttractionAdapterDto(
    val id: Int = 0,
    val attractionName: String,
    val description: String,
    val attractionDistrictId: Int,
    val imageUrlList: List<String>
)
