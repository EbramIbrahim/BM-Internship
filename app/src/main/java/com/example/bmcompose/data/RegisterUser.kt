package com.example.bmcompose.data

data class RegisterUser(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = ""
)