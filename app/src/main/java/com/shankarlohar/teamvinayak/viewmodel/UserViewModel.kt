package com.shankarlohar.teamvinayak.viewmodel

import androidx.lifecycle.ViewModel
import com.shankarlohar.teamvinayak.repository.UserDataRepository

class UserViewModel: ViewModel() {
    private val userDataRepository = UserDataRepository()

//    private val _personalDetails = MutableLiveData<UserData>()
//    val personalDetails: LiveData<User>
//        get() = _personalDetails


    fun fetchPersonalDetails(uid: String) {
//        viewModelScope.launch(Dispatchers.Main) {
//            _personalDetails.value = userDataRepository.getUserPersonalDetails(uid)
//        }
    }
}