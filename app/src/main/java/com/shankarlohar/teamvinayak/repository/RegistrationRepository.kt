package com.shankarlohar.teamvinayak.repository

import android.net.Uri
import android.util.Log
import com.shankarlohar.teamvinayak.data.firebase.FirestoreDatabase
import com.shankarlohar.teamvinayak.data.firebase.Storage
import com.shankarlohar.teamvinayak.model.Enquiry
import com.shankarlohar.teamvinayak.model.FaqItem
import com.shankarlohar.teamvinayak.model.GymInfo
import com.shankarlohar.teamvinayak.model.UserData
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

    suspend fun fetchFaq(): List<FaqItem> {
        return withContext(Dispatchers.IO) {
            return@withContext firestoreDatabase.fetchFaq()
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

    suspend fun createAccount(newUser: UserData):Boolean {
        return withContext(Dispatchers.IO){
            Log.d("formSubmit","firestoreDatabase call")
            return@withContext firestoreDatabase.createNewUser(newUser)
        }
    }
}