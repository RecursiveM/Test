package com.ncgr.maqsaf.presentation.orderDetails.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ncgr.maqsaf.presentation.common.composable.AppBar
import com.ncgr.maqsaf.presentation.orderDetails.composable.OrderDetailsBody
import com.ncgr.maqsaf.presentation.orderDetails.viewModel.OrderDetailsViewModel
import com.ncgr.maqsaf.ui.theme.MAQSAFTheme
import com.ncgr.maqsaf.ui.theme.ScreenBackgroundColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailsActivity : AppCompatActivity() {

    private val viewModel: OrderDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.changeZoneColor(intent.getStringExtra("Zone Color")!!)

            MAQSAFTheme {
                OrderDetailsScreen()
            }
        }
    }

    @Composable
    fun OrderDetailsScreen(
        modifier: Modifier = Modifier,
    ) {
        Scaffold(
            backgroundColor = ScreenBackgroundColor,
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
                AppBar()

                OrderDetailsBody(viewModel = viewModel)
            }
        }
    }
}