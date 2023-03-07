package com.ncgr.maqsaf.presentation.common.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.R

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    withSignOutButton: Boolean = false,
    signOutFunction: () -> Unit = fun() {},
    reportsButtonFunction: () -> Unit = fun() {},
    refreshIcon: Boolean = false,
) {
    Surface(
        elevation = 10.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.weight(1f)
            ) {
                if (withSignOutButton)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize(0.5f)
                            .padding(horizontal = 10.dp)
                            .clickable {
                                signOutFunction()
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_exit_to_app_24),
                            contentDescription = "Sign out",
                            colorFilter = ColorFilter.tint(Color.Black),
                            modifier = Modifier
                                .fillMaxSize()
                                .rotate(180f)
                        )
                    }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.maqsaf_logo),
                        contentDescription = "Sign out",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }

            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier.weight(1f)
            ) {
                if (withSignOutButton && !refreshIcon)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize(0.5f)
                            .padding(horizontal = 10.dp)
                            .clickable {
                                reportsButtonFunction()
                            }
                    ) {
                        Text(
                            text = "!",
                            style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)
                        )


                    }
                else if (refreshIcon){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize(0.5f)
                            .padding(horizontal = 10.dp)
                            .clickable {
                                reportsButtonFunction()
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_refresh_24),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.Black),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }

        }
    }
}