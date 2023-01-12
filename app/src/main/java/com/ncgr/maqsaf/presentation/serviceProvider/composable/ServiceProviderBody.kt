package com.ncgr.maqsaf.presentation.serviceProvider.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.presentation.serviceProvider.viewModel.ServiceProviderViewModel

@Composable
fun ServiceProviderBody(
    modifier: Modifier = Modifier,
    viewModel: ServiceProviderViewModel,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(3.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Orders", style = TextStyle(fontSize = 30.sp))
                RefreshButton(viewModel = viewModel, modifier = Modifier.width(50.dp))
            }
            Spacer(modifier = Modifier.height(3.dp))
            Divider(color = Color.Gray, thickness = 3.dp)

            OrderLazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentSize(),
                viewModel = viewModel
            )
        }
    }
}