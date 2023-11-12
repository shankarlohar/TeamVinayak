package com.shankarlohar.teamvinayak.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.shankarlohar.teamvinayak.model.FormModel
import com.shankarlohar.teamvinayak.model.OnBoardingModel
import com.shankarlohar.teamvinayak.model.SignupFormModel
import kotlinx.coroutines.tasks.await

class FirestoreDatabase {
    private val db = FirebaseFirestore.getInstance()

    private val tncList = db.collection("register").document("terms-and-conditions")
    private val signupFormFields = db.collection("register").document("form")

    private val newUser = db.collection("user") // Replace with your collection name



    suspend fun getTnC(): List<OnBoardingModel>{
        val snapshot = tncList.get().await()

        if (snapshot.exists()) {
            val data = snapshot.data
            val tncList = mutableListOf<OnBoardingModel>()

            if (data != null) {
                for (key in data.keys) {
                    if (data[key] is List<*>) {
                        val content = (data[key] as List<String>)
                        val onBoardingModel = OnBoardingModel(key, content)
                        tncList.add(onBoardingModel)
                    }
                }
            }

            return tncList
        }

        return emptyList()
    }

    suspend fun getSignupForm(): List<SignupFormModel>{
        val snapshot = signupFormFields.get().await()

        if (snapshot.exists()) {
            val data = snapshot.data
            val signupForm = mutableListOf<SignupFormModel>()

            if (data != null) {
                for (key in data.keys) {
                    if (data[key] is List<*>) {
                        val content = (data[key] as List<String>)
                        val signupFormData = SignupFormModel(key, content)
                        signupForm.add(signupFormData)
                    }
                }
            }

            return signupForm
        }

        return emptyList()
    }

    suspend fun uploadNewUser(formModelList: List<FormModel>): Boolean {
        val results = mutableListOf<Boolean>()

        val userSections = mutableMapOf<String, Map<String, String>>()

        for (formModel in formModelList) {
            val sectionData = userSections.getOrPut(formModel.field) { emptyMap() }
            val newData = formModel.data.associate { it.first to it.second }
            userSections[formModel.field] = sectionData + newData
        }

            try {

                // Create a reference to a new document within the "users" collection with a random unique ID
                val newUserDoc = newUser.document()

                // Set the map in the new document
                newUserDoc.set(userSections).await()

                Log.e("myuserdata", "uploaded to Firestore")
                results.add(true)
            } catch (e: Exception) {
                Log.e("myuserdata", "could not upload to Firestore", e)
                results.add(false)
            }

        // Check if all tasks were successful
        return results.all { it }
    }




}