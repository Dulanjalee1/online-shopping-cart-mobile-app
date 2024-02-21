package com.example.clothingshop.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dresses(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val dressName: String,
    val price: Double,
    val imageName: String
)

