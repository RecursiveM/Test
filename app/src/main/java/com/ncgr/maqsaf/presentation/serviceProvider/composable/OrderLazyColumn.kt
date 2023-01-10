package com.ncgr.maqsaf.presentation.serviceProvider.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ncgr.maqsaf.presentation.serviceProvider.viewModel.ServiceProviderViewModel

@Composable
fun OrderLazyColumn(
    modifier: Modifier = Modifier,
    viewModel: ServiceProviderViewModel,
) {
    val orderList = viewModel.orderList.collectAsState()
    val itemList = viewModel.itemList.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        itemsIndexed(orderList.value) { index, order ->
            OrderWidget(
                order = order,
                item = itemList.value[index]
            )
        }
    }
}