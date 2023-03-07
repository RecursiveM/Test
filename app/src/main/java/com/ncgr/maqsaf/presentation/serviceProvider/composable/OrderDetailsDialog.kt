package com.ncgr.maqsaf.presentation.serviceProvider.composable

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.data.remote.model.OrderListItemDto
import com.ncgr.maqsaf.presentation.serviceProvider.viewModel.ServiceProviderViewModel
import com.ncgr.maqsaf.ui.theme.Green
import com.ncgr.maqsaf.ui.theme.Red

@Composable
fun OrderDetailsDialog(
    modifier: Modifier = Modifier,
    viewModel: ServiceProviderViewModel,
    orderListItem: OrderListItemDto?,
    orderListItemIndex: Int,
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
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                        modifier =
                        Modifier
                            .fillMaxSize()
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

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            Image(
                                painter = painterResource(
                                    id =
                                    when (orderListItem!!.have[orderListItemIndex].item.id) {
                                        "263256b9-e00b-4f1e-99f2-5d09152d5fc6" -> R.drawable.water_bottle_free_png_2
                                        "1bf93ba4-2021-4407-96b2-5b0e34bf1104" -> R.drawable.tea
                                        else -> R.drawable.coffee
                                    }
                                ),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxSize(if (orderListItem.have[orderListItemIndex].item.id == "1bf93ba4-2021-4407-96b2-5b0e34bf1104") 0.8f else 1f)
                            )
                        }

                        when (orderListItem!!.have[orderListItemIndex].item.type) {
                            "water" -> {

                                Spacer(modifier = Modifier.height(100.dp))

                                Box(
                                    contentAlignment = Alignment.Center, modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Text(
                                        text = "No Details for this order",
                                        style = TextStyle(fontSize = 20.sp)
                                    )
                                }
                            }
                            "coffee" -> {
                                Spacer(modifier = Modifier.height(20.dp))

                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.coffee_1),
                                        contentDescription = "Coffee",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Spacer(modifier = Modifier.width(50.dp))
                                    Text(
                                        text = when (orderListItem.have[orderListItemIndex].type) {
                                            "ماء" -> "Water"
                                            "شاهي" -> "Regular Tea"
                                            "قهوة" -> "Regular Coffee"
                                            "حبق" -> "Basil Tea"
                                            "نعناع" -> "Mint Tea"
                                            "امريكية" -> "Americano"
                                            "نسكافيه" -> "Nescafe"
                                            else -> "Unknown"
                                        },
                                    )
                                }

                                Spacer(modifier = Modifier.height(50.dp))

                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.sugar_cube_1),
                                        contentDescription = "sugar cube",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Spacer(modifier = Modifier.width(50.dp))
                                    Text(
                                        text = orderListItem.have[orderListItemIndex].sugarAmount.toString(),
                                    )
                                }

                                Spacer(modifier = Modifier.height(50.dp))

                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.milk_1),
                                        contentDescription = "milk",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Spacer(modifier = Modifier.width(50.dp))
                                    Text(
                                        text = if (orderListItem.have[orderListItemIndex].withMilk) "Yes" else "No",
                                        color = if (orderListItem.have[orderListItemIndex].withMilk) Green else Red
                                    )
                                }
                            }
                            else -> {
                                Spacer(modifier = Modifier.height(20.dp))

                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.mint),
                                        contentDescription = "Coffee",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Spacer(modifier = Modifier.width(50.dp))
                                    Text(
                                        text = if (orderListItem.have[orderListItemIndex].type == "نعناع") "Yes" else "No",
                                        color = if (orderListItem.have[orderListItemIndex].type == "نعناع") Green else Red,
                                    )
                                }

                                Spacer(modifier = Modifier.height(50.dp))

                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.basil),
                                        contentDescription = "sugar cube",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Spacer(modifier = Modifier.width(50.dp))
                                    Text(
                                        text = if (orderListItem.have[orderListItemIndex].type == "حبق") "Yes" else "No",
                                        color = if (orderListItem.have[orderListItemIndex].type == "حبق") Green else Red,
                                    )
                                }

                                Spacer(modifier = Modifier.height(50.dp))

                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.sugar_cube_1),
                                        contentDescription = "sugar cube",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Spacer(modifier = Modifier.width(50.dp))
                                    Text(
                                        text = orderListItem.have[orderListItemIndex].sugarAmount.toString(),
                                    )
                                }

                                Spacer(modifier = Modifier.height(50.dp))

                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.milk_1),
                                        contentDescription = "milk",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Spacer(modifier = Modifier.width(50.dp))
                                    Text(
                                        text = if (orderListItem.have[orderListItemIndex].withMilk) "Yes" else "No",
                                        color = if (orderListItem.have[orderListItemIndex].withMilk) Green else Red
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}