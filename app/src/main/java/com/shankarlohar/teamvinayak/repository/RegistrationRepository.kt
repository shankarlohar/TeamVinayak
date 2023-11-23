package com.shankarlohar.teamvinayak.repository

import android.net.Uri
import android.util.Log
import com.shankarlohar.teamvinayak.data.firebase.FirestoreDatabase
import com.shankarlohar.teamvinayak.data.firebase.Storage
import com.shankarlohar.teamvinayak.model.Enquiry
import com.shankarlohar.teamvinayak.model.GymInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegistrationRepository {
    private val firestoreDatabase = FirestoreDatabase()
    private val storage = Storage()

    suspend fun getGymInfo(): GymInfo {
        return withContext(Dispatchers.IO) {
            return@withContext firestoreDatabase.getGymInfo()
        }
    }

    suspend fun updateGymInfo() {
        return withContext(Dispatchers.IO) {
            return@withContext firestoreDatabase.updateGymInfo()
        }
    }


    suspend fun saveEnquiryQuestion(enquiry: Enquiry, onDone: (Boolean) -> Unit) {
        firestoreDatabase.saveEnquiryQuestion(enquiry,onDone)
    }

    suspend fun uploadProfilePicture(uri: Uri, result: (String) -> Unit) {
        return withContext(Dispatchers.IO){
            return@withContext storage.uploadImage(uri,result){
                Log.d("Picture Upload Fail",it.toString())
            }
        }
    }
}