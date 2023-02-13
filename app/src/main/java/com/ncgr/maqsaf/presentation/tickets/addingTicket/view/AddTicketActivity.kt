package com.ncgr.maqsaf.presentation.tickets.addingTicket.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.ncgr.maqsaf.presentation.tickets.addingTicket.composable.AddingTicketsDialog
import com.ncgr.maqsaf.presentation.tickets.addingTicket.viewModel.AddTicketsViewModel
import com.ncgr.maqsaf.ui.theme.Blue
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
                    horizontalAlignment = Alignment.CenterHorizontally,
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

                    TextField(
                        value = description ?: "",
                        label = { Text("Description") },
                        placeholder = { Text("Your Description") },
                        onValueChange = { viewModel.setDescription(it) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = priority ?: "",
                        label = { Text("Priority") },
                        placeholder = { Text("Low, Medium, High") },
                        onValueChange = { viewModel.setPriority(it) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10))
                            .background(Blue)
                            .clickable {
                                viewModel.addTicket()
                            }
                            .padding(10.dp)) {
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

    private fun navigateBack() {
        finish()
    }
}