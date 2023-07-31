package com.example.bmcompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bmcompose.ui.theme.RegisterViewModel

@Composable
fun ShowRegisterScreen(rvm: RegisterViewModel = viewModel()) {


    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .shadow(8.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp)
    ) {


        Column(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "User Registration",
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )

            ShowUserName(rvm)
            ShowEmail(rvm)
            ShowPassword(rvm)
            ShowConfirmPassword(rvm)

            ShowButton(rvm)

        }
    }
}

@Composable
private fun ShowButton(
    rvm: RegisterViewModel,

) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.register))

    Button(onClick = {
        rvm.register()
        showDialog = true
    },

        enabled = rvm.isEnabledRegisterButton.value
    ) {
        Text("Register Me")
        if (showDialog) {
            AnimatedVisibility(visible = showDialog) {
                Dialog(onDismissRequest = { Unit }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                    ) {
                        LottieAnimation(composition = lottieComposition,)
                        showDialog = false

                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowUserName(rvm: RegisterViewModel) {
    Column(modifier = Modifier.padding(5.dp)) {
        OutlinedTextField(
            value = rvm.userName.value,
            onValueChange = {
                rvm.userName.value = it
                rvm.validateUserName()
            },
            isError = rvm.isUserNameValid.value,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(5.dp),
            label = { Text("User Name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowEmail(rvm: RegisterViewModel) {
    Column(modifier = Modifier.padding(5.dp)) {
        OutlinedTextField(
            value = rvm.email.value,
            onValueChange = {
                rvm.email.value = it
                rvm.validateEmail()
            },
            isError = rvm.isEmailValid.value,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(5.dp),
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowPassword(rvm: RegisterViewModel) {
    Column(modifier = Modifier.padding(5.dp)) {
        OutlinedTextField(
            value = rvm.password.value,
            onValueChange = {
                rvm.password.value = it
                rvm.validatePassword()

            },
            isError = rvm.isPasswordValid.value,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(5.dp),
            label = { Text("Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)

        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowConfirmPassword(rvm: RegisterViewModel) {
    Column(modifier = Modifier.padding(5.dp)) {
        OutlinedTextField(
            value = rvm.confirmPassword.value,
            onValueChange = {
                rvm.confirmPassword.value = it
                rvm.validateConfirmPassword()
            },
            isError = rvm.isConfirmPasswordValid.value,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(5.dp),
            label = { Text("Confirm Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),

        )

    }
}