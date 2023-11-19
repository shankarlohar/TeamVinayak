package com.shankarlohar.teamvinayak.ui.clientside

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.shankarlohar.teamvinayak.ui.clientside.component.bottomnav.ClientBottomToolbar
import com.shankarlohar.teamvinayak.ui.clientside.hiddenpanel.ClientPanelComponent
import com.shankarlohar.teamvinayak.ui.navigation.ClientBottomNavigation
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel
import com.shankarlohar.teamvinayak.viewmodel.UserViewModel

@Composable
fun Client(
    authViewModel: AuthViewModel,
    navController: NavHostController,
    userViewModel: UserViewModel
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            ClientPanelComponent(
                authViewModel = authViewModel,
                navController = navController,
                userViewModel = userViewModel
            )
        }
    }


}