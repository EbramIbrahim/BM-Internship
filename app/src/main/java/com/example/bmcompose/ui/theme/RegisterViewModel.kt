package com.example.bmcompose.ui.theme

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bmcompose.data.RegisterUser

class RegisterViewModel : ViewModel() {

    private var regUser: RegisterUser = RegisterUser()

    var userName: MutableState<String> = mutableStateOf(regUser.name)
    var isUserNameValid: MutableState<Boolean> = mutableStateOf(false)
    var userNameErrMsg: MutableState<String> = mutableStateOf("")

    var email: MutableState<String> = mutableStateOf(regUser.email)
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf(regUser.password)
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    var confirmPassword: MutableState<String> = mutableStateOf(regUser.confirmPassword)
    var isConfirmPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var confPasswordErrMsg: MutableState<String> = mutableStateOf("")

    var isEnabledRegisterButton: MutableState<Boolean> = mutableStateOf(false)



    private fun shouldEnabledRegisterButton() {
        isEnabledRegisterButton.value = userNameErrMsg.value.isEmpty()
                && emailErrMsg.value.isEmpty()
                && passwordErrMsg.value.isEmpty()
                && confPasswordErrMsg.value.isEmpty()
                && userName.value.isNotEmpty()
                && email.value.isNotEmpty()
                && password.value.isNotEmpty()
                && confirmPassword.value.isNotEmpty()
    }

    fun validateUserName() {
        if (userName.value.length >= 10) {
            isUserNameValid.value = true
            userNameErrMsg.value = "User Name should be less than 10 chars"
        } else {
            isUserNameValid.value = false
            userNameErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }

    fun validateEmail() {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isEmailValid.value = true
            emailErrMsg.value = "Input proper email id"
        } else {
            isEmailValid.value = false
            emailErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }

    fun validatePassword() {
        if (password.value.length < 6) {
            isPasswordValid.value = true
            passwordErrMsg.value = "Password should be 6 digits or more"
        } else {
            isPasswordValid.value = false
            passwordErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }

    fun validateConfirmPassword() {
        if (password.value != confirmPassword.value) {
            isConfirmPasswordValid.value = true
            confPasswordErrMsg.value = "Password did not match"
        } else {
            isConfirmPasswordValid.value = false
            confPasswordErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }

    fun register() {
        regUser.name = userName.value
        regUser.email = email.value
        regUser.password = password.value
        regUser.confirmPassword = confirmPassword.value
    }

}