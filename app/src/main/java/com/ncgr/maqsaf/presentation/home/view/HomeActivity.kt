package com.ncgr.maqsaf.presentation.home.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.presentation.customer.view.CustomerActivity
import com.ncgr.maqsaf.presentation.seller.view.SellerActivity
import com.ncgr.maqsaf.ui.theme.MAQSAFTheme
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
    fun HomeScreen(
        modifier: Modifier = Modifier,
    ) {
        Scaffold(
            snackbarHost= {SnackbarHostState()},
            backgroundColor = Color(0xFFEDF5E9),
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color(0xFFDCE0DA))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ncgr_logo),
                            contentDescription = "NCGR logo",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(100.dp)
                        )
                    }
                }
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
                            text = getString(R.string.home_welcome),
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
                                title = getString(R.string.continue_as_customer),
                                onClick = { navigateToCustomerActivity() })
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(20.dp)
                        ) {
                            HomeButton(title = getString(R.string.continue_as_seller),
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

    fun navigateToCustomerActivity() {
        startActivity(Intent(this, CustomerActivity::class.java))
    }

    fun showAlertDialog() {
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
                startActivity(Intent(this, SellerActivity::class.java))
            } else {
                alertDialog.dismiss()
                AlertDialog.Builder(this).setTitle("Wrong Password").show()
            }
        }
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()

    }

}