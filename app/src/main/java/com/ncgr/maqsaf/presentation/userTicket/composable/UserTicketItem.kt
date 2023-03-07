package com.ncgr.maqsaf.presentation.userTicket.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp

@Composable
fun UserTicketItem(
    modifier: Modifier = Modifier,
    id: String,
    description: String,
    respond: String,
    priority: String,
) {
    Surface(
        modifier = modifier
            .padding(10.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(20)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20))
                .background(Color.White)
                .padding(20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(text = ":الوصف")
                Text(
                    text = description,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textDirection = TextDirection.Rtl
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = ":الأهمية")
                Text(
                    text = priority,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textDirection = TextDirection.Rtl
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = ":الرد")
                Text(
                    text = respond,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textDirection = TextDirection.Rtl
                    )
                )
            }
        }
    }
}