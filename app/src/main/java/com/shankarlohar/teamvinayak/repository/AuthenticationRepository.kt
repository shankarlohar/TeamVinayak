package com.shankarlohar.teamvinayak.repository

import com.shankarlohar.teamvinayak.data.firebase.Authentication
import com.shankarlohar.teamvinayak.data.firebase.FirestoreDatabase
import com.shankarlohar.teamvinayak.model.TermsAndConditionsModel
import com.shankarlohar.teamvinayak.model.UserData
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


    suspend fun loginMember(email: String, password: String,onResult: (Boolean, String?) -> Unit) {
        return withContext(Dispatchers.Main){
            return@withContext authentication.loginMember(email,password,onResult)
        }
    }

    fun logoutMember(onResult: (Boolean) -> Unit) {
        authentication.logoutMember(onResult)
    }


}