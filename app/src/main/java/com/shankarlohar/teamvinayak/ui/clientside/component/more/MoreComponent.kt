package com.shankarlohar.teamvinayak.ui.clientside.component.more

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shankarlohar.teamvinayak.ui.clientside.component.bottomnav.ClientBottomToolbar
import com.shankarlohar.teamvinayak.ui.clientside.component.complain.ComplainComponent
import com.shankarlohar.teamvinayak.ui.clientside.component.diet.DietComponent
import com.shankarlohar.teamvinayak.ui.clientside.component.leaderboard.LeaderboardComponent
import com.shankarlohar.teamvinayak.ui.navigation.ClientBottomNavigation

@Composable
fun MoreComponent() {
    val bottomNavScreen = remember { mutableStateOf(ClientBottomNavigation.Complain) }

    Scaffold(
        bottomBar = {
            ClientBottomToolbar(bottomNavScreen)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            when (bottomNavScreen.value) {
                ClientBottomNavigation.Complain -> {
                    ComplainComponent()
                }
                ClientBottomNavigation.Diet -> {
                    DietComponent()
                }
                ClientBottomNavigation.Leaderboard -> {
                    LeaderboardComponent()
                }
            }
        }
    }

}