package com.shankarlohar.teamvinayak.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.shankarlohar.teamvinayak.model.ToSubmitFormModel
import com.shankarlohar.teamvinayak.model.TermsAndConditionsModel
import com.shankarlohar.teamvinayak.model.SignupFormModel
import kotlinx.coroutines.tasks.await

class FirestoreDatabase {
    private val db = FirebaseFirestore.getInstance()

    private val tncList = db.collection("register").document("terms-and-conditions")
    private val signupFormFields = db.collection("register").document("form")

    private val newUser = db.collection("user") // Replace with your collection name



    suspend fun getTnC(): List<TermsAndConditionsModel>{
        val snapshot = tncList.get().await()

        if (snapshot.exists()) {
            val data = snapshot.data
            val tncList = mutableListOf<TermsAndConditionsModel>()

            if (data != null) {
                for (key in data.keys) {
                    if (data[key] is List<*>) {
                        val content = (data[key] as List<String>)
                        val termsAndConditionsModel = TermsAndConditionsModel(key, content)
                        tncList.add(termsAndConditionsModel)
                    }
                }
            }

            return tncList
        }

        return emptyList()
    }

    suspend fun getNewUserForm(): List<SignupFormModel>{
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

    suspend fun createNewUser(toSubmitFormModelList: List<ToSubmitFormModel>): Boolean {
        val results = mutableListOf<Boolean>()
        val userSections = mutableMapOf<String, Map<String, String>>()

        val auth = Authentication()

        for (formModel in toSubmitFormModelList) {
            val sectionData = userSections.getOrPut(formModel.field) { emptyMap() }
            val newData = formModel.data.associate { it.first to it.second }
            userSections[formModel.field] = sectionData + newData
        }

            try {
                userSections["1. Personal Details"]?.get("Create a password")?.let {password ->
                    userSections["1. Personal Details"]?.get("Email")?.let { email ->
                        auth.registerUser(email, password).onSuccess { uid ->

                            val newUserDoc = newUser.document(uid)
                            newUserDoc.set(userSections).await()

                        }
                    }
                }
                results.add(true)
            } catch (e: Exception) {
                results.add(false)
            }

        return results.all { it }
    }




}