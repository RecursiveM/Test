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
import com.ncgr.maqsaf.presentation.common.composable.AppBar
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.orderDetails.view.OrderDetailsActivity
import com.ncgr.maqsaf.presentation.user.composable.OrderDialog
import com.ncgr.maqsaf.presentation.user.composable.OrderNowButton
import com.ncgr.maqsaf.presentation.user.composable.UserScreenBody
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel
import com.ncgr.maqsaf.ui.theme.MAQSAFTheme
import com.ncgr.maqsaf.ui.theme.ScreenBackgroundColor
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
        val selectedZoneColor by viewModel.selectedZoneColor.collectAsState()
        val selectedItem by viewModel.selectedItem.collectAsState()
        val itemSelectionError by viewModel.itemSelectionError.collectAsState()
        val zoneColorSelectionError by viewModel.zoneColorSelectionError.collectAsState()
        val orderStatus by viewModel.orderStatus.collectAsState()
        val showOrderDialog by viewModel.showOrderDialog.collectAsState()
        val navigateToOrderDetailsActivity by viewModel.navigateToOrderDetails.collectAsState()

        if (navigateToOrderDetailsActivity) navigateToOrderDetailsActivity(selectedItem,selectedZoneColor)

        Scaffold(
            backgroundColor = ScreenBackgroundColor,
            scaffoldState = rememberScaffoldState(),
            modifier = modifier
                .fillMaxSize()
        ) { paddingValues ->

            OrderDialog(
                viewModel = viewModel,
                zoneColorSelectionError = zoneColorSelectionError,
                itemSelectionError = itemSelectionError,
                orderStatus = orderStatus,
                showOrderDialog = showOrderDialog
                )

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
                OrderNowButton(viewModel = viewModel)
            }
        }
    }

    private fun navigateToOrderDetailsActivity(selectedZoneColor: String, selectedItem: String) {
        if (selectedZoneColor == "" || selectedItem == "") return

        val intent = Intent(this, OrderDetailsActivity::class.java)
        intent.putExtra("Zone Color", selectedZoneColor)
        startActivity(intent)
        finish()
    }
}