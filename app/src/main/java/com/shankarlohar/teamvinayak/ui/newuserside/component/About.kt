package com.shankarlohar.teamvinayak.ui.newuserside.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shankarlohar.teamvinayak.model.GymInfo

@Composable
fun About(gymInfo: GymInfo, openDialog: MutableState<Boolean>){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { openDialog.value = true }) {
            Text("Show Alert Dialog")
        }

        if (openDialog.value) {
            // Display the alert dialog when openDialog is true
            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or presses the back button
                    openDialog.value = false
                },
                title = {
                    // Display a title in the dialog
                    Text(gymInfo.name)
                },
                text = {
                    // Display the main text content of the dialog
                    Text("This is the body of the alert dialog.")
                },
                confirmButton = {
                    // Display a single confirm button with the text "OK"
                    Button(
                        onClick = {
                            // Dismiss the dialog when the user clicks the confirm button
                            openDialog.value = false
                        }
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }
}