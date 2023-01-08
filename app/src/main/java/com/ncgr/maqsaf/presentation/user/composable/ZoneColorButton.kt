package com.ncgr.maqsaf.presentation.user.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel

@Composable
fun ZoneColorButton(
    color: Color,
    strokeTriggerColor: String,
    selectedZoneColor : String,
    viewModel: UserViewModel
) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(50.dp)
            .background(color)
            .border(
                BorderStroke(
                    if (selectedZoneColor == strokeTriggerColor) 3.dp else 0.dp,
                    Color.Black
                )
            )
            .clickable {
                viewModel.changeZoneColor(color)
            }
    )
}