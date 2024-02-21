package com.example.clothingshop.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Denims(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val denimName: String,
    val price: Double,
    val ImageName: String
)