package com.ncgr.maqsaf.presentation.user.view

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ncgr.maqsaf.domain.order.model.Order
import com.ncgr.maqsaf.presentation.common.composable.AppBar
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.home.view.HomeActivity
import com.ncgr.maqsaf.presentation.orderDetails.view.OrderDetailsActivity
import com.ncgr.maqsaf.presentation.tickets.view.TicketsActivity
import com.ncgr.maqsaf.presentation.user.composable.ItemDetailsDialog
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
        val selectedItem by viewModel.selectedItems.collectAsState()
        val itemSelectionError by viewModel.itemSelectionError.collectAsState()
        val zoneColorSelectionError by viewModel.zoneColorSelectionError.collectAsState()
        val orderStatus by viewModel.orderStatus.collectAsState()
        val showOrderDialog by viewModel.showOrderDialog.collectAsState()
        val orderDetails by viewModel.orderDetails.collectAsState(Order("", 0, "",""))

        val navigateToOrderDetailsActivity by viewModel.navigateToOrderDetails.collectAsState()
        if (navigateToOrderDetailsActivity) navigateToOrderDetailsActivity()

        val navigateBackToHome by viewModel.navigateBackToHome.collectAsState()
        if (navigateBackToHome) navigateBackToHome()

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
                AppBar(
                    withSignOutButton = true,
                    signOutFunction = { viewModel.signOut() },
                    reportsButtonFunction = { navigateToReportsActivity() }
                )

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

        val openItemDetails by viewModel.openItemDetails.collectAsState()
        if (openItemDetails) ItemDetailsDialog(viewModel = viewModel)

    }

    private fun navigateToOrderDetailsActivity() {
        val intent = Intent(this, OrderDetailsActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    private fun navigateBackToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
    private fun navigateToReportsActivity() {
        val intent = Intent(this, TicketsActivity::class.java)
        startActivity(intent)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}