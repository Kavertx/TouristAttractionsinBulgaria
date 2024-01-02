package com.example.touristattractionsinbulgaria.ui.attractions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.touristattractionsinbulgaria.data.Attraction
import com.example.touristattractionsinbulgaria.data.AttractionDao
import com.example.touristattractionsinbulgaria.data.District
import com.example.touristattractionsinbulgaria.data.ImageDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AttractionListViewModel(private val attractionDao: AttractionDao, private val imageDao: ImageDao) : ViewModel() {
    private val _allAttractions = MutableLiveData<List<Attraction>?>()
    val allAttractions: LiveData<List<Attraction>?> get() = _allAttractions

    fun fetchData(){
        viewModelScope.launch {
            _allAttractions.value = getAttractions()
        }
    }
    suspend fun getAttractions(): List<Attraction>{
        return withContext(Dispatchers.IO){
            attractionDao.getAllAttractions()
        }
    }


}

class AttractionListViewModelFactory(private val attractionDao: AttractionDao, private val imageDao: ImageDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttractionListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AttractionListViewModel(attractionDao, imageDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}