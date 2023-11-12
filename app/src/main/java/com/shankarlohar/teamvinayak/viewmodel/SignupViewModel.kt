package com.shankarlohar.teamvinayak.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shankarlohar.teamvinayak.model.OnBoardingModel
import com.shankarlohar.teamvinayak.model.SignupFormModel
import com.shankarlohar.teamvinayak.repository.OnBoardingRepository
import com.shankarlohar.teamvinayak.repository.SignupFormRepository
import com.shankarlohar.teamvinayak.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class SignupViewModel: ViewModel() {

    private val _dataStatus = MutableStateFlow(Status.Loading)
    val dataStatus = _dataStatus.asStateFlow()

    private val onBoardingRepository = OnBoardingRepository()
    private val signupFormRepository = SignupFormRepository()

    private val _onBoardingData = MutableStateFlow<List<OnBoardingModel>>(emptyList())
    val termsAndConditionsData: StateFlow<List<OnBoardingModel>> = _onBoardingData

    private val _signupFormData = MutableStateFlow<List<SignupFormModel>>(emptyList())
    val signupFormData: StateFlow<List<SignupFormModel>> = _signupFormData

    init {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                loadOnBoardingData()
                loadSignupFormData()
            }catch (e: Exception){
                _dataStatus.value = Status.Failed
            }finally {
                _dataStatus.value = Status.Completed // when data loading is complete
            }
        }
    }

    private fun loadSignupFormData() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = onBoardingRepository.getOnBoardingData()
            _onBoardingData.value = data
        }
    }

    private fun loadOnBoardingData() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = signupFormRepository.getSignupForm()
            _signupFormData.value = data
        }
    }
}