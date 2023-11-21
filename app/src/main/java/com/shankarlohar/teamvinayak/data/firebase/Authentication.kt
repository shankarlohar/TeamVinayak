package com.shankarlohar.teamvinayak.data.firebase

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

    fun loginMember(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
       auth.signInWithEmailAndPassword(email, password)
           .addOnCompleteListener { task ->
               if (task.isSuccessful) {
                   onResult(true, null) // Sign-in successful
               } else {
                   onResult(false, task.exception?.message ?: "Login failed")
               }
           }
    }

    fun logoutMember(onResult: (Boolean) -> Unit) {
            try {
                auth.signOut()
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }

    fun getUid(): String {
        return auth.uid.toString()
    }



}