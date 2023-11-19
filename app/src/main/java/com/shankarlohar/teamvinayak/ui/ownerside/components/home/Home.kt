package com.shankarlohar.teamvinayak.ui.ownerside.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shankarlohar.teamvinayak.ui.navigation.OwnerBottomNavigation
import com.shankarlohar.teamvinayak.ui.ownerside.components.bottomnav.OwnerBottomToolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeComponent() {

    val bottomNavScreen = remember { mutableStateOf(OwnerBottomNavigation.Attendance) }


    Scaffold(
        bottomBar ={

            OwnerBottomToolbar(bottomNavScreen)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (bottomNavScreen.value) {
                OwnerBottomNavigation.Attendance -> {
                    Text(text = "Attendance")
                }
                OwnerBottomNavigation.Message -> {
                    Text(text = "Expense")
                }
                OwnerBottomNavigation.Notification -> {
                    Text(text = "Add a visitor")
                }
                OwnerBottomNavigation.Payment -> {
                    Text(text = "Monthly Status")
                }
                OwnerBottomNavigation.Profile -> {
                    Text(text = "Add a new Member")
                }
            }

        }
    }

}