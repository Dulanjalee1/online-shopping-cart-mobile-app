package com.example.clothingshop.modelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.clothingshop.DAO.DenimsDao
import com.example.clothingshop.entity.Denims
import com.example.clothingshop.viewModel.DenimsViewModel


class DenimsRespository(private  val denimsDao: DenimsDao) {
    suspend fun getAllDenimsInfo(): List<Denims> {
        return denimsDao.getAllDenims()
    }
}

class DenimsModelFactory(private val denimsRespository: DenimsRespository) :  ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DenimsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return DenimsViewModel(denimsRespository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}