package com.example.touristattractionsinbulgaria.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.touristattractionsinbulgaria.common.AttractionArray
import com.example.touristattractionsinbulgaria.common.AttractionDistrictMap
import com.example.touristattractionsinbulgaria.common.DistrictsArray
import com.example.touristattractionsinbulgaria.data.Attraction
import com.example.touristattractionsinbulgaria.data.AttractionDao
import com.example.touristattractionsinbulgaria.data.District
import com.example.touristattractionsinbulgaria.data.DistrictDao
import com.example.touristattractionsinbulgaria.data.Image
import com.example.touristattractionsinbulgaria.data.ImageDao
import com.example.touristattractionsinbulgaria.network.getAttractionDataWikiRequest
import com.example.touristattractionsinbulgaria.network.getAttractionImageFileWikiRequest
import com.example.touristattractionsinbulgaria.network.getAttractionImageUrlWikiRequest
import com.example.touristattractionsinbulgaria.network.getDistrictDataWikiRequest
import kotlinx.coroutines.launch
import kotlin.math.log

class HomeViewModel(
    private val districtDao: DistrictDao,
    private val attractionDao: AttractionDao,
    private val imageDao: ImageDao) : ViewModel() {


    fun addDistricts(){
        viewModelScope.launch {
            val districtData = DistrictsArray.retrieveDistrictExtractData()
            districtData.forEach {
                districtDao.insert(it)
            }
        }
    }
    fun addAttractionData(){
        viewModelScope.launch {
            val attractionData = AttractionDistrictMap.retrieveAttractionExtractData()
            attractionData.forEach {
                attractionDao.insert(it)
            }
        }
    }
    fun addImages() {
        viewModelScope.launch {
            val attractionImages = AttractionArray.retrieveImageUrls()
            attractionImages.forEach {
                imageDao.insert(it)
            }
        }
    }
    fun doNothing(){
        val a = 4+9
        Log.d("a", "$a")
    }


    /**
     * Method which makes api calls to wikipedia and retrieves attraction extract data for each item in the hashMap
     * Properties:
     * v= districtName
     * k= attractionName
     *
     * Returns a list of Attraction objects
     */
    private suspend fun HashMap<String,String>.retrieveAttractionExtractData(): List<Attraction>{

        val attractionList : MutableList<Attraction> = mutableListOf()
        for ((k,v) in this){

            if (k[0]<500.toChar()){     //trying to remove the attractions with cyrillic names, because there will be a conflict with the base url of the api calls
                val response = getAttractionDataWikiRequest(k)
                val attraction = Attraction(
                    attractionDistrictId = districtDao.getDistrictId(v),
                    attractionName = k,
                    description = response.query.pages.values.firstOrNull()?.extract.toString()
                )
                attractionList.add(attraction)
            }

        }
        return attractionList
    }

    /**
     * Method which makes api calls to wikipedia and retrieves Urls for images in the array
     * Properties:
     * i = attractionName
     *
     * Returns a list of Image objects
     */
    private suspend fun List<String>.retrieveImageUrls(): List<Image>{
        val imageList : MutableList<Image> = mutableListOf()

        //i represents attractionName
        for (i in this){
            if (i[0]<500.toChar()) {     //trying to remove the attractions with cyrillic names, because there will be a conflict with the base url of the api calls
                val imageFileList: MutableList<String> = mutableListOf()
                val imageFileResponse =
                    getAttractionImageFileWikiRequest(i) // contains all img file names
                imageFileResponse.query?.pages?.values?.firstOrNull()?.images?.forEach {
                    // adds all image file names for this attraction to imageFileList
                    imageFileList.add(it.title.toString())
                }
                imageFileList.forEach {
                    val url =
                        getAttractionImageUrlWikiRequest(it).queryImageUrls.imageInfo.values.firstOrNull()?.url
                    val img = Image(
                        imageUrl = url.toString(),
                        attractionId = attractionDao.getAttractionId(i)
                    )
                    imageList.add(img)
                }
            }
        }
        return imageList
    }


    /**
     * Method which makes api calls to wikipedia and retrieves district data for each element in the array
     * Properties:
     * it = districtName
     *
     * Returns a list of District objects
     */
    private suspend fun List<String>.retrieveDistrictExtractData(): List<District>{
        val districtList: MutableList<District> = mutableListOf()
        this.forEach {
            val response = getDistrictDataWikiRequest(it)

            val district: District = District(
                districtName = it,
                districtDescription = response
                    .query
                    .pages
                    .values
                    .firstOrNull()
                    ?.extract
                    .toString()
            )
            districtList.add(district)
        }
        return districtList
    }
}
class HomeViewModelFactory(
    private val districtDao: DistrictDao,
    private val attractionDao: AttractionDao,
    private val imageDao: ImageDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(districtDao,attractionDao,imageDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}