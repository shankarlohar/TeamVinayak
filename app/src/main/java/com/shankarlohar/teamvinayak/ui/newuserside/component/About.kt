package com.shankarlohar.teamvinayak.ui.newuserside.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.shankarlohar.teamvinayak.R
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
        // Use rememberImagePainter to load the image from the URL
        val painter = rememberAsyncImagePainter(ImageRequest.Builder // Image to display in case of an error
                (LocalContext.current).data(data = gymInfo.logo).apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {
                crossfade(true)
                placeholder(R.drawable.vinayak_multi_gym_no_background) // Placeholder image while loading
                error(R.drawable.maintanance) // Image to display in case of an error
                scale(Scale.FILL) // Scale type
            }).build()
            )
        val modifier = Modifier.padding(4.dp)
        val fontSize = 12.sp
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
                    Column {
                        Text(gymInfo.genderPreference)
                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                                .clip(MaterialTheme.shapes.medium)
                                .padding(4.dp),
                            contentScale = ContentScale.Fit
                        )
                        Text(gymInfo.address)
                        Spacer(modifier = modifier)
                        Text("Email: " + gymInfo.email)
                        Spacer(modifier = modifier)
                        Row(modifier = modifier.fillMaxWidth()) {
                            Text("Phone: ", fontSize = fontSize)
                            Text(gymInfo.phone[0],fontSize = fontSize)
                            Spacer(modifier = modifier)
                            Text(gymInfo.phone[1],fontSize = fontSize)
                            Spacer(modifier = modifier)
                            Text(gymInfo.phone[2],fontSize = fontSize)
                        }
                        Spacer(modifier = modifier)
                        Spacer(modifier = modifier)

                        Text("Late Fee: " + gymInfo.lateFee.toString())
                        Spacer(modifier = modifier)
                        Text("Locker Rental: " + gymInfo.lockerRental.toString())
                        Spacer(modifier = modifier)
                        Text("Monthly Fee: " + gymInfo.monthlyFee.toString())
                        Spacer(modifier = modifier)
                        Text("Membership holding fee: " + gymInfo.membershipHoldingFree.toString())
                        Spacer(modifier = modifier)
                        Text(if(gymInfo.openToday) "Gym is open today." else "Gym is closed today.")
                        Spacer(modifier = modifier)
                        Column(modifier = modifier.fillMaxWidth()) {
                            Text(gymInfo.timing[0],fontSize = fontSize)
                            Spacer(modifier = modifier)
                            Text(gymInfo.timing[1],fontSize = fontSize)
                        }
                    }
                },
                confirmButton = {
                    // Display a single confirm button with the text "OK"
                    Button(
                        onClick = {
                            // Dismiss the dialog when the user clicks the confirm button
                            openDialog.value = false
                        }
                    ) {
                        Text("Close")
                    }
                }
            )
        }
    }
}