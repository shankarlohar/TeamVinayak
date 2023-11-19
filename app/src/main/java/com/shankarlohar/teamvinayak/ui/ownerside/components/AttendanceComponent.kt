package com.shankarlohar.teamvinayak.ui.ownerside.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel

@Composable
fun AttendanceComponent(
    authViewModel: AuthViewModel, navController: NavHostController
){
    val ownerName by authViewModel.admin.collectAsState()

    Surface(Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("This is $ownerName Side")
        }
    }
}