package com.shankarlohar.teamvinayak.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shankarlohar.teamvinayak.model.LoginResult
import com.shankarlohar.teamvinayak.model.ToSubmitFormModel
import com.shankarlohar.teamvinayak.model.TermsAndConditionsModel
import com.shankarlohar.teamvinayak.model.SignupFormModel
import com.shankarlohar.teamvinayak.repository.AuthenticationRepository
import com.shankarlohar.teamvinayak.util.Status
import com.shankarlohar.teamvinayak.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    private val _dataStatus = MutableStateFlow(Status.Loading)
    val dataStatus = _dataStatus.asStateFlow()

    private val _userForm = MutableStateFlow(Status.Loading)
    val userForm = _userForm.asStateFlow()



    private val authenticationRepository = AuthenticationRepository()

    private val _tncData = MutableStateFlow<List<TermsAndConditionsModel>>(emptyList())
    val termsAndConditionsData: StateFlow<List<TermsAndConditionsModel>> = _tncData

    private val _signupFormData = MutableStateFlow<List<SignupFormModel>>(emptyList())
    val signupFormData: StateFlow<List<SignupFormModel>> = _signupFormData


    private val _formMap = mutableMapOf<String, MutableList<Pair<String, String>>>()


    fun loginMember(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch(Dispatchers.Main){
            return@launch authenticationRepository.loginMember(email,password, onResult)

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
                fetchNewUserForm()
                fetchTnc()
            }catch (e: Exception){
                _dataStatus.value = Status.Failed
            }finally {
                _dataStatus.value = Status.Completed // when data loading is complete
            }
        }
    }

    private fun fetchTnc() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = authenticationRepository.getTncData()
            _tncData.value = data
        }
    }

    private fun fetchNewUserForm() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = authenticationRepository.getNewUserForm()
            _signupFormData.value = data
        }
    }

    fun addQuestion(heading: String, question: String, answer: String) {
        viewModelScope.launch(Dispatchers.Default){
            val questionList = _formMap.getOrPut(heading) { mutableListOf() }
            questionList.add(question to answer)
        }
    }

    fun createNewMember(){
        addQuestion("6. Membership Details", "Approved","false")
        addQuestion("6. Membership Details", "Form Fill Date and Time",Utils.getCurrentDate())
        _userForm.value = Status.Loading
        var status = false
        viewModelScope.launch(Dispatchers.IO)
            {
            try {
                val toSubmitFormModelList = _formMap.map { (field, data) ->
                    ToSubmitFormModel(field, data)
                }

                status = authenticationRepository.createNewMember(toSubmitFormModelList)

            } catch (e: Exception) {
                _userForm.value = Status.Failed
            } finally {
                if (status) _userForm.value = Status.Completed // when data loading is complete
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