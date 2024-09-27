package com.valoriur.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// База данных
@Database(entities = [Cell::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    //abstract fun userDao(): UserDao
    abstract val dao: CellDao

    companion object {
        fun createDataBase(context : Context) : AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).fallbackToDestructiveMigration().build()
        }
    }
}