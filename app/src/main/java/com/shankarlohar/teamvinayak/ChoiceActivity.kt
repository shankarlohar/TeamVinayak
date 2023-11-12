package com.shankarlohar.teamvinayak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.shankarlohar.teamvinayak.ui.components.ChoiceComponent
import com.shankarlohar.teamvinayak.ui.components.FailedToLoad
import com.shankarlohar.teamvinayak.ui.components.FormComponent
import com.shankarlohar.teamvinayak.ui.components.LoadingData
import com.shankarlohar.teamvinayak.ui.components.OnBoardingComponent
import com.shankarlohar.teamvinayak.ui.theme.TeamVinayakTheme
import com.shankarlohar.teamvinayak.util.Status
import com.shankarlohar.teamvinayak.util.Steps
import com.shankarlohar.teamvinayak.viewmodel.ChoiceScreenViewModel
import com.shankarlohar.teamvinayak.viewmodel.SignupViewModel

class ChoiceActivity : ComponentActivity() {

    private lateinit var choiceScreenViewModel: ChoiceScreenViewModel
    private lateinit var signupViewModel: SignupViewModel

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                choiceScreenViewModel.isLoading.value
            }
        }
        choiceScreenViewModel = ChoiceScreenViewModel()
        signupViewModel = SignupViewModel()

        setContent {

            TeamVinayakTheme {
                val isLoading by choiceScreenViewModel.isLoading.collectAsState()

                val signupDataStatus by signupViewModel.dataStatus.collectAsState()


                if (!isLoading) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = Steps.ONBOARD.name
                        ){
                            composable(Steps.ONBOARD.name){
                                when (signupDataStatus) {
                                    Status.Completed -> {
                                        OnBoardingComponent(
                                            viewModel = signupViewModel,
                                            navController = navController,
                                        )
                                    }
                                    Status.Failed -> {
                                        FailedToLoad()
                                    }
                                    else -> {
                                        LoadingData()
                                    }
                                }

                            }
                            composable(Steps.FORM.name){
                                when (signupDataStatus) {
                                    Status.Completed -> {
                                        FormComponent(
                                            viewModel = signupViewModel,
                                            navController = navController,
                                        )
                                    }
                                    Status.Failed -> {
                                        FailedToLoad()
                                    }
                                    else -> {
                                        LoadingData()
                                    }

                                }
                            }
                            composable(Steps.CHOICE.name){
                                ChoiceComponent(
                                    viewModel = choiceScreenViewModel,
                                    navController = navController
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}
