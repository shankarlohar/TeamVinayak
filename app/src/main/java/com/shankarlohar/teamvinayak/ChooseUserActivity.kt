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
import com.shankarlohar.teamvinayak.ui.clientside.Client
import com.shankarlohar.teamvinayak.ui.newuserside.ChooseUserComponent
import com.shankarlohar.teamvinayak.ui.newuserside.FailedToLoad
import com.shankarlohar.teamvinayak.ui.newuserside.NewUserFormComponent
import com.shankarlohar.teamvinayak.ui.newuserside.LoadingData
import com.shankarlohar.teamvinayak.ui.newuserside.TermsAndConditionsComponent
import com.shankarlohar.teamvinayak.ui.ownerside.hiddenpanel.OwnerPanelComponent
import com.shankarlohar.teamvinayak.ui.theme.TeamVinayakTheme
import com.shankarlohar.teamvinayak.util.Status
import com.shankarlohar.teamvinayak.util.Steps
import com.shankarlohar.teamvinayak.viewmodel.ChooseUserViewModel
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel
import com.shankarlohar.teamvinayak.viewmodel.UserViewModel


class ChooseUserActivity : ComponentActivity() {

    private lateinit var chooseUserViewModel: ChooseUserViewModel
    private lateinit var authViewModel: AuthViewModel
    private lateinit var userViewModel: UserViewModel

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
        userViewModel = UserViewModel()


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
                                    authViewModel = authViewModel,
                                    navController = navController
                                )
                            }
                            composable(Steps.CLIENT.name){
                                Client(
                                    authViewModel = authViewModel,
                                    navController = navController,
                                    userViewModel = userViewModel
                                )
                            }
                            composable(Steps.OWNER.name){
                                OwnerPanelComponent(
                                    authViewModel = authViewModel,
                                    navController = navController,
                                    userViewModel = userViewModel
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}
