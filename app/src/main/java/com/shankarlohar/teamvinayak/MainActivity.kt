package com.shankarlohar.teamvinayak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.shankarlohar.teamvinayak.ui.authentication.AuthViewModel
import com.shankarlohar.teamvinayak.ui.authentication.signup.OnBoardingViewModel
import com.shankarlohar.teamvinayak.ui.theme.TeamVinayakTheme


enum class MenuState {
    EXPANDED, COLLAPSED
}



class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()
    private val onBoardingViewModel: OnBoardingViewModel by viewModels()

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                mainViewModel.isLoading.value
            }
        }

        val onBoardingContent = onBoardingViewModel.onBoardingData.value


        setContent {

            TeamVinayakTheme {
                GymApp(
                    authViewModel = authViewModel,
                    onBoardingContent = onBoardingContent,
                    context = LocalContext.current
                )
            }
        }
    }
}
