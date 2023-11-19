package com.shankarlohar.teamvinayak.ui.clientside.component.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.ui.clientside.component.bottomnav.BottomNav
import com.shankarlohar.teamvinayak.ui.clientside.component.bottomnav.BottomToolbar
import com.shankarlohar.teamvinayak.ui.clientside.component.complain.ComplainComponent
import com.shankarlohar.teamvinayak.ui.clientside.component.diet.DietComponent
import com.shankarlohar.teamvinayak.ui.clientside.component.leaderboard.LeaderboardComponent
import com.shankarlohar.teamvinayak.ui.clientside.component.status.StatusComponent
import com.shankarlohar.teamvinayak.ui.clientside.component.suggestion.SuggestionComponent


@Composable
fun ProfileComponent() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val screen = remember { mutableStateOf(BottomNav.Status) }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (screen.value) {
                    BottomNav.Status -> StatusComponent()
                    BottomNav.Diet -> DietComponent()
                    BottomNav.Complain -> ComplainComponent()
                    BottomNav.Suggestion -> SuggestionComponent()
                    BottomNav.Leaderboard -> LeaderboardComponent()
                }
            }
            BottomToolbar(screen)
        }
    }
}