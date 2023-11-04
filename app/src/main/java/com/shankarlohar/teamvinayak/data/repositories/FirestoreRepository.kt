package com.shankarlohar.teamvinayak.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.shankarlohar.teamvinayak.model.GymInfoModel
import com.shankarlohar.teamvinayak.model.OnBoardingModel
import kotlinx.coroutines.tasks.await

class FirestoreRepository {
    private val db = FirebaseFirestore.getInstance()
    private val gymInfoDocument = db.collection("vmg").document("info")
    private val tncList = db.collection("register").document("terms-and-conditions")
    suspend fun getGymInfoDocument(): GymInfoModel? {
        val snapshot = gymInfoDocument.get().await()
        return snapshot.toObject(GymInfoModel::class.java)
    }

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
}