package com.creature.craze.shambambukli.Game

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.creature.craze.shambambukli.ViewModel.CellViewModel
import com.valoriur.DataBase.Cell
import kotlin.random.Random

@Preview
@Composable
fun MainScreen(viewModel: CellViewModel = viewModel(factory = CellViewModel.factiry)) {

    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp

    val context = LocalContext.current

    val cells = viewModel.list.collectAsState(initial = emptyList())
    var random by remember { mutableStateOf(Random.nextInt(2)) }

    //если равно 3, новая жизнь
    var checkLive by rememberSaveable { mutableStateOf(viewModel.loadFromSharedPreferences(context, "checkLive")) }
    //если равно 3, жизнь рядом умирает
    var checkDie by rememberSaveable { mutableStateOf(viewModel.loadFromSharedPreferences(context, "checkDie")) }
    //проверка удаления
    var checkTrue by remember { mutableStateOf(viewModel.loadFromSharedPreferences(context, "checkTrue")) }

    var countCell by remember { mutableStateOf(viewModel.loadFromSharedPreferences(context, "countCell")) }

    var liveCellId by rememberSaveable { mutableStateOf(viewModel.loadFromSharedPreferences(context, "liveCellId")) }


    var tempId = remember{ mutableStateOf(viewModel.loadFromSharedPreferences(context, "tempId")) }

    val listState = rememberLazyListState()

    LaunchedEffect(countCell) {
        listState.scrollToItem(index = countCell)
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF27003F)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Box(modifier = Modifier
            .size((width * 0.9).dp, (height * 0.15).dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Клеточное наполнение",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        LazyColumn(state = listState,
            modifier = Modifier
            .size((width * 1).dp, (height * 0.75).dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(cells.value) {cell ->
                CellScreen(cell, tempId)
                liveCellId = tempId.value
            }
        }

        Box(modifier = Modifier
            .size((width * 1).dp, (height * 0.15).dp),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                countCell++
                random = Random.nextInt(2)
                viewModel.insertCell(Cell(0, random))

                if (random == 0) {
                    checkLive = 0
                    checkDie++
                }
                else {
                    checkLive++
                    checkTrue = 0
                    checkDie = 0
                }
                if(checkLive == 3) {
                    checkTrue = 1
                    checkDie = 0
                    viewModel.insertCell(Cell(0, 2))
                    countCell++
                    checkLive = 0
                }
                if (checkDie == 3 && checkTrue == 1 || checkDie == 4 && checkTrue == 1) {
                    Log.d("vmfkmve", tempId.value.toString())
                    viewModel.deleteCell(Cell(liveCellId, 2))
                    countCell--
                    checkLive = 0
                    checkDie = 0
                }


                viewModel.saveToSharedPreferences(context, "checkLive", checkLive)
                viewModel.saveToSharedPreferences(context, "liveCellId", liveCellId)
                viewModel.saveToSharedPreferences(context, "countCell", countCell)
                viewModel.saveToSharedPreferences(context, "checkTrue", checkTrue)
                viewModel.saveToSharedPreferences(context, "tempId", tempId.value)
                viewModel.saveToSharedPreferences(context, "checkDie", checkDie)

             },
                colors = ButtonDefaults.buttonColors(Color(0xFFBA68C8)),
                modifier = Modifier.size((width * 0.95).dp, (height * 0.05).dp),
                shape = MaterialTheme.shapes.small,
            ) {
                Text(text = "СОТВОРИТЬ",
                    color = Color.White,
                    fontSize = 16.sp,
                    )
            }
        }
    }

}



