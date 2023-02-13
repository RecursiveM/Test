package com.ncgr.maqsaf.presentation.userRegister.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.presentation.userRegister.viewModel.UserRegisterViewModel
import com.ncgr.maqsaf.ui.theme.Blue

@Composable
fun UserRegisterScreenBody(
    modifier: Modifier = Modifier,
    viewModel: UserRegisterViewModel,
) {
    val username by viewModel.username.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val passwordText by viewModel.passwordText.collectAsState()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .height(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = username ?: "",
            label = { Text("اسم المستخدم") },
            placeholder = { Text("اسم المستخدم") },
            onValueChange = { viewModel.setUsername(it) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = phoneNumber ?: "",
            label = { Text("رقمك الوظيفي") },
            placeholder = { Text("رقمك الوظيفي") },
            onValueChange = { if (it.length <= 6) viewModel.setPhoneNumber(it) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = passwordText ?: "",
            label = { Text("كلمة المرور") },
            placeholder = { Text("كلمة المرور") },
            onValueChange = { viewModel.setPasswordText(it) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10))
                .background(Blue)
                .clickable {
                    viewModel.register()
                }
                .padding(10.dp)) {
            Text(
                text = "تسجيل كجديد",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            )
        }
    }
}