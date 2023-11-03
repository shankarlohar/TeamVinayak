package com.shankarlohar.teamvinayak

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shankarlohar.teamvinayak.data.model.GymInfoModel
import com.shankarlohar.teamvinayak.data.repositories.FirestoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    firestoreRepository: FirestoreRepository
) : ViewModel() {


    private val _isLoading = MutableStateFlow(true)
    private val _gymInfo = MutableStateFlow<GymInfoModel?>(null)

    val isLoading = _isLoading.asStateFlow()
    val gymInfo = _gymInfo.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _gymInfo.value = firestoreRepository.getGymInfoDocument()
            } catch (e: Exception) {
                Log.e("firestore", "Error fetching GymInfo data: ${e.message}")
            } finally {
                _isLoading.value = false // Data loading is complete
            }
        }
    }

}