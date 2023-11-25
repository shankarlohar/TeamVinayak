package com.shankarlohar.teamvinayak.data.firebase

import android.util.Log
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
        Log.d("bootomsheetlogin","in auth")
       auth.signInWithEmailAndPassword(email, password)
           .addOnCompleteListener { task ->
               Log.d("bootomsheetlogin","signed int with ->" + auth.uid)
               if (task.isSuccessful) {
                   onResult(true, auth.uid) // Sign-in successful
               } else {
                   onResult(false, task.exception?.message ?: "Login failed")
               }
           }
           .addOnFailureListener {
               Log.d("bootomsheetlogin",it.toString())
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
}