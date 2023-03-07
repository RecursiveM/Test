package com.ncgr.maqsaf.presentation.user.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ncgr.maqsaf.data.remote.api.item.body.AddItem
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel

@Composable
fun MenuLazyGrid(
    modifier: Modifier = Modifier,
    itemList: List<ListItem>,
    selectedItems: List<AddItem>,
    viewModel: UserViewModel,
) {
    LazyRow(horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        itemsIndexed(itemList) { _, item ->
            MenuItem(
                item = item,
                viewModel = viewModel,
            )
        }
    }

}