package com.ncgr.maqsaf.presentation.serviceProviderTicket.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.serviceProviderTicket.viewModel.TicketsViewModel
import com.ncgr.maqsaf.ui.theme.Blue

@Composable
fun ReportsBodyComposable(
    modifier: Modifier = Modifier,
    viewModel: TicketsViewModel,
    navigateToAddTicket: () -> Unit
) {
    val ticketList = viewModel.ticketList.collectAsState(Resource.Loading()).value

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        when (ticketList) {
            is Resource.Loading -> {
                CircularProgressIndicator(color = Blue)
            }
            is Resource.Error -> {
                Text(
                    text = ticketList.apiError.errorMessage,
                    style = TextStyle(color = Color.Black, fontSize = 30.sp)
                )
            }
            is Resource.Success -> {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Tickets", style = TextStyle(fontSize = 30.sp))

                    LazyColumn(modifier = Modifier.weight(1f)) {
                        itemsIndexed(ticketList.data) { _, item ->
                            TicketItem(
                                id = item.id,
                                description = item.description,
                                respond = item.respond ?: "No respond Found",
                                priority = item.priority
                            )
                        }
                    }

                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .background(Color(0xff183C69))
                            .clickable { navigateToAddTicket() }
                    ) {
                        Text(
                            text = "Add New Ticket",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}