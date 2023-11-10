package com.shankarlohar.vmgclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.shankarlohar.vmgclient.ui.components.home.HomeComponent
import com.shankarlohar.vmgclient.ui.screens.login.LoginScreen
import com.shankarlohar.vmgclient.ui.theme.TeamVinayakTheme

class ClientActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamVinayakTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ClientApp()
                }
            }
        }
    }
}
