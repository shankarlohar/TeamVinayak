package com.shankarlohar.teamvinayak.ui.clientside.component.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.ui.clientside.component.bottomnav.ClientBottomToolbar
import com.shankarlohar.teamvinayak.ui.navigation.ClientBottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreComponent() {
    val bottomNavScreen = remember { mutableStateOf(ClientBottomNavigation.Status) }

    Scaffold(
        bottomBar = {
            ClientBottomToolbar(bottomNavScreen)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            when (bottomNavScreen.value) {
                ClientBottomNavigation.Status -> {
                    Text(text = "Status")
                }
                ClientBottomNavigation.Complain -> {
                    Text(text = "Complain")
                }
                ClientBottomNavigation.Diet -> {
                    Text(text = "Diet")
                }
                ClientBottomNavigation.Suggestion -> {
                    Text(text = "Suggestion")
                }
                ClientBottomNavigation.Leaderboard -> {
                    Text(text = "Leaderboard")
                }
            }
        }
    }

}