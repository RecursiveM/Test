package com.ncgr.maqsaf.presentation.user.composable

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.domain.menu.model.Item
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel
import com.ncgr.maqsaf.ui.theme.*

@Composable
fun UserScreenBody(
    modifier: Modifier = Modifier,
    selectedZoneColor: String,
    itemList: Resource<List<Item>>,
    selectedItem: String,
    viewModel: UserViewModel,
) {
    Box(
        modifier = modifier
    ) {
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
                selectedItem = selectedItem,
                viewModel = viewModel,
                itemList = itemList,
            )
        }
    }
}