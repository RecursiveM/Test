package com.ncgr.maqsaf.presentation.orderDetails.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.presentation.orderDetails.viewModel.OrderDetailsViewModel
import com.ncgr.maqsaf.ui.theme.Blue
import com.ncgr.maqsaf.ui.theme.Green
import com.ncgr.maqsaf.ui.theme.Red
import com.ncgr.maqsaf.ui.theme.Yellow

@Composable
fun OrderDetailsBody(
    modifier: Modifier = Modifier,
    viewModel: OrderDetailsViewModel,
) {
    val zoneColor = viewModel.zoneColor.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .size(300.dp)
                .background(
                    when(zoneColor.value){
                        "Yellow" -> Yellow
                        "Blue" -> Blue
                        "Green" -> Green
                        else -> Red
                    }
                )
        ) {
            Text(
                viewModel.randomNumber.toString(),
                style = TextStyle(
                    color = Color.White, fontSize = 120.sp, shadow = Shadow(Color.Black, blurRadius = 10f)
                )
            )
        }
    }
}