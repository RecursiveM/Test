package com.ncgr.maqsaf.presentation.user.composable

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.data.remote.api.item.body.AddItem
import com.ncgr.maqsaf.data.utils.ItemIdsConstants
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel
import com.ncgr.maqsaf.ui.theme.Green
import com.ncgr.maqsaf.ui.theme.Red

@Composable
fun ItemDetailsDialog(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel,
) {
    val itemDetails by viewModel.itemDetails.collectAsState()
    val type by viewModel.drinkType.collectAsState()
    val withMilk by viewModel.withMilk.collectAsState()
    val sugarAmount by viewModel.sugarAmount.collectAsState()
    val showItemDetailsDialogError by viewModel.showItemDetailsDialogError.collectAsState()
    val orderList = viewModel.selectedItems.collectAsState().value

    OrderDialog(
        viewModel = viewModel,
        orderStatus = Resource.Error(ApiError(0, "لا يمكنك اختيار اكثر من منتجين في طلب واحد")),
        zoneColorSelectionError = "",
        itemSelectionError = "",
        showOrderDialog = showItemDetailsDialogError
    )

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
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .clip(CircleShape)
                            .background(Red)
                            .clickable {
                                viewModel.closeItemDetails()
                            }
                    ) {
                        Text(text = "رجوع", style = TextStyle(color = Color.White))
                    }
                }


                when (itemDetails) {
                    "tea" -> Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp)
                            .verticalScroll(rememberScrollState())
                    ) {

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                        ) {
                            Image(
                                contentScale = ContentScale.Fit,
                                painter = painterResource(id = R.drawable.tea),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = ":عدد ملاعق السكر",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                        ) {
                            Text(text = "5")
                            RadioButton(
                                selected = sugarAmount == 5,
                                onClick = { viewModel.changeSugarAmount(5) })
                            Text(text = "4")
                            RadioButton(
                                selected = sugarAmount == 4,
                                onClick = { viewModel.changeSugarAmount(4) })
                            Text(text = "3")
                            RadioButton(
                                selected = sugarAmount == 3,
                                onClick = { viewModel.changeSugarAmount(3) })
                            Text(text = "2")
                            RadioButton(
                                selected = sugarAmount == 2,
                                onClick = { viewModel.changeSugarAmount(2) })
                            Text(text = "1")
                            RadioButton(
                                selected = sugarAmount == 1,
                                onClick = { viewModel.changeSugarAmount(1) })
                            Text(text = "0")
                            RadioButton(
                                selected = sugarAmount == 0,
                                onClick = { viewModel.changeSugarAmount(0) })
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = ":اضافات مع الشاهي",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "حليب")
                            Checkbox(
                                checked = withMilk,
                                onCheckedChange = { viewModel.changeWithMilk() })
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = ":نوع الشاهي",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "نعناع")
                            RadioButton(
                                selected = type == "نعناع",
                                onClick = { viewModel.changeType("نعناع") },
                            )
                            Text(text = "حبق")
                            RadioButton(
                                selected = type == "حبق",
                                onClick = { viewModel.changeType("حبق") })
                        }

                        if (orderList.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = ":طلباتك الحاليه",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End
                            )

                            for (orderItem in orderList) {
                                Spacer(modifier = Modifier.height(5.dp))

                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(80.dp)
                                ) {
                                    Image(
                                        contentScale = ContentScale.Fit,
                                        painter = painterResource(
                                            id = when (orderItem.type) {
                                                "شاهي" -> R.drawable.tea
                                                "حبق" -> R.drawable.tea
                                                "نعناع" -> R.drawable.tea
                                                "قهوة" -> R.drawable.coffee
                                                "امريكية" -> R.drawable.coffee
                                                "نسكافيه" -> R.drawable.coffee
                                                else -> R.drawable.water_bottle_free_png_2
                                            }

                                        ),
                                        contentDescription = orderItem.type,
                                    )

                                    Text(text = "نوع المنتج : ${orderItem.type}")

                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .height(50.dp)
                                            .width(50.dp)
                                            .clip(CircleShape)
                                            .background(Red)
                                            .clickable {
                                                viewModel.deleteSelectedItem(orderItem)
                                            }
                                            .padding(2.dp)
                                    ) {
                                        Text(text = "حذف", style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold))
                                    }
                                }

                            }
                        }
                    }

                    "coffee" -> Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp)
                            .verticalScroll(rememberScrollState())
                    ) {

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            Image(
                                contentScale = ContentScale.Fit,
                                painter = painterResource(id = R.drawable.coffee),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = ":عدد ملاعق السكر",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                        ) {
                            Text(text = "5")
                            RadioButton(
                                selected = sugarAmount == 5,
                                onClick = { viewModel.changeSugarAmount(5) })
                            Text(text = "4")
                            RadioButton(
                                selected = sugarAmount == 4,
                                onClick = { viewModel.changeSugarAmount(4) })
                            Text(text = "3")
                            RadioButton(
                                selected = sugarAmount == 3,
                                onClick = { viewModel.changeSugarAmount(3) })
                            Text(text = "2")
                            RadioButton(
                                selected = sugarAmount == 2,
                                onClick = { viewModel.changeSugarAmount(2) })
                            Text(text = "1")
                            RadioButton(
                                selected = sugarAmount == 1,
                                onClick = { viewModel.changeSugarAmount(1) })
                            Text(text = "0")
                            RadioButton(
                                selected = sugarAmount == 0,
                                onClick = { viewModel.changeSugarAmount(0) })
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = ":اضافات مع القهوة",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "حليب")
                            Checkbox(
                                checked = withMilk,
                                onCheckedChange = { viewModel.changeWithMilk() })
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = ":نوع القهوة",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "نسكافيه")
                            RadioButton(
                                selected = type == "نسكافيه",
                                onClick = { viewModel.changeType("نسكافيه") },
                            )
                            Text(text = "امريكية")
                            RadioButton(
                                selected = type == "امريكية",
                                onClick = { viewModel.changeType("امريكية") },
                            )
                        }

                        if (orderList.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = ":طلباتك الحاليه",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End
                            )

                            for (orderItem in orderList) {
                                Spacer(modifier = Modifier.height(5.dp))

                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(80.dp)
                                ) {
                                    Image(
                                        contentScale = ContentScale.Fit,
                                        painter = painterResource(
                                            id = when (orderItem.type) {
                                                "شاهي" -> R.drawable.tea
                                                "حبق" -> R.drawable.tea
                                                "نعناع" -> R.drawable.tea
                                                "قهوة" -> R.drawable.coffee
                                                "امريكية" -> R.drawable.coffee
                                                "نسكافيه" -> R.drawable.coffee
                                                else -> R.drawable.water_bottle_free_png_2
                                            }

                                        ),
                                        contentDescription = orderItem.type,
                                    )

                                    Text(text = "نوع المنتج : ${orderItem.type}")

                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .height(50.dp)
                                            .width(50.dp)
                                            .clip(CircleShape)
                                            .background(Red)
                                            .clickable {
                                                viewModel.deleteSelectedItem(orderItem)
                                            }
                                            .padding(2.dp)
                                    ) {
                                        Text(text = "حذف", style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold))
                                    }
                                }

                            }
                        }
                    }
                    "water" -> Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp)
                            .verticalScroll(rememberScrollState())
                    ) {

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            Image(
                                contentScale = ContentScale.Fit,
                                painter = painterResource(id = R.drawable.water_bottle_free_png_2),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text(
                                text = "لا يوجد تعديلات خاصة بالماء",
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp, textAlign = TextAlign.Center),
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        if (orderList.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = ":طلباتك الحاليه",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End
                            )

                            for (orderItem in orderList) {
                                Spacer(modifier = Modifier.height(5.dp))

                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(80.dp)
                                ) {
                                    Image(
                                        contentScale = ContentScale.Fit,
                                        painter = painterResource(
                                            id = when (orderItem.type) {
                                                "شاهي" -> R.drawable.tea
                                                "حبق" -> R.drawable.tea
                                                "نعناع" -> R.drawable.tea
                                                "قهوة" -> R.drawable.coffee
                                                "امريكية" -> R.drawable.coffee
                                                "نسكافيه" -> R.drawable.coffee
                                                else -> R.drawable.water_bottle_free_png_2
                                            }

                                        ),
                                        contentDescription = orderItem.type,
                                    )

                                    Text(text = "نوع المنتج : ${orderItem.type}")

                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .height(50.dp)
                                            .width(50.dp)
                                            .clip(CircleShape)
                                            .background(Red)
                                            .clickable {
                                                viewModel.deleteSelectedItem(orderItem)
                                            }
                                            .padding(2.dp)
                                    ) {
                                        Text(text = "حذف", style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold))
                                    }
                                }

                            }
                        }
                    }
                }

                Box(contentAlignment = Alignment.Center,
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color(0xff183C69))
                        .clickable {
                            when (itemDetails) {
                                "tea" -> {
                                    viewModel.addItem(
                                        AddItem(
                                            order_id = orderList.size.toString(),
                                            type = when (type) {
                                                "" -> "شاهي"
                                                else -> type
                                            },
                                            withMilk = withMilk,
                                            sugar_amount = sugarAmount,
                                            item_id = ItemIdsConstants.TEA
                                        )
                                    )
                                }
                                "coffee" -> {
                                    viewModel.addItem(
                                        AddItem(
                                            order_id = orderList.size.toString(),
                                            type = when (type) {
                                                "" -> "قهوة"
                                                else -> type
                                            },
                                            withMilk = withMilk,
                                            sugar_amount = sugarAmount,
                                            item_id = ItemIdsConstants.COFFEE
                                        )
                                    )
                                }
                                "water" -> {
                                    viewModel.addItem(
                                        AddItem(
                                            order_id = orderList.size.toString(),
                                            type = "ماء",
                                            item_id = ItemIdsConstants.WATER
                                        )
                                    )
                                }
                            }
                        }
                ) {
                    Text(
                        text = "تأكيد", style = TextStyle(
                            color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp
                        )
                    )
                }
            }
        }
    }
}