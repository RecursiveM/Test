package com.ncgr.maqsaf.presentation.serviceProvider.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.presentation.serviceProvider.viewModel.ServiceProviderViewModel

@Composable
fun RefreshButton(
    modifier: Modifier = Modifier,
    viewModel: ServiceProviderViewModel,
) {
    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .background(Color(0xff183C69))
            .clickable {
                viewModel.refreshOrders()
            }
            .padding(5.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_refresh_24),
            contentDescription = "Refresh button"
        )
    }
}