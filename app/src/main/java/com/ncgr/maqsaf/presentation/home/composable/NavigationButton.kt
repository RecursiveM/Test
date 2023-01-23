package com.ncgr.maqsaf.presentation.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
) {
    Surface(
        elevation = 20.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    onClick()
                }
                .padding(10.dp)
        ) {
            Text(
                text = title,
                style = TextStyle(
                    textDirection = TextDirection.Rtl,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                    ),

                )
        }
    }

}