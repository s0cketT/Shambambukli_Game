package com.creature.craze.shambambukli

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.creature.craze.shambambukli.Game.MainScreen
import com.creature.craze.shambambukli.ViewModel.CellViewModel
import com.creature.craze.shambambukli.ui.theme.ShambambukliTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShambambukliTheme {
                MainScreen()
            }
        }
    }
}

