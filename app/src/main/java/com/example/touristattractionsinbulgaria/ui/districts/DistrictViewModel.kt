package com.example.touristattractionsinbulgaria.ui.districts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.touristattractionsinbulgaria.data.District
import com.example.touristattractionsinbulgaria.data.DistrictDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DistrictViewModel(private val districtDao: DistrictDao) : ViewModel() {

    //data is static(already in the database),
    //but without this livedata-observe method,
    //it doesn't get properly sent to the adapter
    private val _allDistricts = MutableLiveData<List<District>?>()
    val allDistricts: LiveData<List<District>?> get() = _allDistricts

    private val _selectedDistrict = MutableLiveData<District>()
    val selectedDistrict: LiveData<District> get() = _selectedDistrict

    fun setDistrictList() {
        viewModelScope.launch {
            _allDistricts.value = getDistricts()
        }
    }

    private suspend fun getDistricts(): List<District> {
        return withContext(Dispatchers.IO) {
            districtDao.getAllDistrictsFullInformation()
        }
    }

    fun getSelectedDistrict(id: Int){
        viewModelScope.launch {
        _selectedDistrict.value = getOneDistrictInfo(id)
        }
    }
    private suspend fun getOneDistrictInfo(id: Int): District{
        return withContext(Dispatchers.IO){
            districtDao.getDistrictById(id)
        }
    }


}


class DistrictViewModelFactory(private val districtDao: DistrictDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DistrictViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DistrictViewModel(districtDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}