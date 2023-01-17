package com.ncgr.maqsaf.presentation.user.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.domain.order.model.Item
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel
import com.ncgr.maqsaf.ui.theme.Blue

@Composable
fun MenuLazyGrid(
    modifier: Modifier =Modifier,
    itemList: Resource<List<Item>>,
    selectedItem: String,
    viewModel: UserViewModel,
) {
    when (itemList) {
        is Resource.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
            ) {
                CircularProgressIndicator(color = Blue)
            }
        }
        is Resource.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier
            ) {
                itemsIndexed(itemList.data) { _, item ->
                    MenuItem(
                        selectedItem = selectedItem,
                        item = item,
                        viewModel = viewModel,
                    )
                }
            }
        }
        is Resource.Error -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.padding(20.dp)
            ) {
                Text(
                    text = itemList.apiError.errorMessage,
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}