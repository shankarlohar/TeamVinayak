package com.shankarlohar.teamvinayak.ui.newuserside.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.GymInfo
import com.shankarlohar.teamvinayak.util.Steps
import com.shankarlohar.teamvinayak.viewmodel.ChooseUserViewModel

@Composable
fun JoinNowCard(
    viewModel: ChooseUserViewModel,
    navController: NavController,
    gymInfo: GymInfo
){
    val openAboutDialog = remember { mutableStateOf(false) }
    val openEnquiryDialog = remember { mutableStateOf(false) }
    val openRegistrationStatusDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.vinayak_multi_gym_no_background),
            contentDescription = stringResource(R.string.gym_name),
            modifier = Modifier.size(100.dp)
        )
        Row() {
            Button(onClick = {
                openAboutDialog.value = true
            }) {
                Text("About")
            }
            Spacer(Modifier.padding(4.dp))
            Button(onClick = {
                openEnquiryDialog.value = true
            }) {
                Text("Enquiry")
            }
        }
        Button(onClick = {
            navController.navigate(Steps.ONBOARD.name)
        }) {
            Text("Register Now!")
        }


        Button(onClick = {

        }) {
            Text("View Registration Status")
        }
    }
    About(gymInfo = gymInfo, openAboutDialog)
    Enquiry(viewModel = viewModel, openDialog = openEnquiryDialog)
}