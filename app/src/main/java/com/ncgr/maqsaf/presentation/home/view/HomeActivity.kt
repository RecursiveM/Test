package com.ncgr.maqsaf.presentation.home.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.home.composable.Body
import com.ncgr.maqsaf.presentation.home.viewModel.HomeViewModel
import com.ncgr.maqsaf.presentation.orderDetails.view.OrderDetailsActivity
import com.ncgr.maqsaf.presentation.serviceProvider.view.ServiceProviderActivity
import com.ncgr.maqsaf.presentation.serviceProviderLogin.view.ServiceProviderLoginActivity
import com.ncgr.maqsaf.presentation.user.view.UserActivity
import com.ncgr.maqsaf.presentation.userLogin.view.UserLoginActivity
import com.ncgr.maqsaf.ui.theme.Blue
import com.ncgr.maqsaf.ui.theme.MAQSAFTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAQSAFTheme {
                HomeScreen()
            }
        }
    }

    @Composable
    private fun HomeScreen(
        modifier: Modifier = Modifier,
    ) {
        val checkingUser by viewModel.checkingUser.collectAsState()

        Scaffold(
            backgroundColor = Color.White,
            scaffoldState = rememberScaffoldState(),
            modifier = modifier.fillMaxSize()
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                Box(
                    contentAlignment = Alignment.BottomStart,
                    modifier = Modifier.fillMaxSize()

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ncgr_logo_only),
                        contentDescription = "Bottom Logo",
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.BottomStart,
                        alpha = 0.15f,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .size(300.dp)
                    )
                }

                when (checkingUser) {
                    is Resource.Error -> {
                        Body(
                            navigateToUserLoginActivity = { navigateToLoginActivity() },
                            navigateToServiceProviderLoginActivity = { navigateToServiceProviderLoginActivity() },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    is Resource.Loading -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator(color = Blue)
                        }
                    }

                    is Resource.Success -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator(color = Blue)
                        }
                    }
                }
            }
        }

        val navigateToOrderDetailsActivity by viewModel.navigateToOrderDetailsActivity.collectAsState()
        if (navigateToOrderDetailsActivity) navigateToOrderDetailsActivity()

        val navigateToServiceProviderActivity by viewModel.navigateToServiceProviderActivity.collectAsState()
        if (navigateToServiceProviderActivity) navigateToServiceProviderActivity()

        val navigateToUserActivity by viewModel.navigateToUserActivity.collectAsState()
        if (navigateToUserActivity) navigateToUserActivity()
    }

    private fun navigateToLoginActivity() {
        startActivity(Intent(this, UserLoginActivity::class.java))
    }

    private fun navigateToServiceProviderLoginActivity() {
        startActivity(Intent(this, ServiceProviderLoginActivity::class.java))
    }

    private fun navigateToOrderDetailsActivity() {
        startActivity(Intent(this, OrderDetailsActivity::class.java))
        finishAffinity()
    }

    private fun navigateToUserActivity() {
        startActivity(Intent(this, UserActivity::class.java))
        finishAffinity()
    }

    private fun navigateToServiceProviderActivity() {
        startActivity(Intent(this, ServiceProviderActivity::class.java))
        finishAffinity()
    }

}