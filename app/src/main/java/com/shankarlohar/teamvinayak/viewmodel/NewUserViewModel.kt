package com.shankarlohar.teamvinayak.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shankarlohar.teamvinayak.model.UserData
import com.shankarlohar.teamvinayak.repository.RegistrationRepository
import com.shankarlohar.teamvinayak.util.UiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewUserViewModel:ViewModel() {

    private val registrationRepository = RegistrationRepository()

    fun uploadProfilePicture(uri:Uri, result: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            registrationRepository.uploadProfilePicture(uri,result)
        }
    }

    fun createAccount(newUser: UserData, status: (Boolean) -> Unit) {
        var isDone = false
        viewModelScope.launch(Dispatchers.Main) {

            try {
                Log.d("formSubmit","repository.createAccount start")
                isDone = registrationRepository.createAccount(newUser)
                Log.d("formSubmit","repository.createAccount finish")

            } catch (e: Exception) {
                Log.d("create account",e.toString())
            } finally {
                Log.d("formSubmit","view modal finally block")
                if (isDone) status(true) else status(false)
            }
        }
    }


}