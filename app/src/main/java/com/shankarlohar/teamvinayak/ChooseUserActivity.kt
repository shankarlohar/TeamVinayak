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
import com.shankarlohar.teamvinayak.ui.clientside.home.HomeComponent
import com.shankarlohar.teamvinayak.ui.newuserside.ChooseUserComponent
import com.shankarlohar.teamvinayak.ui.newuserside.FailedToLoad
import com.shankarlohar.teamvinayak.ui.newuserside.NewUserFormComponent
import com.shankarlohar.teamvinayak.ui.newuserside.LoadingData
import com.shankarlohar.teamvinayak.ui.newuserside.TermsAndConditionsComponent
import com.shankarlohar.teamvinayak.ui.ownerside.OwnerHomeComponent
import com.shankarlohar.teamvinayak.ui.theme.TeamVinayakTheme
import com.shankarlohar.teamvinayak.util.Status
import com.shankarlohar.teamvinayak.util.Steps
import com.shankarlohar.teamvinayak.viewmodel.ChooseUserViewModel
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel


class ChooseUserActivity : ComponentActivity() {

    private lateinit var chooseUserViewModel: ChooseUserViewModel
    private lateinit var authViewModel: AuthViewModel

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                chooseUserViewModel.isLoading.value
            }
        }
        chooseUserViewModel = ChooseUserViewModel()
        authViewModel = AuthViewModel()

        setContent {

            TeamVinayakTheme {
                val isLoading by chooseUserViewModel.isLoading.collectAsState()

                val signupDataStatus by authViewModel.dataStatus.collectAsState()


                if (!isLoading) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = Steps.CHOICE.name
                        ){
                            composable(Steps.ONBOARD.name){
                                when (signupDataStatus) {
                                    Status.Completed -> {
                                        TermsAndConditionsComponent(
                                            viewModel = authViewModel,
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
                                        NewUserFormComponent(
                                            viewModel = authViewModel,
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
                                ChooseUserComponent(
                                    viewModel = chooseUserViewModel,
                                    navController = navController
                                )
                            }
                            composable(Steps.CLIENT.name){
                                HomeComponent(
                                    onLogoutClick = { navController.navigate(Steps.CHOICE.name) }
                                )
                            }
                            composable(Steps.OWNER.name){
                                OwnerHomeComponent()
                            }
                        }

                    }
                }
            }
        }
    }
}
