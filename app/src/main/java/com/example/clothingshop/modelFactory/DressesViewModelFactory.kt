package com.example.clothingshop.modelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.clothingshop.DAO.DressesDao
import com.example.clothingshop.entity.Dresses
import com.example.clothingshop.viewModel.DressesViewModel

class DressesRespository(private  val dressesDao: DressesDao) {
    suspend fun getAllDressesInfo(): List<Dresses> {
        return dressesDao.getAllDresses()
    }
}

class DressesViewModelFactory(private val dressesRespository: DressesRespository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DressesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return DressesViewModel(dressesRespository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}