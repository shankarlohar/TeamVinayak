package com.shankarlohar.teamvinayak.repository

import com.shankarlohar.teamvinayak.data.firebase.FirestoreDatabase
import com.shankarlohar.teamvinayak.model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataRepository {

    private val firestoreDatabase = FirestoreDatabase()


    suspend fun getUserData(uid: String): UserData{
        return withContext(Dispatchers.Main){
            return@withContext firestoreDatabase.getUserData(uid)
        }
    }
}