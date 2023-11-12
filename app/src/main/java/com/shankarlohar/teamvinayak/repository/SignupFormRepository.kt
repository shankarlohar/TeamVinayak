package com.shankarlohar.teamvinayak.repository

import android.util.Log
import com.shankarlohar.teamvinayak.data.FirestoreDatabase
import com.shankarlohar.teamvinayak.model.FormModel
import com.shankarlohar.teamvinayak.model.SignupFormModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignupFormRepository {

    private val firestoreDatabase = FirestoreDatabase()

    suspend fun getSignupForm(): List<SignupFormModel> {
        return withContext(Dispatchers.IO) {
            return@withContext firestoreDatabase.getSignupForm().sortedBy { it.field }
        }
    }

    suspend fun uploadNewRegistration(formModelList: List<FormModel>): Boolean {
        return withContext(Dispatchers.IO) {
            return@withContext firestoreDatabase.uploadNewUser(formModelList)
        }
    }
}