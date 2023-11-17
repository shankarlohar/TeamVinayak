package com.shankarlohar.teamvinayak.repository

import com.shankarlohar.teamvinayak.data.Authentication
import com.shankarlohar.teamvinayak.data.FirestoreDatabase
import com.shankarlohar.teamvinayak.model.ToSubmitFormModel
import com.shankarlohar.teamvinayak.model.SignupFormModel
import com.shankarlohar.teamvinayak.model.TermsAndConditionsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthenticationRepository {

    private val firestoreDatabase = FirestoreDatabase()
    private val authentication = Authentication()

    suspend fun getTncData(): List<TermsAndConditionsModel> {
        return withContext(Dispatchers.IO) {
            return@withContext firestoreDatabase.getTnC().sortedBy { it.section }
        }
    }

    suspend fun getNewUserForm(): List<SignupFormModel> {
        return withContext(Dispatchers.IO) {
            return@withContext firestoreDatabase.getNewUserForm().sortedBy { it.field }
        }
    }

    suspend fun createNewMember(toSubmitFormModelList: List<ToSubmitFormModel>): Boolean {
        return withContext(Dispatchers.IO) {
            return@withContext firestoreDatabase.createNewUser(toSubmitFormModelList)
        }
    }

    suspend fun loginMember(email: String, password: String,onResult: (Boolean, String?) -> Unit) {
        return withContext(Dispatchers.Main){
            return@withContext authentication.loginMember(email,password,onResult)
        }
    }

    fun logoutMember(onResult: (Boolean) -> Unit) {
        authentication.logoutMember(onResult)
    }

    fun getUid(): String {
        return authentication.getUid()
    }

    suspend fun loginAdmin(name: String, password: String, onResult: (Boolean, String?) -> Unit) {
        return withContext(Dispatchers.Main){
            return@withContext firestoreDatabase.loginAdmin(name,password,onResult)
        }
    }

    suspend fun getAdmin():String{
        return withContext(Dispatchers.Main){
            return@withContext firestoreDatabase.getAdmin()
        }
    }
}