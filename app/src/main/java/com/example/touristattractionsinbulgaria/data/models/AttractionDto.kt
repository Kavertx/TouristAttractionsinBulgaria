package com.example.touristattractionsinbulgaria.data.models

data class AttractionDto(
    val id: Int,
    val imageUrls: List<String>,
    val name: String,
    val description: String
)