package com.ncgr.maqsaf.presentation.home.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.presentation.common.composable.AppBar
import com.ncgr.maqsaf.presentation.home.composable.CustomAlertDialog
import com.ncgr.maqsaf.presentation.home.composable.NavigationButton
import com.ncgr.maqsaf.presentation.home.viewModel.HomeViewModel
import com.ncgr.maqsaf.presentation.serviceProvider.view.ServiceProviderActivity
import com.ncgr.maqsaf.presentation.user.view.UserActivity
import com.ncgr.maqsaf.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAQSAFTheme {
                val openDialog = viewModel.openDialog.collectAsState()
                if (openDialog.value) CustomAlertDialog(viewModel = viewModel) { navigateToServiceProviderActivity() }

                HomeScreen()
            }
        }
    }

    @Composable
    private fun HomeScreen(
        modifier: Modifier = Modifier,
    ) {
        Scaffold(
            backgroundColor = ScreenBackgroundColor,
            scaffoldState = rememberScaffoldState(),
            modifier = modifier.fillMaxSize()
        ) { paddingValues ->
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                AppBar()
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = HomeWelcome, style = TextStyle(
                                textDirection = TextDirection.Rtl,
                                fontSize = 30.sp,
                            ), modifier = Modifier.fillMaxWidth()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(20.dp)
                        ) {
                            NavigationButton(title = ContinueAsUser,
                                onClick = { navigateToUserActivity() })
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(20.dp)
                        ) {
                            NavigationButton(title = ContinueAsServiceProvider,
                                onClick = { viewModel.openDialog() })
                        }

                    }
                }
            }
        }
    }

    private fun navigateToUserActivity() {
        startActivity(Intent(this, UserActivity::class.java))
    }

    private fun navigateToServiceProviderActivity() {
        startActivity(Intent(this, ServiceProviderActivity::class.java))
    }

}