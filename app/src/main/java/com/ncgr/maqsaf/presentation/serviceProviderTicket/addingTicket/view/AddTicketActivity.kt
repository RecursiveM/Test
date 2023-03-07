package com.ncgr.maqsaf.presentation.serviceProviderTicket.addingTicket.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.presentation.common.composable.AppBar
import com.ncgr.maqsaf.presentation.serviceProviderTicket.addingTicket.composable.AddingTicketsDialog
import com.ncgr.maqsaf.presentation.serviceProviderTicket.addingTicket.viewModel.AddTicketsViewModel
import com.ncgr.maqsaf.ui.theme.MAQSAFTheme
import com.ncgr.maqsaf.ui.theme.ScreenBackgroundColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTicketActivity : AppCompatActivity() {

    private val viewModel: AddTicketsViewModel by viewModels()

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
        viewModel: AddTicketsViewModel
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
                val finishActivity by viewModel.finishActivity.collectAsState()
                if (finishActivity) navigateBack()

                //Custom TopBar
                AppBar()

                AddingTicketsDialog(viewModel = viewModel)

                val description by viewModel.description.collectAsState()
                val priority by viewModel.priority.collectAsState()

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = modifier.fillMaxSize()
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(text = "Add New Ticket", style = TextStyle(fontSize = 30.sp))
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    val listItems = arrayOf("1", "2", "3", "4")
                    var expanded by remember {
                        mutableStateOf(false)
                    }

                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth(0.5f)
                            .height(50.dp)
                            .clip(RoundedCornerShape(30))
                            .border(1.dp, color = Color.Black, shape = RoundedCornerShape(30))
                            .clickable {
                                expanded = true
                            }
                    ) {
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier.fillMaxSize().padding(horizontal = 15.dp)
                        ) {
                            Text(text = "Priority :$priority")
                        }
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            listItems.forEachIndexed { _, itemValue ->
                                DropdownMenuItem(
                                    onClick = {
                                        expanded = false
                                        viewModel.setPriority(itemValue)
                                    },
                                ) {
                                    Text(text = itemValue)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = description ?: "",
                        placeholder = { Text("Description") },
                        onValueChange = { viewModel.setDescription(it) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        shape = RoundedCornerShape(30),
                        maxLines = 7,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(RoundedCornerShape(30))
                                .background(Color(0xff183C69))
                                .clickable {
                                    viewModel.addTicket()
                                }
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "Add",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun navigateBack() {
        finish()
    }
}