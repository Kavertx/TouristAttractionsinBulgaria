package com.example.touristattractionsinbulgaria.data.models.responseDataClasses

data class DistrictDataResponse(
    val query: QueryDistrict
)
data class QueryDistrict(
    val pages: Map<String, Page>
)
data class District(
    val title: String,
    val extract: String
)