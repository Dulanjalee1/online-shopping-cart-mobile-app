package com.example.clothingshop.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.clothingshop.entity.Denims

@Dao
interface DenimsDao {
    @Insert
    suspend fun insert(denims: Denims)

    @Update
    suspend fun update(denims: Denims)

    @Delete
    suspend fun delete(denims: Denims)

    @Query("SELECT * FROM Denims")
    suspend fun getAllDenims(): List<Denims>

    @Query("SELECT * FROM Denims WHERE id = :denimsId")
    suspend fun getDenimsById(denimsId: Long): Denims
}
