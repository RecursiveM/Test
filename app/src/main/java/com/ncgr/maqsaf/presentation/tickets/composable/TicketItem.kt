package com.ncgr.maqsaf.presentation.tickets.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun TicketItem(
    modifier: Modifier = Modifier,
    id: String,
    description: String,
    respond: String,
    priority: String,
) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10))
            .background(Color.Gray)
            .padding(10.dp)
    ) {
        Text(text = "Description:")
        Text(text = description, style = TextStyle(color = Color.White))
        Text(text = "Priority:")
        Text(text = priority, style = TextStyle(color = Color.White))
        Text(text = "Respond:")
        Text(text = respond, style = TextStyle(color = Color.White))
    }
}