package com.ncgr.maqsaf.presentation.orderDetails.composable

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
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
        when (myOrders){
            is Resource.Loading ->{
                CircularProgressIndicator(color = Blue)
            }
            is Resource.Success -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(300.dp)
                        .background(
                            when (myOrders.data[0].zoneColor) {
                                "Yellow" -> Yellow
                                "Blue" -> Blue
                                "Green" -> Green
                                else -> Red
                            }
                        )
                ) {
                    Text(
                        (myOrders.data[0].orderNumber % 1000).toString(),
                        style = TextStyle(
                            color = Color.White, fontSize = 120.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(
                            when (myOrders.data[0].orderState) {
                                "Pending" -> Yellow
                                "Accepted" -> Green
                                "Rejected" -> Red
                                "Finished" -> Blue
                                else -> Red
                            }
                        )
                        .border(BorderStroke(5.dp, color = Color.Black))
                        .padding(10.dp)
                ){
                    Text(
                        myOrders.data[0].orderState,
                        style = TextStyle(
                            color = Color.White, fontSize = 40.sp, shadow = Shadow(Color.Black),
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .width(50.dp)
                        .height(50.dp)
                        .background(Color.Gray)
                        .border(BorderStroke(2.dp, color = Color.Black))
                        .clickable {
                            viewModel.getMyOrder()
                        }
                        .padding(10.dp)
                ){
                    Image(painter = painterResource(id = R.drawable.ic_baseline_refresh_24), contentDescription = "", modifier = Modifier.fillMaxSize())
                }

                if (myOrders.data.first().orderState != "Pending") {
                    Spacer(modifier = Modifier.height(10.dp))

                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .width(80.dp)
                            .height(50.dp)
                            .background(Color.Red)
                            .border(BorderStroke(2.dp, color = Color.Black))
                            .clickable {
                                viewModel.navToUserActivity()
                            }
                            .padding(10.dp)
                    ){
                        Text(text = "رجوع", style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold))

                    }
                }
            }
            is Resource.Error -> {
                Text(text = myOrders.apiError.errorMessage)
            }
        }
    }
}