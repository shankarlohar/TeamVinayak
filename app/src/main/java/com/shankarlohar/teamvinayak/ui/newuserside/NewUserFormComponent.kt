package com.shankarlohar.teamvinayak.ui.newuserside

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.shankarlohar.teamvinayak.model.UserData
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel

@Preview
@ExperimentalPagerApi
@Composable
fun NewUserFormComponent(
//    viewModel: AuthViewModel,
//    navController: NavHostController,
) {
    val userData by remember{ mutableStateOf(UserData()) }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "this is it")



    }



}
