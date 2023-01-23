package com.ncgr.maqsaf.presentation.user.composable

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.data.remote.api.item.body.AddItem
import com.ncgr.maqsaf.domain.order.model.Item
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel
import com.ncgr.maqsaf.ui.theme.*

@Composable
fun UserScreenBody(
    modifier: Modifier = Modifier,
    selectedZoneColor: String,
    itemList: Resource<List<Item>>,
    selectedItems: List<AddItem>,
    viewModel: UserViewModel,
) {
    Box(
        modifier = modifier
    ) {
        when (itemList) {
            is Resource.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(color = Blue)
                }
            }
            is Resource.Success -> {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        ChooseZoneColor,
                        style = TextStyle(
                            fontSize = 20.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(state = rememberScrollState())
                            .padding(horizontal = 10.dp)

                    ) {
                        ZoneColorButton(
                            color = Blue,
                            strokeTriggerColor = "Blue",
                            selectedZoneColor = selectedZoneColor,
                            viewModel = viewModel
                        )
                        ZoneColorButton(
                            color = Green,
                            strokeTriggerColor = "Green",
                            selectedZoneColor = selectedZoneColor,
                            viewModel = viewModel
                        )
                        ZoneColorButton(
                            color = Yellow,
                            strokeTriggerColor = "Yellow",
                            selectedZoneColor = selectedZoneColor,
                            viewModel = viewModel
                        )
                        ZoneColorButton(
                            color = Red,
                            strokeTriggerColor = "Red",
                            selectedZoneColor = selectedZoneColor,
                            viewModel = viewModel
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        ChooseItem,
                        style = TextStyle(
                            fontSize = 20.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    MenuLazyGrid(
                        modifier = Modifier.weight(1f),
                        selectedItems = selectedItems,
                        viewModel = viewModel,
                        itemList = itemList.data,
                    )
                }
            }
            is Resource.Error -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                ) {
                    Text(
                        text = itemList.apiError.errorMessage,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}