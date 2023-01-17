package com.ncgr.maqsaf.presentation.serviceProviderRegister.view

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
import com.ncgr.maqsaf.presentation.serviceProvider.view.ServiceProviderActivity
import com.ncgr.maqsaf.presentation.serviceProviderRegister.composable.RegisterDialog
import com.ncgr.maqsaf.presentation.serviceProviderRegister.composable.RegisterScreenBody
import com.ncgr.maqsaf.presentation.serviceProviderRegister.viewModel.ServiceProviderRegisterViewModel
import com.ncgr.maqsaf.presentation.user.view.UserActivity
import com.ncgr.maqsaf.ui.theme.MAQSAFTheme
import com.ncgr.maqsaf.ui.theme.ScreenBackgroundColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServiceProviderRegisterActivity : AppCompatActivity() {

    private val viewModel: ServiceProviderRegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAQSAFTheme {
                RegisterScreen()
            }
        }
    }

    @Composable
    fun RegisterScreen(
        modifier: Modifier = Modifier,
    ) {
        val navigater by viewModel.navigateToHomeActivity.collectAsState()
        if (navigater) navigateToServiceProviderActivity()

        RegisterDialog(viewModel = viewModel)

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
                RegisterScreenBody(viewModel = viewModel)
            }
        }
    }

    private fun navigateToServiceProviderActivity() {
        startActivity(Intent(this, ServiceProviderActivity::class.java))
        finishAffinity()
    }
}