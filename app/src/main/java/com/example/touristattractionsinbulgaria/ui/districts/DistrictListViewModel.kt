package com.example.touristattractionsinbulgaria.ui.districts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.touristattractionsinbulgaria.data.DistrictDao
import com.example.touristattractionsinbulgaria.ui.attractions.AttractionViewModel

class DistrictListViewModel(private val districtDao: DistrictDao) : ViewModel() {

    fun doNothing(){
        val a = 4+9
        Log.d("a", "$a")
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