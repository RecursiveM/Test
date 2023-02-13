package com.ncgr.maqsaf.presentation.userLogin.view

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
import com.ncgr.maqsaf.presentation.orderDetails.view.OrderDetailsActivity
import com.ncgr.maqsaf.presentation.user.view.UserActivity
import com.ncgr.maqsaf.presentation.userLogin.composable.LoginDialog
import com.ncgr.maqsaf.presentation.userLogin.composable.LoginScreenBody
import com.ncgr.maqsaf.presentation.userLogin.viewModel.UserLoginViewModel
import com.ncgr.maqsaf.presentation.userRegister.view.UserRegisterActivity
import com.ncgr.maqsaf.ui.theme.MAQSAFTheme
import com.ncgr.maqsaf.ui.theme.ScreenBackgroundColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserLoginActivity : AppCompatActivity() {

    private val viewModel: UserLoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAQSAFTheme {
                LoginScreen()
            }
        }
    }

    @Composable
    fun LoginScreen(
        modifier: Modifier = Modifier,
    ) {
        val navigater by viewModel.navigateToUserActivity.collectAsState()
        if (navigater) navigateToUserActivity()

        val navigateToOrder by viewModel.navigateToOrderDetailsActivity.collectAsState()
        if (navigateToOrder) navigateToOrderDetailsActivity()

        LoginDialog(viewModel = viewModel)

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
                AppBar()

                //Body
                LoginScreenBody(
                    viewModel = viewModel,
                    navigateToRegisterActivity = { navigateToRegisterActivity() })
            }
        }
    }

    private fun navigateToUserActivity() {
        startActivity(Intent(this, UserActivity::class.java))
        finishAffinity()
    }

    private fun navigateToOrderDetailsActivity() {
        startActivity(Intent(this, OrderDetailsActivity::class.java))
        finishAffinity()
    }

    private fun navigateToRegisterActivity() {
        startActivity(Intent(this, UserRegisterActivity::class.java))
    }
}