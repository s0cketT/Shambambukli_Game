package com.creature.craze.shambambukli.Game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.creature.craze.shambambukli.R
import com.creature.craze.shambambukli.ViewModel.CellViewModel
import com.valoriur.DataBase.Cell


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CellScreen(
    cell: Cell, tempId: MutableState<Int>
    ) {

    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp

    if (cell.live == 2) tempId.value = cell.id


    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .size(
                (width * 1).dp, (height * 0.15).dp
            )
            .padding((width * 0.02).dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(start = (width * 0.1).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
            ) {
            Box(modifier = Modifier
                .size((width * 0.15).dp),
                contentAlignment = Alignment.Center
                ) {
                Image(painter = painterResource(id = if(cell.live == 2) R.drawable.ellipse3
                else if (cell.live == 0) R.drawable.ellipse else R.drawable.ellipse2), contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                    )

                Image(painter = painterResource(id = if(cell.live == 2) R.drawable.lable3
                else if (cell.live == 0) R.drawable.lable else R.drawable.lable2), contentDescription = "",
                    modifier = Modifier.size((width * 0.08).dp)
                    )
            }

            Spacer(modifier = Modifier.size((width * 0.05).dp))

            Column {
                Text(text = if(cell.live == 0) "Мертвая" else "Живая",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                    )
                Text(text = if(cell.live == 0) "или прикидывается" else "и шевелится!",
                    color = Color.Black,
                    fontSize = 16.sp
                    )
            }
        }
    }

}