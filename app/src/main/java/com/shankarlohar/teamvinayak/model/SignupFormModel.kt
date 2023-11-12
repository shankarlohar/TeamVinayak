package com.shankarlohar.teamvinayak.model

data class SignupFormModel(
    val field: String,
    val data: List<String>
)

data class FormModel(
    val field: String,
    val data: List<Pair<String,String>>
)
