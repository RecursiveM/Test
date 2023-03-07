package com.ncgr.maqsaf.presentation.serviceProvider.view

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
import com.ncgr.maqsaf.presentation.home.view.HomeActivity
import com.ncgr.maqsaf.presentation.serviceProviderTicket.view.TicketsActivity
import com.ncgr.maqsaf.presentation.serviceProvider.composable.OrderDetailsDialog
import com.ncgr.maqsaf.presentation.serviceProvider.composable.ServiceProviderBody
import com.ncgr.maqsaf.presentation.serviceProvider.composable.SignOutDialog
import com.ncgr.maqsaf.presentation.serviceProvider.viewModel.ServiceProviderViewModel
import com.ncgr.maqsaf.ui.theme.MAQSAFTheme
import com.ncgr.maqsaf.ui.theme.ScreenBackgroundColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServiceProviderActivity : AppCompatActivity() {

    private val viewModel: ServiceProviderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAQSAFTheme {
                ServiceProviderScreen()
            }
        }
    }

    @Composable
    fun ServiceProviderScreen(
        modifier: Modifier = Modifier,
    ) {
        val signOutStatus by viewModel.signOutStatus.collectAsState()
        val showSignOutDialog by viewModel.showSignOutDialog.collectAsState()

        val navigateBackToHome by viewModel.navigateBackToHome.collectAsState()
        if (navigateBackToHome) navigateBackToHome()

        SignOutDialog(
            viewModel = viewModel,
            zoneColorSelectionError = "",
            itemSelectionError = "",
            orderStatus = signOutStatus,
            showOrderDialog = showSignOutDialog
        )

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
                AppBar(
                    withSignOutButton = true,
                    signOutFunction = { viewModel.signOut() },
                    reportsButtonFunction = { navigateToReportsActivity() }
                )

                ServiceProviderBody(viewModel = viewModel)
            }
        }

        val openOrderDetails by viewModel.openOrderDetails.collectAsState()
        val orderListItem = viewModel.orderListItem.collectAsState()
        val orderListItemIndex = viewModel.orderListItemIndex.collectAsState()
        if (openOrderDetails) OrderDetailsDialog(viewModel = viewModel, orderListItem= orderListItem.value, orderListItemIndex = orderListItemIndex.value)
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
}