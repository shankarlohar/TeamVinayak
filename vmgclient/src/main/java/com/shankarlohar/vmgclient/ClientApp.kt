package com.shankarlohar.vmgclient

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shankarlohar.vmgclient.ui.screens.home.HomeScreen
import com.shankarlohar.vmgclient.ui.screens.login.LoginScreen
import com.shankarlohar.vmgclient.ui.utils.Screen

@Composable
fun ClientApp(
    navHostController: NavHostController = rememberNavController(),
){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navHostController,
            startDestination = Screen.LOGIN.name,
        ){

            composable(route = Screen.LOGIN.name){
                LoginScreen(
                    onLoginClick = { navHostController.navigate(Screen.HOME.name) }
                )
            }

            composable(route = Screen.HOME.name){
                HomeScreen(
                onLogoutClick = { navHostController.navigate(Screen.LOGIN.name) }
                )
            }
        }
    }
}



