package com.shankarlohar.teamvinayak.ui.screens

import androidx.compose.runtime.Composable
import com.shankarlohar.teamvinayak.MainViewModel
import com.shankarlohar.teamvinayak.ui.components.ChoiceComponent

@Composable
fun ChoiceScreen(
    viewModel: MainViewModel,
){
    ChoiceComponent(viewModel = viewModel)
}