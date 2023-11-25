package com.shankarlohar.teamvinayak.data.firebase

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.shankarlohar.teamvinayak.model.Enquiry
import com.shankarlohar.teamvinayak.model.GymInfo
import com.shankarlohar.teamvinayak.model.TermsAndConditionsModel
import com.shankarlohar.teamvinayak.model.UserData
import com.shankarlohar.teamvinayak.util.Utils.getCurrentDate
import kotlinx.coroutines.tasks.await

class FirestoreDatabase {
    private val db = FirebaseFirestore.getInstance()

    private val tncList = db.collection("register").document("terms-and-conditions")

    private val gym = db.collection("vmg")

    private val enquiries = db.collection("enquiry")

    private val accounts = db.collection("accounts")

    suspend fun updateGymInfo() {
        gym.document("info").update("totalMembers",FieldValue.increment(1)).await()
    }

    suspend fun getGymInfo(): GymInfo {
        val gymDocRef = gym.document("info")
        var gymInfoLiveData = GymInfo()

        val gymRef = gymDocRef.get().await()
        if (gymRef.data != null) {
            val gymData = gymRef.toObject(GymInfo::class.java)
            if (gymData != null) {
                gymInfoLiveData = gymData
            } else {
                // Handle the case where mapping to GymData failed
                Log.d("gyminfo","isempty")
            }
        } else {
            // Handle the case where the document does not exist
            Log.d("gyminfo","does not exist")
        }

        return gymInfoLiveData
    }

    suspend fun getUserData(uid: String): UserData{
        val userUidRef = accounts.document(uid)
        var userData = UserData()

        val userRef = userUidRef.get().await()

        if (userRef.data != null){
            val userFetchedData = userRef.toObject(UserData::class.java)
            if (userFetchedData != null){
                userData = userFetchedData
            }else {
                // Handle the case where mapping to GymData failed
                Log.d("userFetchedData","isempty")
            }
        }
        else {
            // Handle the case where the document does not exist
            Log.d("userFetchedData","does not exist")
        }
        return userData
    }


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



    suspend fun createNewUser(userData: UserData): Boolean {
        val results = mutableListOf<Boolean>()

        val authentication = Authentication()


        try {
            // register user
            Log.d("formSubmit","registering user")
            authentication.registerUser(userData.personalDetails.email,userData.personalDetails.password).onSuccess { uid ->
                Log.d("formSubmit", "registration complete ->$uid")
                // use the uid to add the details
                val updatedUserData = userData.copy(uid = uid).copy(membership = userData.membership.copy(formSubmission = getCurrentDate()))
                Log.d("formSubmit","added uid to updatedUserData -> ${updatedUserData.uid}")
                Log.d("formSubmit","uploading userdata to firebase")
                accounts.document(uid).set(updatedUserData).await()
                Log.d("formSubmit","firebase userdata upload complete")

            }
            results.add(true)
        } catch (e: Exception) {
            results.add(false)
        }

        return results.all { it }
    }


    suspend fun saveEnquiryQuestion(enquiry: Enquiry, onDone: (Boolean) -> Unit) {
        val doc = enquiries.document(enquiry.phone)
        try {
            doc.set(enquiry).await()
        }catch (e:Exception){
            onDone(false)
        }finally {
            onDone(true)
        }
    }


}