package com.ncgr.maqsaf.presentation.userTicket.addingTicket.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.userTicket.addingTicket.viewModel.UserAddTicketsViewModel
import com.ncgr.maqsaf.ui.theme.Blue

@Composable
fun UserAddingTicketsDialog(
    viewModel: UserAddTicketsViewModel,
) {
    val addingTicketStatus = viewModel.addingTicketStatus.collectAsState().value
    val openDialog by viewModel.openAddingTicketDialog.collectAsState()
    val descriptionError by viewModel.descriptionError.collectAsState()
    val priorityError by viewModel.priorityError.collectAsState()

    if (!openDialog) return

    when (addingTicketStatus) {
        is Resource.Loading -> {
            AlertDialog(
                onDismissRequest = {
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "جاري الإنتظار",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                text = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Blue)
                    }
                },
                buttons = {}
            )
        }
        is Resource.Success -> {
            AlertDialog(
                onDismissRequest = {
                },
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_cloud_done_24),
                            contentDescription = "",
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "نجحت الإضافة")
                    }
                },
                text = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "نجحت الإضافة",
                            fontSize = 20.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                buttons = {}
            )
        }
        is Resource.Error -> {
            if (addingTicketStatus.apiError.errorMessage == "Error form submission") {
                AlertDialog(
                    onDismissRequest = {
                        viewModel.closeTicketsDialog()
                    },
                    title = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_warning_24),
                                contentDescription = "Something went wrong",
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "حدث خطأ")
                        }
                    },
                    text = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${descriptionError ?: ""}\n${priorityError ?: ""}",
                                textAlign = TextAlign.End
                            )
                        }
                    },
                    buttons = {}
                )
            } else {
                AlertDialog(
                    onDismissRequest = {
                        viewModel.closeTicketsDialog()
                    },
                    title = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_warning_24),
                                contentDescription = "Something went wrong",
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "حدث خطأ")
                        }
                    },
                    text = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = addingTicketStatus.apiError.errorMessage)
                        }
                    },
                    buttons = {}
                )
            }
        }
    }
}