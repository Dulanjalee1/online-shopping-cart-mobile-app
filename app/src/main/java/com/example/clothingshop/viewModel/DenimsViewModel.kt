package com.example.clothingshop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothingshop.entity.Denims
import com.example.clothingshop.modelFactory.DenimsRespository
import kotlinx.coroutines.launch

class DenimsViewModel(private val denimsRespository: DenimsRespository) : ViewModel() {
    val allDenimsInfo = MutableLiveData<List<Denims>>()

    init {
        fetchAllDenimsInfo()
    }
    private fun fetchAllDenimsInfo() {
        viewModelScope.launch {
            val data = denimsRespository.getAllDenimsInfo()
            allDenimsInfo.value = data
        }
    }
}