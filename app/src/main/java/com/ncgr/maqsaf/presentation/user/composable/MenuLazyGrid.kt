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
import com.ncgr.maqsaf.data.remote.api.item.body.AddItem
import com.ncgr.maqsaf.domain.order.model.Item
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel
import com.ncgr.maqsaf.ui.theme.Blue

@Composable
fun MenuLazyGrid(
    modifier: Modifier = Modifier,
    itemList: List<Item>,
    selectedItems: List<AddItem>,
    viewModel: UserViewModel,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        itemsIndexed(itemList) { _, item ->
            MenuItem(
                selectedItems = selectedItems,
                item = item,
                viewModel = viewModel,
            )
        }
    }

}