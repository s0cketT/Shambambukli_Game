package com.valoriur.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CellDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cell: Cell)

    @Update
    suspend fun update(cell: Cell)

    @Delete
    suspend fun delete(cell: Cell)

    @Query("SELECT * FROM cell")
    fun getAllCards(): Flow<List<Cell>>

}
