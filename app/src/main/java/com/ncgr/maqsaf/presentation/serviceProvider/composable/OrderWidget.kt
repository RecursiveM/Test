package com.ncgr.maqsaf.presentation.serviceProvider.composable

import androidx.compose.foundation.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.R
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

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Surface(
            elevation = 10.dp,
            shape = RoundedCornerShape(30),
            modifier = Modifier
        ) {
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30))
                    .background(
                        when (orderListItem.zoneColor) {
                            "Yellow" -> Yellow
                            "Blue" -> Blue
                            "Green" -> Green
                            else -> Red
                        }
                    )

            ) {

                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = orderListItem.orderNumber.toString(),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth()
        ) {

            if (!showRejectDialog)
                LazyRow() {
                    itemsIndexed(orderListItem.have) { index, item ->
                        Image(
                            painter = painterResource(
                                id = when (item.item.id) {
                                    "263256b9-e00b-4f1e-99f2-5d09152d5fc6" -> R.drawable.water_bottle_free_png_2
                                    "1bf93ba4-2021-4407-96b2-5b0e34bf1104" -> R.drawable.tea
                                    else -> R.drawable.coffee
                                }
                            ),
                            contentDescription = item.type,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .width(150.dp)
                                .height(200.dp)
                                .clip(RoundedCornerShape(30))
                                .clickable {
                                    viewModel.openOrderDetailsDialog(orderListItem,index)
                                }
                        )
                    }
                }

            if (!showRejectDialog)
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 70.dp)
                    ) {
                        if (orderListItem.orderState == OrderState.Accepted.name) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(100.dp)
                                    .clip(RoundedCornerShape(30))
                                    .background(Color(0xff183C69))
                                    .border(
                                        BorderStroke(3.dp, Color.Black),
                                        shape = RoundedCornerShape(30)
                                    )
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
                                    .clip(RoundedCornerShape(30))
                                    .background(Color.White)
                                    .border(
                                        BorderStroke(1.dp, Color.Black),
                                        shape = RoundedCornerShape(30)
                                    )
                                    .clickable {
                                        showRejectDialog = true
                                    }
                            ) {
                                Text(
                                    text = "Reject",
                                    style = TextStyle(
                                        color = Red,
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
                                    .clip(RoundedCornerShape(30))
                                    .background(Color(0xff183C69))
                                    .border(
                                        BorderStroke(3.dp, Color.Black),
                                        shape = RoundedCornerShape(30)
                                    )
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

        if (showRejectDialog)
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(30))
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
                                .background(Green)
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
                                .background(Red)
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
    }
}