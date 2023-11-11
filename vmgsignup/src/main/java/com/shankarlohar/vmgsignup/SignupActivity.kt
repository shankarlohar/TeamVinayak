package com.shankarlohar.vmgsignup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.shankarlohar.vmgsignup.ui.component.FormComponent
import com.shankarlohar.vmgsignup.ui.component.OnBoardingComponent
import com.shankarlohar.vmgsignup.ui.theme.TeamVinayakTheme

class SignupActivity : ComponentActivity() {

    private lateinit var signupViewModel: SignupViewModel
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signupViewModel = SignupViewModel()


        setContent {
            TeamVinayakTheme {
                // A surface container using the 'background' color from the theme
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
                            OnBoardingComponent(
                                viewModel = signupViewModel,
                                navController = navController,
                                onBackPressed = { onBackPressed() }
                            ) { finish() }
                        }
                        composable(Steps.FORM.name){
                            FormComponent()
                        }
                    }




                }
            }
        }
    }
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SignupActivity::class.java)
        }
    }
}

enum class Steps{
    ONBOARD,
    FORM
}