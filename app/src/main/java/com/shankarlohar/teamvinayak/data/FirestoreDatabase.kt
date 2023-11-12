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

    private val newUser = db.collection("User") // Replace with your collection name



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

        for (formModel in formModelList) {
            try {
                val task = newUser.add(formModel).await()
                if (task != null) {
                    Log.e("myuserdata", "uploaded to firebase")
                    results.add(true)
                }else{
                    Log.e("myuserdata", "null task firebase")
                }
            } catch (e: Exception) {
                Log.e("myuserdata", "could not upload to firebase", e)
                results.add(false)
            }
        }

        // Check if all tasks were successful
        return results.all { it }
    }


}