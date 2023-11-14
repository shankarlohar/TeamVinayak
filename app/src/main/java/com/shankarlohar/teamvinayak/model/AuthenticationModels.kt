package com.shankarlohar.teamvinayak.model

data class SignupFormModel(
    val field: String,
    val data: List<String>
)

data class ToSubmitFormModel(
    val field: String,
    val data: List<Pair<String,String>>
)

sealed class LoginResult {
    object Success : LoginResult()
    data class Error(val error: String) : LoginResult()
}

