package com.shankarlohar.teamvinayak.data

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class Authentication {
    // Firebase authentication instance
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Function to register a user with email and password
    suspend fun registerUser(email: String, password: String): Result<String> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()

            Result.success(auth.uid.toString())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}