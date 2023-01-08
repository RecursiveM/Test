package com.ncgr.maqsaf.presentation.home.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.presentation.common.composable.AppBar
import com.ncgr.maqsaf.presentation.user.view.UserActivity
import com.ncgr.maqsaf.presentation.serviceProvider.view.ServiceProviderActivity
import com.ncgr.maqsaf.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

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
                                onClick = { navigateToCustomerActivity() })
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(20.dp)
                        ) {
                            HomeButton(title = ContinueAsServiceProvider,
                                onClick = { showAlertDialog() })
                        }

                    }
                }
            }
        }
    }

    @Composable
    fun HomeButton(
        modifier: Modifier = Modifier,
        title: String,
        onClick: () -> Unit,
    ) {
        Surface(
            elevation = 20.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        onClick()
                    }
                    .padding(10.dp)
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        textDirection = TextDirection.Rtl,
                        fontSize = 30.sp,

                        ),

                    )
            }
        }

    }

    private fun navigateToCustomerActivity() {
        startActivity(Intent(this, UserActivity::class.java))
    }

    private fun showAlertDialog() {
        val alertDialog = AlertDialog.Builder(this).create()
        val alertView = layoutInflater.inflate(R.layout.alert_dialog, null)
        val cancelButton: Button = alertView.findViewById(R.id.dialog_dismiss_button)
        val confirmButton: Button = alertView.findViewById(R.id.dialog_confirm_button)
        val passwordText: EditText = alertView.findViewById(R.id.password_text)

        alertDialog.setView(alertView)
        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }
        confirmButton.setOnClickListener {
            if (passwordText.text.toString() == "123456") {
                alertDialog.dismiss()
                startActivity(Intent(this, ServiceProviderActivity::class.java))
            } else {
                alertDialog.dismiss()
                AlertDialog.Builder(this).setTitle("Wrong Password").show()
            }
        }
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()

    }

}