package com.shankarlohar.teamvinayak.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shankarlohar.teamvinayak.model.ChooseUserModel
import com.shankarlohar.teamvinayak.model.GymInfo
import com.shankarlohar.teamvinayak.repository.RegistrationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChooseUserViewModel() : ViewModel() {

    private val registrationRepository = RegistrationRepository()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _gymInfo = MutableStateFlow(GymInfo())
    val gymInfo = _gymInfo.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                _gymInfo.value = registrationRepository.getGymInfo()
            }catch (e: Exception){
                Log.d("registration",e.toString())
            }finally {
                _isLoading.value = false // when data loading is complete
                Log.d("gyminfo", _gymInfo.value.name)
            }
        }
    }

    val screenState = mutableStateOf<UiState>(UiState.Home)


    sealed class UiState {
        class Details(val chooseUserModel: ChooseUserModel) : UiState()
        object Home : UiState()
    }



    fun saveEnquiryQuestion(name: String, phone: String, query: String, whatsapp: Boolean, onDone: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO){
            registrationRepository.saveEnquiryQuestion(name,phone,query,whatsapp,onDone)
        }
    }

}