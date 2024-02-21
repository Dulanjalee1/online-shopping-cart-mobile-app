package com.example.clothingshop.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val itemId: Long,
    val itemName: String,
    var itemQty: Int,
    val totalPrice: Double,
    val itemImage: String
)