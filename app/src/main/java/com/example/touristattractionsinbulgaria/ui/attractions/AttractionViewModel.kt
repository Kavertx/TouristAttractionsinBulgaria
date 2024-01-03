package com.example.touristattractionsinbulgaria.ui.attractions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.touristattractionsinbulgaria.data.AttractionDao
import com.example.touristattractionsinbulgaria.data.ImageDao

class AttractionViewModel(
    private val attractionDao: AttractionDao,
    private val imageDao: ImageDao
) : ViewModel()

class AttractionViewModelFactory(
    private val attractionDao: AttractionDao,
    private val imageDao: ImageDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttractionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AttractionViewModel(attractionDao, imageDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}