package com.example.touristattractionsinbulgaria.data.models.responseDataClasses





data class AttractionDataResponse(
    val query: QueryAttraction
)
data class QueryAttraction(
    val pages: Map<String, Page>
)
data class Page(
    val title: String,
    val extract: String
)


data class ImageFileResponse(
    val batchcomplete: String?,
    val query: QueryImages?
)
data class QueryImages(
    val pages:Map<String,ImageFileArray>?
)
data class ImageFileArray(
    val pageid: Int?,
    val ns: Int?,
    val title: String?,
    val images: List<ImageFile>?
)
data class ImageFile(
    val ns: Int?,
    val title: String?,
)


data class ImageUrlResponse(
    val queryImageUrls: QueryImageUrls
)
data class QueryImageUrls(
    val imageInfo: Map<String, ImageUrl>
)
data class ImageUrl(
    val url: String
)