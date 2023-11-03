package com.shankarlohar.teamvinayak.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.shankarlohar.teamvinayak.data.model.GymInfoModel
import kotlinx.coroutines.tasks.await

class FirestoreRepository {
    private val db = FirebaseFirestore.getInstance()
    private val gymInfoDocument = db.collection("vmg").document("info")

    suspend fun getGymInfoDocument(): GymInfoModel? {
        val snapshot = gymInfoDocument.get().await()
        return snapshot.toObject(GymInfoModel::class.java)
    }
}