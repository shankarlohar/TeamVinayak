package com.shankarlohar.teamvinayak.data.firebase

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.util.UUID

class Storage {
    private var storage = FirebaseStorage.getInstance()


    suspend fun uploadImage(uri: Uri, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        val storageRef = storage.reference
        val imageRef = storageRef.child("images/${UUID.randomUUID()}")

        try {
            imageRef.putFile(uri).await()
        }catch (e: Exception){
            Log.d("Image Upload",e.toString())
        }finally {
            imageRef.downloadUrl.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    onSuccess(uri.toString())
                }
            }
                .addOnFailureListener {
                    onFailure(it)
                }
        }

    }
}