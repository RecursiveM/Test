package com.ncgr.maqsaf.presentation.user.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    item: ListItem,
    viewModel: UserViewModel
) {
    val selected by viewModel.checkIfItemSelected(item = item).collectAsState()

    Box(contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        Surface(
            elevation = 10.dp,
            shape = RoundedCornerShape(30),
            modifier = modifier.padding(10.dp)
        ) {
            Box(contentAlignment = Alignment.BottomEnd,
                modifier = Modifier
                    .height(150.dp)
                    .width(250.dp)
                    .clip(RoundedCornerShape(30))
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
                        shape = RoundedCornerShape(30)
                    )
                    .clickable {
                        viewModel.openItemDetails(item.type)
                    }
                    .padding(10.dp)
            ) {

                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "العدد $selected")
                }
            }


        }
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .height(350.dp).width(250.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = item.type,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
            )
        }
    }

}