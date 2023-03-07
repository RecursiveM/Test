package com.ncgr.maqsaf.presentation.serviceProviderTicket.view

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ncgr.maqsaf.presentation.common.composable.AppBar
import com.ncgr.maqsaf.presentation.serviceProviderTicket.addingTicket.view.AddTicketActivity
import com.ncgr.maqsaf.presentation.serviceProviderTicket.composable.ReportsBodyComposable
import com.ncgr.maqsaf.presentation.serviceProviderTicket.viewModel.TicketsViewModel
import com.ncgr.maqsaf.ui.theme.MAQSAFTheme
import com.ncgr.maqsaf.ui.theme.ScreenBackgroundColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TicketsActivity : AppCompatActivity() {

    private val viewModel: TicketsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAQSAFTheme {
                ReportsScreen(viewModel = viewModel)
            }
        }
    }

    @Composable
    fun ReportsScreen(
        modifier: Modifier = Modifier,
        viewModel: TicketsViewModel
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
                AppBar(refreshIcon = true, reportsButtonFunction = {
                    viewModel.getTicketList()
                })

                ReportsBodyComposable(viewModel = viewModel, navigateToAddTicket = {navigateToAddTicket()})
            }
        }
    }

    private fun navigateToAddTicket(){
        intent = Intent(this, AddTicketActivity::class.java)
        startActivity(intent)
    }
}