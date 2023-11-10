package com.shankarlohar.teamvinayak

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.shankarlohar.teamvinayak.ui.screens.ChoiceScreen
import com.shankarlohar.teamvinayak.ui.signup.OnBoarding

enum class Screens{
    CHOICES,
    SIGNUP,
    ONBOARDING
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GymApp(
    mainViewModel: MainViewModel,
    navHostController: NavHostController = rememberNavController(),
    context: Context,
){
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navHostController,
            startDestination = Screens.CHOICES.name,
        ){
            composable(route = Screens.CHOICES.name){
                ChoiceScreen(
                    viewModel = mainViewModel
                )
            }

            composable(route = Screens.ONBOARDING.name){
                OnBoarding(
                    mainViewModel = mainViewModel,
                    onJoinClick = {
                        navHostController.navigate(Screens.SIGNUP.name)
                    },
                    onBackToLoginClick = {
                        navHostController.navigate(Screens.SIGNUP.name)
                    },
                    context = context
                )
            }
        }
    }
}