package com.example.clothingshop.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.clothingshop.entity.Dresses

@Dao
interface DressesDao {
    @Insert
    suspend fun insert(dresses: Dresses)

    @Update
    suspend fun update(dresses: Dresses)

    @Delete
    suspend fun delete(dresses: Dresses)

    @Query("SELECT * FROM Dresses")
    suspend fun getAllDresses(): List<Dresses>

    @Query("SELECT * FROM Dresses WHERE id = :dressesId")
    suspend fun getDressesById(dressesId: Long): Dresses
}
