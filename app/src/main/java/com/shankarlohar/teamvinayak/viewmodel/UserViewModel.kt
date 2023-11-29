package com.shankarlohar.teamvinayak.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shankarlohar.teamvinayak.model.Attendance
import com.shankarlohar.teamvinayak.model.UserData
import com.shankarlohar.teamvinayak.repository.UserDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    private val userDataRepository = UserDataRepository()

    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData>
        get() = _userData

    private val _attendanceState = MutableLiveData(false)
    private val _attendanceData = MutableLiveData<Attendance>()
    val attendanceState: LiveData<Boolean>
        get() = _attendanceState

    val attendanceData: LiveData<Attendance>
        get() = _attendanceData


    fun fetchUserData(uid: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _userData.value = userDataRepository.getUserData(uid)
        }
    }
    fun fetchUserEmail(username: String, emailValue: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            val email = userDataRepository.getUserEmail(username)
            emailValue(email)
        }
    }

    fun uploadAttendance(userAttendance: Attendance, uid: String, onDone: (Boolean) -> Unit ){
        viewModelScope.launch(Dispatchers.Main){
            userDataRepository.uploadAttendance(userAttendance,uid,onDone)
        }
    }

    fun fetchTodaysAttendance(uid: String)  {
        viewModelScope.launch(Dispatchers.Main){
            _attendanceData.value = userDataRepository.fetchTodaysAttendance(uid){
                _attendanceState.value = it
            }
        }
    }

}