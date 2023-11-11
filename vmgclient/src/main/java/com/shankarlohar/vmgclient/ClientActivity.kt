package com.shankarlohar.vmgclient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.shankarlohar.vmgclient.ui.theme.TeamVinayakTheme

class ClientActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val email = intent.getStringExtra("email")
        val pass = intent.getStringExtra("pass")

        setContent {
            TeamVinayakTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ClientApp(
                        email = email.toString(),
                        pass = pass.toString()
                    )
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context, email: String, pass: String): Intent {
            return Intent(context, ClientActivity::class.java)
                .putExtra("email",email)
                .putExtra("pass",pass)
        }
    }
}
