package com.ncgr.maqsaf.presentation.user.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.ncgr.maqsaf.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ncgr.maqsaf.domain.menu.model.Item
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    selectedItem: String,
    item: Item,
    viewModel: UserViewModel
) {
    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(20))
            .border(
                if (selectedItem == item.type)
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
                viewModel.changeItem(item.type)
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

    }
}