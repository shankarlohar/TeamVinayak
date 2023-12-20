package com.shankarlohar.teamvinayak.ui.newuserside.component.newuser

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
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
import com.shankarlohar.teamvinayak.model.UserData
import com.shankarlohar.teamvinayak.ui.newuserside.component.newuser.sub.About
import com.shankarlohar.teamvinayak.ui.newuserside.component.newuser.sub.AccountStatus
import com.shankarlohar.teamvinayak.ui.newuserside.component.newuser.sub.Enquiry
import com.shankarlohar.teamvinayak.util.Steps
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel
import com.shankarlohar.teamvinayak.viewmodel.ChooseUserViewModel
import com.shankarlohar.teamvinayak.viewmodel.UserViewModel

@Composable
fun JoinNowCard(
    viewModel: ChooseUserViewModel,
    navController: NavController,
    gymInfo: GymInfo,
    context: Context,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel
) {
    val openAboutDialog = remember { mutableStateOf(false) }
    val openEnquiryDialog = remember { mutableStateOf(false) }
    val openAccountBottomSheet = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.vinayak_multi_gym_no_background),
            contentDescription = stringResource(R.string.gym_name),
            modifier = Modifier.fillMaxWidth()
        )
        ElevatedAssistChip(
            onClick = { openAboutDialog.value = true },
            label = { Text("About Gym") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Info,
                    contentDescription = "About",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )

        Spacer(Modifier.padding(4.dp))
        Spacer(Modifier.padding(4.dp))

        ElevatedAssistChip(
            onClick = { navController.navigate(Steps.ONBOARD.name) },
            label = { Text("Register Now!") },
            leadingIcon = {
                Icon(
                    Icons.Filled.AccountBox,
                    contentDescription = "Register",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )

        Spacer(Modifier.padding(4.dp))

        ElevatedAssistChip(
            onClick = { openAccountBottomSheet.value = true },
            label = { Text("Account Status") },
            leadingIcon = {
                Icon(
                    Icons.Filled.AccountBox,
                    contentDescription = "Account Status",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )

    }
    About(gymInfo = gymInfo, openAboutDialog)
    Enquiry(viewModel = viewModel, openDialog = openEnquiryDialog, context = context)
    AccountStatus(
        openBottomSheet = openAccountBottomSheet,
        authViewModel = authViewModel,
        userViewModel = userViewModel
    )
}