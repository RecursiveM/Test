package com.ncgr.maqsaf.presentation.serviceProviderLogin.composable

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
import com.ncgr.maqsaf.presentation.serviceProviderLogin.viewModel.ServiceProviderLoginViewModel
import com.ncgr.maqsaf.ui.theme.Blue

@Composable
fun ServiceProviderLoginDialog(
    viewModel: ServiceProviderLoginViewModel,
) {
    val loginStatus = viewModel.loginStatus.collectAsState().value
    val openDialog by viewModel.openLoginDialog.collectAsState()
    val phoneNumberError by viewModel.phoneNumberError.collectAsState()
    val passwordTextError by viewModel.passwordTextError.collectAsState()

    if (!openDialog) return

    when (loginStatus) {
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
                            text = "جاري الانتظار",
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
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "نجح تسجيل الدخول")
                    }
                },
                text = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = loginStatus.data,
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
            if (loginStatus.apiError.errorMessage == "Error form submission") {
                AlertDialog(
                    onDismissRequest = {
                        viewModel.closeRegisterDialog()
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
                            Text(text = "${phoneNumberError ?: ""}\n${passwordTextError ?: ""}", textAlign = TextAlign.End)
                        }
                    },
                    buttons = {}
                )
            } else {
                AlertDialog(
                    onDismissRequest = {
                        viewModel.closeRegisterDialog()
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
                            Text(text = loginStatus.apiError.errorMessage)
                        }
                    },
                    buttons = {}
                )
            }
        }
    }
}