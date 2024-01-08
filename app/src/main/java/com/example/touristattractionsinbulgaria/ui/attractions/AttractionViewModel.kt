package com.example.touristattractionsinbulgaria.ui.attractions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.touristattractionsinbulgaria.data.AttractionDao
import com.example.touristattractionsinbulgaria.data.models.AttractionWithImages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AttractionViewModel(
    private val attractionDao: AttractionDao
) : ViewModel() {
    private val _allAttractions = MutableLiveData<List<AttractionWithImages>?>()
    val allAttractions: LiveData<List<AttractionWithImages>?> get() = _allAttractions
    private val _selectedAttraction = MutableLiveData<AttractionWithImages>()
    val selectedAttraction: LiveData<AttractionWithImages> get() = _selectedAttraction
    private val _selectedDistrictAttractions = MutableLiveData<List<AttractionWithImages>>()
    val selectedDistrictAttractions: LiveData<List<AttractionWithImages>> get() = _selectedDistrictAttractions


    fun setAttractionList() {
        viewModelScope.launch {
            _allAttractions.value = getAttractions()
        }
    }

    private suspend fun getAttractions(): List<AttractionWithImages> {
        return withContext(Dispatchers.IO) {
            attractionDao.getAllAttractionsWithImages()
        }
    }

    fun setOneAttraction(id: Int) {
        viewModelScope.launch {
            _selectedAttraction.value = getOneAttraction(id)
        }
    }

    private suspend fun getOneAttraction(id: Int): AttractionWithImages {
        return withContext(Dispatchers.IO) {
            attractionDao.getOneAttractionWithImages(id)
        }
    }

    fun setAttractionListWithDistrict(id:Int) {
        viewModelScope.launch {
            _selectedDistrictAttractions.value = getAttractionsForDistrict(id)
        }
    }

    private suspend fun getAttractionsForDistrict(id:Int): List<AttractionWithImages> {
        return withContext(Dispatchers.IO) {
            attractionDao.getAttractionsWithDistrictId(id)
        }
    }


}

class AttractionViewModelFactory(
    private val attractionDao: AttractionDao,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttractionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AttractionViewModel(attractionDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}