package com.example.clothingshop.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.clothingshop.entity.CartItem

@Dao
interface CartDao {
    @Insert
    suspend fun insert(cartItem: CartItem)

    @Update
    suspend fun update(cartItem: CartItem)

    @Delete
    suspend fun delete(cartItem: CartItem)

    @Query("SELECT * FROM CartItem")
    suspend fun getAllCartItems(): List<CartItem>

    @Query("SELECT * FROM CartItem WHERE id = :cartItemId")
    suspend fun getCartItemById(cartItemId: Long): CartItem

    @Query("UPDATE CartItem SET itemQty = :newQuantity, totalPrice = :newTotalPrice WHERE id = :cartItemId")
    suspend fun updateCartItemQuantityAndTotalPrice(cartItemId: Long, newQuantity: Int, newTotalPrice: Double)
}