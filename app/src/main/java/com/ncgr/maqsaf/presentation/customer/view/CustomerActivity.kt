package com.ncgr.maqsaf.presentation.customer.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import coil.compose.AsyncImage
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.presentation.customer.viewModel.CustomerViewModel
import com.ncgr.maqsaf.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerActivity : AppCompatActivity() {

    private val viewModel: CustomerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAQSAFTheme {
                CustomerScreen()
            }
        }
    }

    @Composable
    fun CustomerScreen(
        modifier: Modifier = Modifier,
    ) {
        val itemList by viewModel.itemList.collectAsState()
        val zoneColor by viewModel.zoneColor.collectAsState()
        val selectedItem by viewModel.selectedItem.collectAsState("")

        Scaffold(
            backgroundColor = screenBackgroundColor,
            scaffoldState = rememberScaffoldState(),
            modifier = modifier
                .fillMaxSize()
        ) { paddingValues ->
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                //Custom TopBar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(toolbarColor)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
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

                //Body
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            getString(R.string.choose_zone_color),
                            style = TextStyle(
                                fontSize = 20.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(state = rememberScrollState())
                                .padding(horizontal = 10.dp)

                        ) {
                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(50.dp)
                                    .background(blue)
                                    .border(
                                        BorderStroke(
                                            if (zoneColor == "Blue") 3.dp else 0.dp,
                                            Color.Black
                                        )
                                    )
                                    .clickable {
                                        viewModel.changeZoneColor(blue)
                                    }
                            )
                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(50.dp)
                                    .background(green)
                                    .border(
                                        BorderStroke(
                                            if (zoneColor == "Green") 3.dp else 0.dp,
                                            Color.Black
                                        )
                                    )
                                    .clickable {
                                        viewModel.changeZoneColor(green)
                                    }
                            )
                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(50.dp)
                                    .background(yellow)
                                    .border(
                                        BorderStroke(
                                            if (zoneColor == "Yellow") 3.dp else 0.dp,
                                            Color.Black
                                        )
                                    )
                                    .clickable {
                                        viewModel.changeZoneColor(yellow)
                                    }
                            )
                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(50.dp)
                                    .background(red)
                                    .border(
                                        BorderStroke(
                                            if (zoneColor == "Red") 3.dp else 0.dp,
                                            Color.Black
                                        )
                                    )
                                    .clickable {
                                        viewModel.changeZoneColor(red)
                                    }
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            getString(R.string.choose_item),
                            style = TextStyle(
                                fontSize = 20.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            items(itemList.size) { index ->
                                Box(contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(250.dp)
                                        .padding(10.dp)
                                        .clip(RoundedCornerShape(20))
                                        .border(
                                            if (selectedItem == itemList[index].type)
                                            BorderStroke(
                                                 3.dp,
                                                Color.Black
                                            )
                                            else
                                                BorderStroke(
                                                    0.dp,
                                                    Color.Transparent
                                                ) ,
                                            shape = RoundedCornerShape(20)
                                        )
                                        .clickable {
                                            viewModel.changeItem(itemList[index].type)
                                        }
                                ) {
                                    AsyncImage(
                                        model = itemList[index].imageUrl,
                                        contentDescription = itemList[index].type,
                                        contentScale = ContentScale.Inside,
                                        modifier = Modifier
                                            .fillMaxSize()
                                    )
                                }
                            }
                        }
                    }
                }

                //Order Now Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(green)
                            .clickable {}
                            .border(BorderStroke(2.dp, Color.Black))
                    ) {
                        Text(
                            text = getString(R.string.order_now),
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }
    }
}