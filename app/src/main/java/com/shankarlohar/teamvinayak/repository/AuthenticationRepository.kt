package com.shankarlohar.teamvinayak.repository

import com.shankarlohar.teamvinayak.data.FirestoreDatabase
import com.shankarlohar.teamvinayak.model.ToSubmitFormModel
import com.shankarlohar.teamvinayak.model.SignupFormModel
import com.shankarlohar.teamvinayak.model.TermsAndConditionsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthenticationRepository {

    private val firestoreDatabase = FirestoreDatabase()

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
}