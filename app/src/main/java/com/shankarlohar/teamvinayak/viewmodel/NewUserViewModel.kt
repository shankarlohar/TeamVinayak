package com.shankarlohar.teamvinayak.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shankarlohar.teamvinayak.repository.RegistrationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewUserViewModel:ViewModel() {

    private val registrationRepository = RegistrationRepository()

    fun uploadProfilePicture(uri:Uri, result: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            registrationRepository.uploadProfilePicture(uri,result)
        }
    }


}