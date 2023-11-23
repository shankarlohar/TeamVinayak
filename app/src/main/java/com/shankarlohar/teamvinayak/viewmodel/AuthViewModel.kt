package com.shankarlohar.teamvinayak.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shankarlohar.teamvinayak.model.TermsAndConditionsModel
import com.shankarlohar.teamvinayak.model.UserData
import com.shankarlohar.teamvinayak.repository.AuthenticationRepository
import com.shankarlohar.teamvinayak.repository.RegistrationRepository
import com.shankarlohar.teamvinayak.util.Status
import com.shankarlohar.teamvinayak.util.UiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {


    private val _dataStatus = MutableStateFlow(UiStatus.Loading)
    val dataStatus = _dataStatus.asStateFlow()

    private val _userForm = MutableStateFlow(UiStatus.Loading)
    val userForm = _userForm.asStateFlow()

    private val _admin = MutableStateFlow("")
    val admin = _admin.asStateFlow()



    private val authenticationRepository = AuthenticationRepository()

    private val _tncData = MutableStateFlow<List<TermsAndConditionsModel>>(emptyList())
    val termsAndConditionsData: StateFlow<List<TermsAndConditionsModel>> = _tncData



    private val _formMap = mutableMapOf<String, MutableList<Pair<String, String>>>()

   private val registrationRepository = RegistrationRepository()

    fun getUid(): String {
        return authenticationRepository.getUid()
    }

    fun loginMember(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch(Dispatchers.Main){
            return@launch authenticationRepository.loginMember(email,password, onResult)
            }
    }
    fun loginAdmin(name: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch(Dispatchers.Main){
            return@launch authenticationRepository.loginAdmin(name,password, onResult)
        }
    }

    fun getAdmin(){
        viewModelScope.launch(Dispatchers.Main) {
            _admin.value = authenticationRepository.getAdmin()
        }
    }

    fun logoutMember(onResult: (Boolean) -> Unit){
        viewModelScope.launch(Dispatchers.Main){
            return@launch authenticationRepository.logoutMember(onResult)
        }
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



    suspend fun createNewMember(userData: UserData){
        _userForm.value = UiStatus.Loading
        var status = false
        viewModelScope.launch(Dispatchers.IO)
            {
            try {


                status = authenticationRepository.createNewMember(userData)

            } catch (e: Exception) {
                _userForm.value = UiStatus.Failed
            } finally {
                registrationRepository.updateGymInfo()
                if (status) _userForm.value = UiStatus.Completed // when data loading is complete
            }
        }

    }

    fun checkAnswer(answer: String, question: String): Pair<Boolean,String> {
        var validity = false
        var response = ""
        if (answer.isEmpty()){
            response = "Field Cannot be empty!"
        }else{
            validity = true
        }
        return validity to response
    }


}