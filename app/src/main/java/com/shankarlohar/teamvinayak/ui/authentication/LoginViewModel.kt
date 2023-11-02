package com.shankarlohar.teamvinayak.ui.authentication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {
    private val _name = MutableStateFlow("shankarlohar")

    val name = _name.asStateFlow()

}