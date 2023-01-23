package com.ncgr.maqsaf.presentation.user.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.ncgr.maqsaf.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ncgr.maqsaf.data.remote.api.item.body.AddItem
import com.ncgr.maqsaf.domain.order.model.Item
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    selectedItems: List<AddItem>,
    item: Item,
    viewModel: UserViewModel
) {
    val selected by viewModel.checkIfItemSelected(item = item).collectAsState()

    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(20))
            .border(
                if (selected != 0)
                    BorderStroke(
                        3.dp,
                        Color.Black
                    )
                else
                    BorderStroke(
                        0.dp,
                        Color.Transparent
                    ),
                shape = RoundedCornerShape(20)
            )
            .clickable {
                viewModel.openItemDetails(item.type)
            }
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.type,
            contentScale = ContentScale.Inside,
            error = painterResource(id = R.drawable.ic_broken_image),
            modifier = Modifier
                .fillMaxSize()
        )

        Box(contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()) {
            Text(text = "العدد $selected")
        }
    }
}