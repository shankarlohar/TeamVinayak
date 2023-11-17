package com.shankarlohar.teamvinayak.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shankarlohar.teamvinayak.model.User
import com.shankarlohar.teamvinayak.repository.UserDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    private val userDataRepository = UserDataRepository()

    private val _personalDetails = MutableLiveData<User>()
    val personalDetails: LiveData<User>
        get() = _personalDetails


    fun fetchPersonalDetails(uid: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _personalDetails.value = userDataRepository.getUserPersonalDetails(uid)
        }
    }
}