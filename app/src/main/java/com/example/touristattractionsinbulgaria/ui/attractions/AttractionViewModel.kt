package com.example.touristattractionsinbulgaria.ui.attractions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.touristattractionsinbulgaria.data.AttractionDao

class AttractionViewModel(private val attractionDao: AttractionDao)  : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}

class AttractionViewModelFactory(private val attractionDao: AttractionDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttractionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AttractionViewModel(attractionDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}