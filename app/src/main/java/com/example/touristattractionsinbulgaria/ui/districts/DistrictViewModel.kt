package com.example.touristattractionsinbulgaria.ui.districts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.touristattractionsinbulgaria.data.DistrictDao

class DistrictViewModel(private val districtDao: DistrictDao) : ViewModel()


class DistrictViewModelFactory(private val districtDao: DistrictDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DistrictViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DistrictViewModel(districtDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}