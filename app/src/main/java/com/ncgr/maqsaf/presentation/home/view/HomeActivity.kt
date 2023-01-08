package com.ncgr.maqsaf.presentation.home.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.presentation.common.composable.AppBar
import com.ncgr.maqsaf.presentation.home.composable.CustomAlertDialog
import com.ncgr.maqsaf.presentation.home.composable.HomeButton
import com.ncgr.maqsaf.presentation.serviceProvider.view.ServiceProviderActivity
import com.ncgr.maqsaf.presentation.user.view.UserActivity
import com.ncgr.maqsaf.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAQSAFTheme {
                val openDialog = remember { mutableStateOf(false) }
                if (openDialog.value) CustomAlertDialog(openDialog = openDialog, navigateToServiceProviderActivity = {navigateToServiceProviderActivity()})

                HomeScreen(openDialog = openDialog)
            }
        }
    }

    @Composable
    private fun HomeScreen(
        modifier: Modifier = Modifier,
        openDialog: MutableState<Boolean>,
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
                AppBar()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = HomeWelcome,
                            style = TextStyle(
                                textDirection = TextDirection.Rtl,
                                fontSize = 30.sp,
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )


                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(20.dp)
                        ) {
                            HomeButton(
                                title = ContinueAsUser,
                                onClick = { navigateToUserActivity() })
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(20.dp)
                        ) {
                            HomeButton(title = ContinueAsServiceProvider,
                                onClick = { openDialog.value = true })
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