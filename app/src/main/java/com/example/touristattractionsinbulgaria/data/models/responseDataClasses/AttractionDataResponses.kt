package com.example.touristattractionsinbulgaria.data.models.responseDataClasses





data class AttractionDataResponse(
    val query: QueryAttraction
)
data class QueryAttraction(
    val pages: Map<String, Page>
)
data class Page(
    val title: String,
    val extract: String?
)


//Image File Names
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


//Image URL
data class ImageUrlResponse(
    val query: QueryImageUrls
)
data class QueryImageUrls(
    val pages: Map<String, ImageInfo>
)

data class ImageInfo(
    val title: String,
    val imageinfo: List<ImageUrl>
)
data class ImageUrl(
    val url: String
)