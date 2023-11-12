package com.shankarlohar.teamvinayak.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shankarlohar.teamvinayak.model.ChoiceScreenDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChoiceScreenViewModel() : ViewModel() {


    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.Main) {
                _isLoading.value = false // when data loading is complete
        }
    }

    val screenState = mutableStateOf<UiState>(UiState.Home)


    sealed class UiState {
        class Details(val choiceScreenDataModel: ChoiceScreenDataModel) : UiState()
        object Home : UiState()
    }

}