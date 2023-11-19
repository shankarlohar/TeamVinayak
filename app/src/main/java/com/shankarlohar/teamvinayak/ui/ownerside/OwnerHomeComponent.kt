package com.shankarlohar.teamvinayak.ui.ownerside

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shankarlohar.teamvinayak.ui.ownerside.components.AttendanceComponent
import com.shankarlohar.teamvinayak.ui.ownerside.components.BottomToolbar
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel

@Composable
fun OwnerHomeComponent(authViewModel: AuthViewModel, navController: NavHostController) {

    val screen = remember { mutableStateOf(BottomNav.Attendance) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (screen.value) {
                    BottomNav.Attendance -> AttendanceComponent(authViewModel,navController)
                    else -> {
                        Text(
                            text = "Coming Soon",
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
            BottomToolbar(screen)
        }
    }
}


