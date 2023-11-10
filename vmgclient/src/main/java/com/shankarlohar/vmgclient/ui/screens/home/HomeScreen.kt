package com.shankarlohar.vmgclient.ui.screens.home

import androidx.compose.runtime.Composable
import com.shankarlohar.vmgclient.ui.components.home.HomeComponent

@Composable
fun HomeScreen(
    onLogoutClick: () -> Unit = {}
) {
    HomeComponent(
        onLogoutClick = onLogoutClick
    )
}