package com.shankarlohar.vmgsignup

import androidx.lifecycle.ViewModel
import com.shankarlohar.vmgsignup.data.onBoardingData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignupViewModel: ViewModel() {
    private val _termsAndConditionsData = MutableStateFlow(onBoardingData)

    val termsAndConditionsData = _termsAndConditionsData.asStateFlow()
}