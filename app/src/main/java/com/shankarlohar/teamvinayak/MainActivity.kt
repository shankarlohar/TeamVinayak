package com.shankarlohar.teamvinayak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.shankarlohar.teamvinayak.data.repositories.FirestoreRepository
import com.shankarlohar.teamvinayak.ui.screens.ChoiceScreen
import com.shankarlohar.teamvinayak.ui.theme.TeamVinayakTheme

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                mainViewModel.isLoading.value
            }
        }

        val firestoreRepository = FirestoreRepository()
        mainViewModel = MainViewModel(firestoreRepository)




        setContent {

            TeamVinayakTheme {
                val isLoading by mainViewModel.isLoading.collectAsState()

                if (!isLoading) {
                    GymApp(
                        mainViewModel = mainViewModel,
                        context = LocalContext.current
                    )
                }
            }
        }
    }
}
