package com.ncgr.maqsaf.presentation.home.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.presentation.home.viewModel.HomeViewModel
import com.ncgr.maqsaf.ui.theme.ContinueAsServiceProvider
import com.ncgr.maqsaf.ui.theme.ContinueAsUser
import com.ncgr.maqsaf.ui.theme.HomeWelcome

@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navigateToUserLoginActivity: () -> Unit,
    navigateToServiceProviderLoginActivity: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
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
                    onClick = { navigateToUserLoginActivity() })
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(20.dp)
            ) {
                NavigationButton(title = ContinueAsServiceProvider,
                    onClick = {navigateToServiceProviderLoginActivity()})
            }

        }
    }
}