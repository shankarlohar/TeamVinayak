package com.shankarlohar.teamvinayak.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun DashboardComponent() {
    val context = LocalContext.current

    Column(modifier = Modifier) {
        Text(text = "something")
    }
}
