package com.shankarlohar.teamvinayak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.shankarlohar.teamvinayak.ui.authentication.LoginPage
import com.shankarlohar.teamvinayak.ui.authentication.LoginViewModel
import com.shankarlohar.teamvinayak.ui.authentication.OnBoarding
import com.shankarlohar.teamvinayak.ui.home.HomeComponent
import com.shankarlohar.teamvinayak.ui.theme.TeamVinayakTheme


enum class MenuState {
    EXPANDED, COLLAPSED
}



class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                mainViewModel.isLoading.value
            }
        }

        setContent {
            TeamVinayakTheme {
                GymApp(
                    loginViewModel = loginViewModel
                )
            }
        }
    }
}
