package com.shankarlohar.teamvinayak.ui.authentication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel: ViewModel() {
    private val _name = MutableStateFlow("shankarlohar")

    val name = _name.asStateFlow()

}