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
import com.ncgr.maqsaf.presentation.customer.view.CustomerActivity
import com.ncgr.maqsaf.presentation.seller.view.SellerActivity
import com.ncgr.maqsaf.ui.theme.MAQSAFTheme
import com.ncgr.maqsaf.ui.theme.screenBackgroundColor
import com.ncgr.maqsaf.ui.theme.toolbarColor
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
            backgroundColor = screenBackgroundColor,
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
                        .background(toolbarColor)
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

    private fun navigateToCustomerActivity() {
        startActivity(Intent(this, CustomerActivity::class.java))
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
                startActivity(Intent(this, SellerActivity::class.java))
            } else {
                alertDialog.dismiss()
                AlertDialog.Builder(this).setTitle("Wrong Password").show()
            }
        }
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()

    }

    @Composable
    fun DialogBoxDeleteItem(
        cornerRadius: Dp = 12.dp,
        deleteButtonColor: Color = Color(0xFFFF0000),
        cancelButtonColor: Color = Color(0xFF35898F),
        titleTextStyle: TextStyle = TextStyle(
            color = Color.Black.copy(alpha = 0.87f),
            fontSize = 20.sp
        ),
        messageTextStyle: TextStyle = TextStyle(
            color = Color.Black.copy(alpha = 0.95f),
            fontSize = 16.sp,
            lineHeight = 22.sp
        ),
        buttonTextStyle: TextStyle = TextStyle(
            fontSize = 16.sp
        ),
        onDismiss: () -> Unit
    ) {

        val context = LocalContext.current.applicationContext

        // This helps to disable the ripple effect
        val interactionSource = remember {
            MutableInteractionSource()
        }

        val buttonCorner = 6.dp

        Dialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(size = cornerRadius)
            ) {
                Column(modifier = Modifier.padding(all = 16.dp)) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 6.dp,
                            alignment = Alignment.Start
                        )
                    ) {

                        // For icon, visit feathericons.com
                        // Icon name: trash-2

                        Text(
                            text = "Delete Item?",
                            style = titleTextStyle
                        )

                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 20.dp),
                        text = "Are you sure you want to delete this item from the list?",
                        style = messageTextStyle
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 10.dp,
                            alignment = Alignment.End
                        )
                    ) {

                        // Cancel button
                        Box(
                            modifier = Modifier
                                .clickable(
                                    // This is to disable the ripple effect
                                    indication = null,
                                    interactionSource = interactionSource
                                ) {
                                    Toast
                                        .makeText(context, "Cancel", Toast.LENGTH_SHORT)
                                        .show()
                                    onDismiss()
                                }
                                .border(
                                    width = 1.dp,
                                    color = cancelButtonColor,
                                    shape = RoundedCornerShape(buttonCorner)
                                )
                                .padding(top = 6.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                        ) {
                            Text(
                                text = "Cancel",
                                style = buttonTextStyle,
                                color = cancelButtonColor
                            )
                        }

                        // Delete button
                        Box(
                            modifier = Modifier
                                .clickable(
                                    // This is to disable the ripple effect
                                    indication = null,
                                    interactionSource = interactionSource
                                ) {
                                    Toast
                                        .makeText(context, "Delete", Toast.LENGTH_SHORT)
                                        .show()
                                    onDismiss()
                                }
                                .background(
                                    color = deleteButtonColor,
                                    shape = RoundedCornerShape(buttonCorner)
                                )
                                .padding(top = 6.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                        ) {
                            Text(
                                text = "Delete",
                                style = buttonTextStyle,
                                color = Color.White
                            )
                        }

                    }
                }

            }

        }
    }

}