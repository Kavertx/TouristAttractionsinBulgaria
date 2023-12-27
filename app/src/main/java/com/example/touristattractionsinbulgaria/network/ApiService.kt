package com.example.touristattractionsinbulgaria.network

import com.example.touristattractionsinbulgaria.data.models.responseDataClasses.AttractionDataResponse
import com.example.touristattractionsinbulgaria.data.models.responseDataClasses.DistrictDataResponse
import com.example.touristattractionsinbulgaria.data.models.responseDataClasses.ImageFileResponse
import com.example.touristattractionsinbulgaria.data.models.responseDataClasses.ImageUrlResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Query

//TODO: For attractions in bulgarian, use the bg.wikipedia.org base url or just delete all attractions in bg
private const val BASE_URL = "https://en.wikipedia.org/w/api.php/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService{
    @GET("w/api.php")
    suspend fun getAttractionData(
        @Query("action") action: String = "query",
        @Query("format") format: String = "json",
        @Query("titles") titles: String,
        @Query("prop") prop: String = "extracts",
        @Query("explaintext") explainText: Boolean = true
    ): AttractionDataResponse

    @GET("w/api.php")
    suspend fun getAttractionImageFile(
        @Query("action") action: String = "query",
        @Query("format") format: String = "json",
        @Query("titles") titles: String,
        @Query("prop") prop: String = "images"
    ): ImageFileResponse

    @GET("w/api.php")
    suspend fun getAttractionImageUrl(
        @Query("action") action: String = "query",
        @Query("format") format: String = "json",
        @Query("titles") titles: String,
        @Query("prop") prop: String = "imageinfo",
        @Query("iiprop") iiprop: String = "url"
    ): ImageUrlResponse

    @GET("w/api.php")
    suspend fun getDistrictData(
        @Query("action") action: String = "query",
        @Query("format") format: String = "json",
        @Query("titles") titles: String,
        @Query("prop") prop: String = "extracts",
        @Query("explaintext") explainText: Boolean = true
    ): DistrictDataResponse

}

object TouristAttractionApi{
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

suspend fun getAttractionDataWikiRequest(titles: String): AttractionDataResponse {
    return withContext(Dispatchers.IO) {
        TouristAttractionApi.retrofitService.getAttractionData(titles = titles)
    }
}
suspend fun getAttractionImageFileWikiRequest(titles: String): ImageFileResponse {
    return withContext(Dispatchers.IO) {
        TouristAttractionApi.retrofitService.getAttractionImageFile(titles = titles)
    }
}
suspend fun getAttractionImageUrlWikiRequest(titles: String): ImageUrlResponse {
    return withContext(Dispatchers.IO) {
        TouristAttractionApi.retrofitService.getAttractionImageUrl(titles = titles)
    }
}
suspend fun getDistrictDataWikiRequest(titles: String): DistrictDataResponse {
    return withContext(Dispatchers.IO) {
        TouristAttractionApi.retrofitService.getDistrictData(titles = titles)
    }
}
