package com.shankarlohar.teamvinayak.ui.authentication.signup

import androidx.lifecycle.ViewModel
import com.shankarlohar.teamvinayak.data.model.OnBoardingModel
import com.shankarlohar.teamvinayak.data.repositories.OnBoardingDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class OnBoardingViewModel: ViewModel() {

    private val boardingContent = getData()
    private val _onBoardingData = MutableStateFlow(boardingContent)

    val onBoardingData = _onBoardingData.asStateFlow()

    private fun getData(): List<OnBoardingModel>{
        return OnBoardingDataRepository.getData()
    }
}
