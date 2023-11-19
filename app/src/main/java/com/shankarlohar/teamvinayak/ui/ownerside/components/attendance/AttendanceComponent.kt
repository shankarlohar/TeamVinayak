package com.shankarlohar.teamvinayak.ui.ownerside.components.attendance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel

@Composable
fun AttendanceComponent(
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //add some content here
        Icon(
            Icons.Filled.Home,
            contentDescription = stringResource(R.string.home)
        )
    }
}
