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

class AttractionListViewModel(
    private val attractionDao: AttractionDao
) : ViewModel() {
    private val _allAttractions = MutableLiveData<List<AttractionWithImages>?>()
    val allAttractions: LiveData<List<AttractionWithImages>?> get() = _allAttractions


    fun setAttractionList() {
        viewModelScope.launch {
            _allAttractions.value = getAttractions()
        }
    }

    private suspend fun getAttractions(): List<AttractionWithImages> {
        return withContext(Dispatchers.IO) {
            attractionDao.getAttractionsWithImages()
        }
    }
}

class AttractionListViewModelFactory(
    private val attractionDao: AttractionDao,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttractionListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AttractionListViewModel(attractionDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}