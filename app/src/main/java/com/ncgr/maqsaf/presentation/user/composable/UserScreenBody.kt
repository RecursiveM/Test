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
import com.ncgr.maqsaf.data.remote.api.item.body.AddItem
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel
import com.ncgr.maqsaf.ui.theme.*

data class ListItem(
    val id: String,
    val imageId: Int,
    val type: String,
)

@Composable
fun UserScreenBody(
    modifier: Modifier = Modifier,
    selectedZoneColor: String,
    selectedItems: List<AddItem>,
    viewModel: UserViewModel,
) {
    
    val itemList = listOf(
        ListItem(
            id = "263256b9-e00b-4f1e-99f2-5d09152d5fc6",
            imageId = com.ncgr.maqsaf.R.drawable.water_bottle_free_png_2,
            type = "water"
        ),
        ListItem(
            id = "1bf93ba4-2021-4407-96b2-5b0e34bf1104",
            imageId = com.ncgr.maqsaf.R.drawable.tea,
            type = "tea"
        ),
        ListItem(
            id = "2dc49c92-c31d-4b19-b52b-40e656f941df",
            imageId = com.ncgr.maqsaf.R.drawable.coffee,
            type = "coffee"
        )
    )

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
                selectedItems = selectedItems,
                viewModel = viewModel,
                itemList = itemList,
            )
        }
    }


}
