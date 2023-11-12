package com.shankarlohar.teamvinayak.repository

import com.shankarlohar.teamvinayak.data.FirestoreDatabase
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
}