package com.creature.craze.shambambukli

import android.app.Application
import com.valoriur.DataBase.AppDatabase


class App : Application() {
    val database by lazy { AppDatabase.createDataBase(this) }
}