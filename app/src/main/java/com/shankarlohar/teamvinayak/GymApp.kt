package com.shankarlohar.teamvinayak

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shankarlohar.teamvinayak.ui.authentication.LoginPage
import com.shankarlohar.teamvinayak.ui.authentication.SignupPage
import com.shankarlohar.teamvinayak.ui.home.HomeComponent

enum class Screens{
    HOME,
    LOGIN,
    SIGNUP
}

@Composable
fun GymApp(
    navHostController: NavHostController = rememberNavController()
){
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navHostController,
            startDestination = Screens.LOGIN.name,
        ){
            composable(route = Screens.LOGIN.name){
                LoginPage(
                    onJoinNowClick = {
                        navHostController.navigate(Screens.SIGNUP.name)
                    },
                    onStartClick = {
                        navHostController.navigate(Screens.HOME.name)
                    }
                )
            }

            composable(route = Screens.HOME.name){
                HomeComponent(
                    onLogoutClick = {
                        navHostController.navigate(Screens.LOGIN.name)
                    }
                )
            }

            composable(route = Screens.SIGNUP.name){
                SignupPage()
            }
        }
    }
}