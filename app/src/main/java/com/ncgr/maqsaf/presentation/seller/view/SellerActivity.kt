package com.ncgr.maqsaf.presentation.seller.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.presentation.seller.viewModel.SellerViewModel
import com.ncgr.maqsaf.ui.theme.MAQSAFTheme
import com.ncgr.maqsaf.ui.theme.screenBackgroundColor
import com.ncgr.maqsaf.ui.theme.toolbarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellerActivity : AppCompatActivity() {

    private val viewModel: SellerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAQSAFTheme {
                SellerScreen()
            }
        }
    }


    @Composable
    fun SellerScreen(
        modifier: Modifier = Modifier,
    ) {
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {


                    }
                }
            }
        }
    }
}