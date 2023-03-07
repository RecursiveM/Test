package com.ncgr.maqsaf.presentation.home.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    icon: Int,
) {
    Surface(
        elevation = 3.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.height(400.dp).width(140.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp))
                .clickable {
                    onClick()
                }
                .padding(5.dp)
        ) {
            Row {
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            textDirection = TextDirection.Rtl,
                            fontSize = 20.sp,
                        ),
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Icon Button",
                    tint = Color(0xffAFBC52),
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.2f)
                )
            }
        }
    }

}