package com.shankarlohar.teamvinayak.repository

import com.shankarlohar.teamvinayak.data.firebase.FirestoreDatabase
import com.shankarlohar.teamvinayak.model.Attendance
import com.shankarlohar.teamvinayak.model.Notification
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

    suspend fun getNotifications():List<Notification>{
        return withContext(Dispatchers.Main){
            return@withContext firestoreDatabase.getNotifications()
        }
    }

    suspend fun getUserEmail(username: String): String{
        return withContext(Dispatchers.Main){
            return@withContext firestoreDatabase.getEmail(username)
        }
    }

    suspend fun uploadAttendance(userAttendance: Attendance, uid: String, onDone: (Boolean) -> Unit ){
        return withContext(Dispatchers.Main){
            return@withContext firestoreDatabase.uploadAttendance(userAttendance,uid,onDone)
        }
    }

    suspend fun fetchTodaysAttendance(uid: String, onDone: (Boolean) -> Unit ): Attendance{
        return withContext(Dispatchers.Main){
            return@withContext firestoreDatabase.fetchTodaysAttendance(uid,onDone)
        }
    }


}