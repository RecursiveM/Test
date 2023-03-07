package com.ncgr.maqsaf.presentation.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.ui.theme.ContinueAsUser

@Composable
fun Body(
    modifier: Modifier = Modifier,
    navigateToUserLoginActivity: () -> Unit,
    navigateToServiceProviderLoginActivity: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.maqsaf_logo),
            contentDescription = "MAQSAF Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(60.dp)
            ) {
                NavigationButton(
                    title = ContinueAsUser,
                    onClick = { navigateToUserLoginActivity() },
                    icon = R.drawable.office_worker__2__1
                )
            }
            Box(
                modifier = Modifier
                    .height(60.dp)
            ) {
                NavigationButton(
                    title = "الإستمرار كموفر خدمة",
                    onClick = { navigateToServiceProviderLoginActivity() },
                    icon = R.drawable.office_worker__1__1
                )
            }
        }
    }
}