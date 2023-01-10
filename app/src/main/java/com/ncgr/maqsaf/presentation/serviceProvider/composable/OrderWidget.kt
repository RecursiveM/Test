package com.ncgr.maqsaf.presentation.serviceProvider.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ncgr.maqsaf.domain.menu.model.Item
import com.ncgr.maqsaf.domain.order.model.Order
import com.ncgr.maqsaf.ui.theme.Blue
import com.ncgr.maqsaf.ui.theme.Green
import com.ncgr.maqsaf.ui.theme.Red
import com.ncgr.maqsaf.ui.theme.Yellow

@Composable
fun OrderWidget(
    modifier: Modifier = Modifier,
    order: Order,
    item: Item,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(20))
            .background(
                when (order.zoneColor) {
                    "Yellow" -> Yellow
                    "Blue" -> Blue
                    "Green" -> Green
                    else -> Red
                }
            )
            .clickable {}
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.type,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
                    .background(Color.White)
            )
            Text(
                text = order.orderNumber.toString(),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 30.sp,
                    shadow = Shadow(
                        Color.Black, blurRadius = 10f
                    )
                ),
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(50.dp)
                        .width(80.dp)
                        .clip(RoundedCornerShape(10))
                        .background(Color.White)
                        .clickable {  }
                ) {
                    Text(
                        text = "Reject",
                        style = TextStyle(
                            color = Color.Red,
                            fontSize = 20.sp,
                            shadow = Shadow(Color.Black, blurRadius = 2f)
                        ),
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(50.dp)
                        .width(80.dp)
                        .clip(RoundedCornerShape(10))
                        .background(Color.White)
                        .clickable {  }
                ) {
                    Text(
                        text = "Accept",
                        style = TextStyle(
                            color = Color.Green,
                            fontSize = 20.sp,
                            shadow = Shadow(Color.Black, blurRadius = 2f)
                        ),
                    )
                }
            }
        }
    }
}