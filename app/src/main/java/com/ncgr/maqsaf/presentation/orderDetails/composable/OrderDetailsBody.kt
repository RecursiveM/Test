package com.ncgr.maqsaf.presentation.orderDetails.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.presentation.common.utils.Resource
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
    val myOrders = viewModel.myOrders.collectAsState().value

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        when (myOrders) {
            is Resource.Loading -> {
                CircularProgressIndicator(color = Blue)
            }
            is Resource.Success -> {

                LazyRow(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(myOrders.data[0].have) { _, item ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = modifier.padding(end = 10.dp)
                        ) {
                            Surface(
                                elevation = 10.dp,
                                shape = RoundedCornerShape(30),
                                modifier = modifier.padding(10.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.BottomEnd,
                                    modifier = Modifier
                                        .height(150.dp)
                                        .width(250.dp)
                                        .clip(RoundedCornerShape(30))
                                        .background(
                                            when (myOrders.data[0].zoneColor) {
                                                "Yellow" -> Yellow
                                                "Blue" -> Blue
                                                "Green" -> Green
                                                else -> Red
                                            }
                                        )
                                        .padding(10.dp)
                                ) {

                                    Box(
                                        contentAlignment = Alignment.BottomCenter,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Text(
                                            text = myOrders.data[0].orderNumber.toString(),
                                            style = TextStyle(
                                                color = Color.White,
                                                fontSize = 20.sp,
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
                                    .width(250.dp)
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = when (item.itemId) {
                                            "263256b9-e00b-4f1e-99f2-5d09152d5fc6" -> R.drawable.water_bottle_free_png_2
                                            "1bf93ba4-2021-4407-96b2-5b0e34bf1104" -> R.drawable.tea
                                            else -> R.drawable.coffee
                                        }
                                    ),
                                    contentDescription = item.type,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .width(200.dp)
                                        .height(200.dp)
                                )
                            }
                        }

                    }
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color(0xff183C69))
                        .padding(10.dp)
                ) {
                    Text(
                        text = myOrders.data[0].orderState,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))


                if (myOrders.data.first().orderState != "Pending") {
                    Spacer(modifier = Modifier.height(10.dp))

                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .width(80.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .border(2.dp, color = Color.Black, shape = RoundedCornerShape(30.dp))
                            .background(Color.White)
                            .clickable {
                                viewModel.navToUserActivity()
                            }
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "رجوع",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )

                    }
                }
            }
            is Resource.Error -> {
                Text(text = myOrders.apiError.errorMessage)
            }
        }
    }
}