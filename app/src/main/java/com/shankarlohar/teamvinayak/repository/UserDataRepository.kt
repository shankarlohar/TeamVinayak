package com.shankarlohar.teamvinayak.repository

import com.shankarlohar.teamvinayak.data.firebase.FirestoreDatabase
import com.shankarlohar.teamvinayak.model.User

class UserDataRepository {

    private val firestoreDatabase = FirestoreDatabase()


    suspend fun getUserPersonalDetails(uid: String): User{
        return firestoreDatabase.getUserPersonalDetails(uid)
    }
}