package com.example.clothingshop.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.clothingshop.DAO.DenimsDao
import com.example.clothingshop.DAO.CartDao
import com.example.clothingshop.DAO.DressesDao
import com.example.clothingshop.entity.Denims
import com.example.clothingshop.entity.CartItem
import com.example.clothingshop.entity.Dresses

@Database(entities = [Dresses::class, Denims::class, CartItem::class], version = 1,exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun dressesDao(): DressesDao
    abstract fun denimsDao(): DenimsDao
    abstract fun cartDao(): CartDao

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "shopping_cart"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}