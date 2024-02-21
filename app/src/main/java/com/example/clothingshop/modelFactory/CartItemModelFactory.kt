package com.example.clothingshop.modelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.clothingshop.DAO.CartDao
import com.example.clothingshop.entity.CartItem
import com.example.clothingshop.viewModel.CartItemViewModel

class CartItemRespository(private  val cartDao: CartDao) {
    suspend fun getAllCartInfo(): List<CartItem> {
        return cartDao.getAllCartItems()
    }
}

class CartItemModelFactory(private val cartItemRespository: CartItemRespository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartItemViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CartItemViewModel(cartItemRespository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}