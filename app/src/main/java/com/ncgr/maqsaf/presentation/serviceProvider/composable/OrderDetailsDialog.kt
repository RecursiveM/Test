package com.ncgr.maqsaf.presentation.serviceProvider.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ncgr.maqsaf.data.remote.model.OrderListItemDto
import com.ncgr.maqsaf.presentation.serviceProvider.viewModel.ServiceProviderViewModel
import com.ncgr.maqsaf.ui.theme.Red

@Composable
fun OrderDetailsDialog(
    modifier: Modifier = Modifier,
    viewModel: ServiceProviderViewModel,
    orderListItem: OrderListItemDto?,
) {

    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.Black.copy(alpha = 0.6f))
        .clickable(enabled = false) { }
        .padding(horizontal = 20.dp, vertical = 50.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10))
                .background(Color.White)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {

                item {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier =
                        Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .clip(CircleShape)
                                .background(Red)
                                .clickable {
                                    viewModel.closeOrderDetailsDialog()
                                }
                        ) {
                            Text(text = "Back", style = TextStyle(color = Color.White))
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1.5f),
                            text = "Item",
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            modifier = Modifier
                                .weight(1.2f),
                            text = "Type",
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f),
                            text = "Milk",
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f),
                            text = "Sugar",
                            textAlign = TextAlign.Center,
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        for (item in orderListItem!!.have) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1.5f),
                                ) {
                                    AsyncImage(
                                        model = item.item.itemImage,
                                        contentDescription = "",
                                        contentScale = ContentScale.Inside,
                                    )
                                }
                                Text(
                                    modifier = Modifier
                                        .weight(1.2f),
                                    text = when (item.type) {
                                        "ماء" -> "Water"
                                        "شاهي" -> "Regular Tea"
                                        "قهوة" -> "Regular Coffee"
                                        "حبق" -> "Basil Tea"
                                        "نعناع" -> "Mint Tea"
                                        "امريكية" -> "Americano"
                                        "نسكافيه" -> "Nescafe"
                                        else -> "Unknown"
                                    },
                                    textAlign = TextAlign.Center,
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = if (item.withMilk) "Yes" else "No",
                                    textAlign = TextAlign.Center,
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(1f),
                                    text = item.sugarAmount.toString(),
                                    textAlign = TextAlign.Center,
                                )
                            }

                        }
                    }


                }
            }
        }
    }
}