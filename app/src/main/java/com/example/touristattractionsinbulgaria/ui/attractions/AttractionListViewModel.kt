package com.example.touristattractionsinbulgaria.ui.attractions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.touristattractionsinbulgaria.data.Attraction
import com.example.touristattractionsinbulgaria.data.AttractionDao
import com.example.touristattractionsinbulgaria.data.ImageDao

class AttractionListViewModel(private val attractionDao: AttractionDao, private val imageDao: ImageDao) : ViewModel() {
    val allAttractions: LiveData<List<Attraction>> = attractionDao.getAllAttractions().asLiveData()
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