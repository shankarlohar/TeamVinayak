package com.shankarlohar.teamvinayak.ui.newuserside.component.newuser.sub

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.shankarlohar.teamvinayak.model.Disability
import com.shankarlohar.teamvinayak.model.EmergencyContact
import com.shankarlohar.teamvinayak.model.PARQ
import com.shankarlohar.teamvinayak.model.PersonalDetails
import com.shankarlohar.teamvinayak.model.UserData
import com.shankarlohar.teamvinayak.ui.common.CircularImageViewer
import com.shankarlohar.teamvinayak.viewmodel.NewUserViewModel
import java.util.Date

@ExperimentalPagerApi
@Composable
fun NewUserForm(
    viewModel: NewUserViewModel,
    navController: NavHostController,
) {
    var personalDetails by remember{ mutableStateOf(PersonalDetails())}
    var emergencyContact by remember{ mutableStateOf(EmergencyContact())}
    var disability by remember{ mutableStateOf(Disability())}
    var parq by remember{ mutableStateOf(PARQ())}
    var referral by remember{ mutableStateOf("")}

    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }


    val formSubmitted = remember{ mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val index = remember {
        mutableStateOf(0)
    }

    val context = LocalContext.current


    val uploadData = {
        selectedImageUri.let { image ->
            image.value?.let {uri ->
                    viewModel.uploadProfilePicture(uri) {
                        personalDetails = personalDetails.copy(picture = it)
                        val newUser = UserData(
                            personalDetails = personalDetails,
                            emergencyContact = emergencyContact,
                            disability = disability,
                            parq = parq,
                            referral = referral
                        )
                    }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Registration Form", style = MaterialTheme.typography.headlineLarge)
        if (formSubmitted.value){
            AlertDialog(
                onDismissRequest = {
                    formSubmitted.value = false
                },
                title = { Text(text = "Final Step")},
                text = {
                    Text("Are you sure you want to submit the form?")
                },
                confirmButton = {
                    Button(onClick = {
                        uploadData()
                        formSubmitted.value = false
                    }) {
                        Text(text = "Submit form")
                    }

                }
            )
        }
        when (index.value){
            0 -> ProfilePictureComponent(selectedImageUri = selectedImageUri)
            1 -> PersonalDetailsComponent(
                personalDetails = personalDetails,
                onPersonalDetailsChange = { updatedDetails ->
                    personalDetails = updatedDetails
                }
            )
            2 -> EmergencyContactComponent(emergencyContact = emergencyContact)
            3 -> DisabilityComponent(disability = disability)
            4 -> PARQComponent(parq = parq)
            5 -> ReferralComponent(referral = referral)
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Button(
            onClick = {
                if (index.value == 5){
                    formSubmitted.value = true
                }else {
                    index.value += 1
                }
        }) {
            Text(text = "Next")
        }
    }

}

@Composable
fun ProfilePictureComponent(
    selectedImageUri: MutableState<Uri?>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onSurface)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CircularImageViewer(
            imageUrl = selectedImageUri.toString(),
            onImageSelected = { uri ->
                selectedImageUri.value = uri
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDetailsComponent(personalDetails: PersonalDetails, onPersonalDetailsChange: (PersonalDetails) -> Unit) {
    var selectedDate by remember { mutableStateOf(Date()) }

        Column() {
            OutlinedTextField(
                value = personalDetails.fullName,
                onValueChange = { newName ->
                    onPersonalDetailsChange(personalDetails.copy(fullName = newName))
                },
                label = { Text("Full Name") }
            )

            OutlinedTextField(
                value = personalDetails.fullAddress,
                onValueChange = { newAddress ->
                    onPersonalDetailsChange(personalDetails.copy(fullAddress = newAddress))
                },
                label = { Text("Full Address") }
            )

            OutlinedTextField(
                value = personalDetails.mobile,
                onValueChange = { mobile ->
                    onPersonalDetailsChange(personalDetails.copy(mobile = mobile))
                },
                label = { Text("Mobile Number") }
            )

            OutlinedTextField(
                value = personalDetails.aadhaarNumber,
                onValueChange = {aadhaar ->
                    onPersonalDetailsChange(personalDetails.copy(aadhaarNumber = aadhaar))
                },
                label = { Text("Aadhaar Number") }
            )

            OutlinedTextField(
                value = personalDetails.email,
                onValueChange = { email ->
                    onPersonalDetailsChange(personalDetails.copy(email = email))
                },
                label = { Text("Email") }
            )

            OutlinedTextField(
                value = personalDetails.password,
                onValueChange = { password ->
                    onPersonalDetailsChange(personalDetails.copy(password = password))
                },
                label = { Text("Password") }
            )

            OutlinedTextField(
                value = personalDetails.username,
                onValueChange = { username ->
                    onPersonalDetailsChange(personalDetails.copy(username = username))
                },
                label = { Text("Username") }
            )

            OutlinedTextField(
                value = personalDetails.currentHeight.toString(),
                onValueChange = { height ->
                    onPersonalDetailsChange(personalDetails.copy(currentHeight = height.toInt()))
                },
                label = { Text("Current Height") }
            )

            OutlinedTextField(
                value = personalDetails.currentWeight.toString(),
                onValueChange = { weight ->
                    onPersonalDetailsChange(personalDetails.copy(currentWeight = weight.toInt()))
                },
                label = { Text("Current Weight") }
            )

            OutlinedTextField(
                value = personalDetails.dateOfBirth,
                onValueChange = { dob ->
                    onPersonalDetailsChange(personalDetails.copy(dateOfBirth = dob))
                },
                label = { Text("Date of Birth") }
            )

            OutlinedTextField(
                value = personalDetails.gender,
                onValueChange = { gender ->
                    onPersonalDetailsChange(personalDetails.copy(gender = gender))
                },
                label = { Text("Gender") }
            )
        }
}

@Composable
fun EmergencyContactComponent(emergencyContact: EmergencyContact) {

}

@Composable
fun DisabilityComponent(disability: Disability) {

}

@Composable
fun PARQComponent(parq: PARQ) {

}

@Composable
fun ReferralComponent(referral: String) {

}




@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun NewUserFormPreview() {
    // Create a mock ViewModel and NavController for preview
    val viewModel = NewUserViewModel() // Implement mockViewModel() to create a mock instance
    val navController = rememberNavController()

    // Call your composable with the preview parameters
    NewUserForm(
        viewModel = viewModel,
        navController = navController
    )
}

