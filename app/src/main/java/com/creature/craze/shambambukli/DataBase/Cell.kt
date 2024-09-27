package com.valoriur.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cell")
data class Cell(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val live: Int
)
