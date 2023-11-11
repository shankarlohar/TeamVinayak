package com.shankarlohar.teamvinayak

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shankarlohar.teamvinayak.data.repositories.FirestoreRepository
import com.shankarlohar.teamvinayak.model.ChoiceScreenDataModel
import com.shankarlohar.teamvinayak.model.GymInfoModel
import com.shankarlohar.teamvinayak.model.OnBoardingModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    firestoreRepository: FirestoreRepository
) : ViewModel() {


    private val _isLoading = MutableStateFlow(true)
    private val _gymInfo = MutableStateFlow<GymInfoModel?>(null)
    private val _termsAndConditionsData = MutableStateFlow<List<OnBoardingModel>?>(null)

    val isLoading = _isLoading.asStateFlow()
    val gymInfo = _gymInfo.asStateFlow()
    val termsAndConditionsData = _termsAndConditionsData.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                _gymInfo.value = firestoreRepository.getGymInfoDocument()
            } catch (e: Exception) {
                Log.e("firestore", "Error fetching GymInfo data: ${e.message}")
            } finally {
                _isLoading.value = false // Data loading is complete
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _termsAndConditionsData.value = firestoreRepository.getTnC().sortedBy { it.section }
            } catch (e: Exception) {
                Log.e("firestore", "Error fetching OnBoarding data: ${e.message}")
            }
        }
    }

    val screenState = mutableStateOf<UiState>(UiState.Home)


    sealed class UiState {
        class Details(val choiceScreenDataModel: ChoiceScreenDataModel) : UiState()
        object Home : UiState()
    }

}