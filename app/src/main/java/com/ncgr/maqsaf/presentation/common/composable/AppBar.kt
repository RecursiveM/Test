package com.ncgr.maqsaf.presentation.common.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.ui.theme.Red
import com.ncgr.maqsaf.ui.theme.ToolbarColor

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    withSignOutButton: Boolean = false,
    signOutFunction: () -> Unit = fun() {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(ToolbarColor)
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            if (withSignOutButton)
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(10))
                            .background(Red)
                            .clickable {
                                signOutFunction()
                            }
                            .padding(2.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_exit_to_app_24),
                            contentDescription = "Sign out",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

            Image(
                painter = painterResource(id = R.drawable.ncgr_logo),
                contentDescription = "NCGR logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
            )
        }
    }
}