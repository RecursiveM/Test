package com.ncgr.maqsaf.presentation.user.view

import android.content.Intent
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ncgr.maqsaf.presentation.common.composable.AppBar
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.details.view.OrderDetailsActivity
import com.ncgr.maqsaf.presentation.user.composable.OrderNowButton
import com.ncgr.maqsaf.presentation.user.composable.UserScreenBody
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel
import com.ncgr.maqsaf.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAQSAFTheme {
                UserScreen()
            }
        }
    }

    @Composable
    fun UserScreen(
        modifier: Modifier = Modifier,
    ) {
        val itemList by viewModel.itemList.collectAsState(Resource.Loading())
        val selectedZoneColor by viewModel.selectedZoneColor.collectAsState("")
        val selectedItem by viewModel.selectedItem.collectAsState("")

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

                //Body
                UserScreenBody(
                    modifier = Modifier.weight(1f),
                    selectedZoneColor,
                    itemList,
                    selectedItem,
                    viewModel
                )

                //Order Now Button
                OrderNowButton(navigateToOrderDetailsActivity = {
                    navigateToOrderDetailsActivity(
                        selectedZoneColor,
                        selectedItem
                    )
                })
            }
        }
    }

    private fun navigateToOrderDetailsActivity(selectedZoneColor: String, selectedItem: String) {
        if (selectedZoneColor == "" || selectedItem == "") return

        val intent = Intent(this, OrderDetailsActivity::class.java)
        intent.putExtra("Zone Color", selectedZoneColor)
        startActivity(intent)
    }
}