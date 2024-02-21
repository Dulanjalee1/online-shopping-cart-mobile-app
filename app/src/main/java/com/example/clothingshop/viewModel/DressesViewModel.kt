package com.example.clothingshop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothingshop.entity.Dresses
import com.example.clothingshop.modelFactory.DressesRespository
import kotlinx.coroutines.launch

class DressesViewModel(private val dressesRespository: DressesRespository) : ViewModel() {
    val allDressesInfo = MutableLiveData<List<Dresses>>()

    init {
        fetchAllDressesInfo()
    }
    private fun fetchAllDressesInfo() {
        viewModelScope.launch {
            val data = dressesRespository.getAllDressesInfo()
            allDressesInfo.value = data
        }
    }
}