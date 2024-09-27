package com.creature.craze.shambambukli.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.creature.craze.shambambukli.App
import com.valoriur.DataBase.AppDatabase
import com.valoriur.DataBase.Cell
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CellViewModel(private val database: AppDatabase) : ViewModel() {

    val list = database.dao.getAllCards()

    companion object {
        val factiry: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return CellViewModel(database) as T
            }
        }
    }

    fun insertCell(cell: Cell) {
        viewModelScope.launch {
            database.dao.insert(cell)
        }
    }

    fun updateCell(cell: Cell) {
        viewModelScope.launch {
            database.dao.update(cell)
        }
    }

    fun deleteCell(cell: Cell) {
        viewModelScope.launch {
            database.dao.delete(cell)
        }
    }

    fun saveToSharedPreferences(context: Context, key: String, value: Int) {
        val sharedPref = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(key, value)
            apply()
        }
    }

    // Функция для загрузки значения из SharedPreferences
    fun loadFromSharedPreferences(context: Context, key: String): Int {
        val sharedPref = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        return sharedPref.getInt(key, 0) // Возвращаем 0 если ключ не найден
    }
}