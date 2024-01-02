package com.example.touristattractionsinbulgaria.ui.districts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.touristattractionsinbulgaria.data.Attraction
import com.example.touristattractionsinbulgaria.data.District
import com.example.touristattractionsinbulgaria.data.DistrictDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DistrictListViewModel(private val districtDao: DistrictDao) : ViewModel() {

    private val _allDistricts = MutableLiveData<List<District>?>()
    val allDistricts: LiveData<List<District>?> get() = _allDistricts

    fun fetchData(){
        viewModelScope.launch {
            _allDistricts.value = getAttractions()
        }
    }
    suspend fun getAttractions(): List<District>{
        return withContext(Dispatchers.IO){
            districtDao.getAllDistrictsFullInformation()
        }
    }


}




class DistrictListViewModelFactory(private val districtDao: DistrictDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DistrictListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DistrictListViewModel(districtDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}