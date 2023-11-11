package com.shankarlohar.teamvinayak

import android.content.Context
import android.content.Intent
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
import com.shankarlohar.teamvinayak.ui.components.ChoiceComponent
import com.shankarlohar.teamvinayak.ui.theme.TeamVinayakTheme
import com.shankarlohar.vmgsignup.SignupActivity

class ChoiceActivity : ComponentActivity() {

    private lateinit var choiceScreenViewModel: ChoiceScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                choiceScreenViewModel.isLoading.value
            }
        }
        choiceScreenViewModel = ChoiceScreenViewModel()
        setContent {

            TeamVinayakTheme {
                val isLoading by choiceScreenViewModel.isLoading.collectAsState()

                if (!isLoading) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ChoiceComponent(
                            viewModel = choiceScreenViewModel,
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, ChoiceActivity::class.java)
        }
    }
}
