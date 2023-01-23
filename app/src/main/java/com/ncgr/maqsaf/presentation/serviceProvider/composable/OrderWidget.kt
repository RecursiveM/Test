package com.ncgr.maqsaf.presentation.serviceProvider.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ncgr.maqsaf.data.remote.model.OrderListItemDto
import com.ncgr.maqsaf.domain.order.utils.OrderState
import com.ncgr.maqsaf.presentation.serviceProvider.viewModel.ServiceProviderViewModel
import com.ncgr.maqsaf.ui.theme.Blue
import com.ncgr.maqsaf.ui.theme.Green
import com.ncgr.maqsaf.ui.theme.Red
import com.ncgr.maqsaf.ui.theme.Yellow

@Composable
fun OrderWidget(
    modifier: Modifier = Modifier,
    orderListItem: OrderListItemDto,
    viewModel: ServiceProviderViewModel,
) {
    var showRejectDialog by remember { mutableStateOf(false) }

    Surface(
        shape = RoundedCornerShape(20),
        elevation = 10.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(10.dp)
    ) {

        Box(contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .background(
                    when (orderListItem.zoneColor) {
                        "Yellow" -> Yellow
                        "Blue" -> Blue
                        "Green" -> Green
                        else -> Red
                    }
                )
                .clickable {
                    viewModel.openOrderDetailsDialog(orderListItem)
                }
        ) {

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                if (showRejectDialog)
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(20))
                            .background(Color.White)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp)
                        ) {
                            Text(
                                text = "Are you sure do you want to reject this order ?",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                            )

                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(100.dp)
                                        .clip(RoundedCornerShape(10))
                                        .background(Color.Green)
                                        .clickable {
                                            showRejectDialog = false
                                        }
                                ) {
                                    Text(
                                        text = "No",
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                        ),
                                    )

                                }

                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(100.dp)
                                        .clip(RoundedCornerShape(10))
                                        .background(Color.Red)
                                        .clickable {
                                            viewModel.changeOrderState(
                                                orderUid = orderListItem.id,
                                                orderState = OrderState.Rejected
                                            )
                                        }
                                ) {
                                    Text(
                                        text = "Yes",
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                        ),
                                    )

                                }
                            }
                        }
                    }
                LazyRow(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
                    .background(Color.White)
                ){
                    itemsIndexed(orderListItem.have) { _, res ->
                        AsyncImage(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            model = res.item.itemImage,
                            contentDescription = "",
                            contentScale = ContentScale.Inside,
                        )
                    }
                }
                Text(
                    text = orderListItem.orderNumber.toString(),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 30.sp,
                        shadow = Shadow(
                            Color.Black,
                            blurRadius = 10f
                        )
                    ),
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    if (orderListItem.orderState == OrderState.Accepted.name) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(50.dp)
                                .width(100.dp)
                                .clip(RoundedCornerShape(10))
                                .background(Color.Green)
                                .border(BorderStroke(3.dp, Color.Black))
                                .clickable {
                                    viewModel.changeOrderState(
                                        orderUid = orderListItem.id,
                                        orderState = OrderState.Finished
                                    )
                                }
                        ) {
                            Text(
                                text = "Finish",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    shadow = Shadow(Color.Black, blurRadius = 2f)
                                ),
                            )

                        }
                    } else {

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(50.dp)
                                .width(100.dp)
                                .clip(RoundedCornerShape(10))
                                .background(Color.Red)
                                .border(BorderStroke(3.dp, Color.Black))
                                .clickable {
                                    showRejectDialog = true
                                }
                        ) {
                            Text(
                                text = "Reject",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    shadow = Shadow(Color.Black, blurRadius = 2f)
                                ),
                            )

                        }

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(50.dp)
                                .width(100.dp)
                                .clip(RoundedCornerShape(10))
                                .background(Color.Green)
                                .border(BorderStroke(3.dp, Color.Black))
                                .clickable {
                                    viewModel.changeOrderState(
                                        orderUid = orderListItem.id,
                                        orderState = OrderState.Accepted
                                    )
                                }
                        ) {
                            Text(
                                text = "Accept",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    shadow = Shadow(Color.Black, blurRadius = 2f)
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}