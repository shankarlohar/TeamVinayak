package com.shankarlohar.teamvinayak.repository

import com.shankarlohar.teamvinayak.data.FirestoreDatabase
import com.shankarlohar.teamvinayak.model.OnBoardingModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OnBoardingRepository {

    private val firestoreDatabase = FirestoreDatabase()

    suspend fun getOnBoardingData(): List<OnBoardingModel> {
        return withContext(Dispatchers.IO) {
            return@withContext firestoreDatabase.getTnC().sortedBy { it.section }
        }
    }
}