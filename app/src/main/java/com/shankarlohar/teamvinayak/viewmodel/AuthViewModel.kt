package com.shankarlohar.teamvinayak.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.shankarlohar.teamvinayak.model.TermsAndConditionsModel
import com.shankarlohar.teamvinayak.repository.AuthenticationRepository
import com.shankarlohar.teamvinayak.util.UiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {


    private val _dataStatus = MutableStateFlow(UiStatus.Loading)
    val dataStatus = _dataStatus.asStateFlow()

    private val authenticationRepository = AuthenticationRepository()

    private val _tncData = MutableStateFlow<List<TermsAndConditionsModel>>(emptyList())
    val termsAndConditionsData: StateFlow<List<TermsAndConditionsModel>> = _tncData


    fun loginMember(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        Log.d("bootomsheetlogin","in viewModel")
        viewModelScope.launch(Dispatchers.Main){
            return@launch authenticationRepository.loginMember(email,password, onResult)
            }
    }

    fun logoutMember(onResult: (Boolean) -> Unit){
        viewModelScope.launch(Dispatchers.Main){
            return@launch authenticationRepository.logoutMember(onResult)
        }
    }

    fun getAuth(): FirebaseUser? {
        return authenticationRepository.getAuth()
    }


    init {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                fetchTnc()
            }catch (e: Exception){
                _dataStatus.value = UiStatus.Failed
            }finally {
                _dataStatus.value = UiStatus.Completed // when data loading is complete
            }
        }
    }

    private fun fetchTnc() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = authenticationRepository.getTncData()
            _tncData.value = data
        }
    }


}