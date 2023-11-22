package com.shankarlohar.teamvinayak.repository

import androidx.lifecycle.LiveData
import com.shankarlohar.teamvinayak.data.firebase.FirestoreDatabase
import com.shankarlohar.teamvinayak.model.GymInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegistrationRepository {
    private val firestoreDatabase = FirestoreDatabase()


    suspend fun getGymInfo(): GymInfo {
        return withContext(Dispatchers.IO) {
            return@withContext firestoreDatabase.getGymInfo()
        }
    }

    suspend fun saveEnquiryQuestion(name: String, phone: String, query: String, whatsapp: Boolean, onDone: (Boolean) -> Unit) {
        firestoreDatabase.saveEnquiryQuestion(name,phone,query,whatsapp,onDone)
    }
}