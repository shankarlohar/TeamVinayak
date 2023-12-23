package com.shankarlohar.teamvinayak.ui.newuserside.component.newuser.sub

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.Disability
import com.shankarlohar.teamvinayak.model.EmergencyContact
import com.shankarlohar.teamvinayak.model.PARQ
import com.shankarlohar.teamvinayak.model.PersonalDetails
import com.shankarlohar.teamvinayak.model.UserData
import com.shankarlohar.teamvinayak.ui.common.CircularImageViewer
import com.shankarlohar.teamvinayak.ui.common.DateInput
import com.shankarlohar.teamvinayak.ui.common.InputChipWithAvatar
import com.shankarlohar.teamvinayak.util.Steps
import com.shankarlohar.teamvinayak.util.UiStatus
import com.shankarlohar.teamvinayak.viewmodel.NewUserViewModel

@ExperimentalPagerApi
@Composable
fun NewUserForm(
    viewModel: NewUserViewModel,
    navController: NavHostController,
) {
    var personalDetails by remember { mutableStateOf(PersonalDetails()) }
    var emergencyContact by remember { mutableStateOf(EmergencyContact()) }
    var disability by remember { mutableStateOf(Disability()) }
    var parq by remember { mutableStateOf(PARQ()) }
    var referral by remember { mutableStateOf("") }

    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }


    val showFormSubmissionDialog = remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val index = remember {
        mutableIntStateOf(0)
    }

    val formSubmitStatus = remember {
        mutableStateOf(UiStatus.Completed)
    }

    val componentPersonalDetails = remember {
        mutableStateOf(0)
    }

    val context = LocalContext.current

    val compositionRegistration by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.registration))


    val uploadData = {
        selectedImageUri.let { image ->
            image.value?.let { uri ->
                viewModel.uploadProfilePicture(uri) {
                    Log.d("formSubmit", "image uploaded url -> $it")
                    personalDetails = personalDetails.copy(picture = it)
                    Log.d("formSubmit", "image url on personalDetails ->" + personalDetails.picture)
                    val newUser = UserData(
                        personalDetails = personalDetails,
                        emergencyContact = emergencyContact,
                        disability = disability,
                        parq = parq,
                        referral = referral
                    )
                    Log.d("formSubmit", "userData() created ->" + newUser.personalDetails.fullName)
                    viewModel.createAccount(newUser) { status ->
                        Log.d("formSubmit", "Account Created ->$status")
                        if (status) formSubmitStatus.value = UiStatus.Completed
                        else formSubmitStatus.value = UiStatus.Failed
                    }
                }
            }
        }
    }


    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    if ((index.value == 1) && (componentPersonalDetails.value != 9)) {
                        componentPersonalDetails.value += 1
                    } else if (index.value == 5) {
                        formSubmitStatus.value = UiStatus.Loading
                        showFormSubmissionDialog.value = true
                        Log.d("formSubmit", "uploadData() started")
                        uploadData()
                        Log.d("formSubmit", "uploadData() finished")
                    } else {
                        index.value += 1
                    }
                },
                icon = {
                    Icon(
                        if (index.value == 5) Icons.Filled.Person else Icons.Filled.ArrowForward,
                        "next button"
                    )
                },
                text = { Text(text = if (index.value == 5) "Submit" else "Next") },
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState)
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Registration Form", style = MaterialTheme.typography.headlineLarge)

            LottieAnimation(
                modifier = Modifier.size(150.dp),
                composition = compositionRegistration,
                iterations = LottieConstants.IterateForever,
            )

            if (showFormSubmissionDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        showFormSubmissionDialog.value = false
                    },
                    title = {
                        Text(
                            text =
                            when (formSubmitStatus.value) {
                                UiStatus.Loading -> "Loading..."
                                UiStatus.Failed -> "Failed"
                                else -> "Final Step"
                            }

                        )
                    },
                    text = {
                        when (formSubmitStatus.value) {
                            UiStatus.Loading -> Text("Wait for a few seconds.")
                            UiStatus.Failed -> Text("Submission was not succeeded.")
                            else -> Column() {
                                Text("Your form is under review.")
                                Text("Please submit a copy of Aadhaar at the gym and ask for approval.")
                            }
                        }

                    },
                    confirmButton = {
                        Button(onClick = {
                            showFormSubmissionDialog.value = false
                            navController.navigate(Steps.CHOICE.name)
                        }) {
                            Text(text = "Okay")
                        }

                    }
                )
            }
            when (index.value) {
                0 -> ProfilePictureComponent(
                    selectedImageUri = selectedImageUri
                )

                1 -> PersonalDetailsComponent(
                    personalDetails = personalDetails,
                    onPersonalDetailsChange = { updatedDetails ->
                        personalDetails = updatedDetails
                    },
                    componentPersonalDetails = componentPersonalDetails
                )

                2 -> EmergencyContactComponent(
                    emergencyContact = emergencyContact,
                    onEmergencyContactChange = { updatedDetails ->
                        emergencyContact = updatedDetails

                    }
                )

                3 -> DisabilityComponent(
                    disability = disability,
                    onDisabilityChange = { updatedDetails ->
                        disability = updatedDetails

                    }
                )

                4 -> PARQComponent(
                    parq = parq,
                    onParqChange = { updatedDetails ->
                        parq = updatedDetails
                    }
                )

                5 -> ReferralComponent(
                    referral = referral,
                    onReferralChange = { updatedDetails ->
                        referral = updatedDetails
                    }
                )
            }
        }
    }

}

@Composable
fun ProfilePictureComponent(
    selectedImageUri: MutableState<Uri?>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(text = "Upload a Professional Photo")
        Spacer(modifier = Modifier.padding(48.dp))
        CircularImageViewer(
            imageUrl = selectedImageUri.toString(),
            onImageSelected = { uri ->
                selectedImageUri.value = uri
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDetailsComponent(
    personalDetails: PersonalDetails,
    onPersonalDetailsChange: (PersonalDetails) -> Unit,
    componentPersonalDetails: MutableState<Int>
) {
    val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    val date = remember {
        mutableStateOf(state)
    }
    val gender = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(text = "Personal Details", style = MaterialTheme.typography.headlineSmall)
        Text(
            text = "All fields are required and important",
            style = MaterialTheme.typography.bodySmall
        )
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {

            when (componentPersonalDetails.value) {
                0 -> {
                    OutlinedTextField(
                        value = personalDetails.aadhaarNumber,
                        onValueChange = { aadhaar ->
                            onPersonalDetailsChange(personalDetails.copy(aadhaarNumber = aadhaar))
                        },
                        label = { Text("Aadhaar Number") }
                    )
                }

                1 -> {
                    Text(
                        text = "Make sure this does not collide with existing members",
                        style = MaterialTheme.typography.bodySmall
                    )
                    OutlinedTextField(
                        value = personalDetails.email,
                        onValueChange = { email ->
                            onPersonalDetailsChange(personalDetails.copy(email = email))
                        },
                        label = { Text("Email") }
                    )
                }

                2 -> {
                    OutlinedTextField(
                        value = personalDetails.mobile,
                        onValueChange = { mobile ->
                            onPersonalDetailsChange(personalDetails.copy(mobile = mobile))
                        },
                        label = { Text("Mobile Number") }
                    )
                }

                3 -> {
                    OutlinedTextField(
                        value = personalDetails.fullName,
                        onValueChange = { newName ->
                            onPersonalDetailsChange(personalDetails.copy(fullName = newName))
                        },
                        label = { Text("Full Name") }
                    )
                }

                4 -> {
                    OutlinedTextField(
                        value = personalDetails.profession,
                        onValueChange = { profession ->
                            onPersonalDetailsChange(personalDetails.copy(profession = profession))
                        },
                        label = { Text("Your Profession") }
                    )
                }

                5 -> {
                    OutlinedTextField(
                        value = personalDetails.fullAddress,
                        onValueChange = { newAddress ->
                            onPersonalDetailsChange(personalDetails.copy(fullAddress = newAddress))
                        },
                        label = { Text("Full Address") },
                        singleLine = false
                    )
                }

                6 -> {
                    DateInput(
                        title = { Text("Date of Birth") },
                        date = date,
                        onValueChange = { date ->
                            onPersonalDetailsChange(
                                personalDetails.copy(
                                    dateOfBirth = date.substring(
                                        0,
                                        11
                                    )
                                )
                            )
                        }
                    )
                }

                7 -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Gender")
                        InputChipWithAvatar(
                            currentSelected = gender.value,
                            selected = gender,
                            icon = Icons.Filled.Person,
                            text = "Male",
                            onValueChange = { gender ->
                                onPersonalDetailsChange(personalDetails.copy(gender = gender))
                            }
                        )
                        InputChipWithAvatar(
                            currentSelected = !gender.value,
                            selected = gender,
                            icon = Icons.Filled.Person,
                            text = "Female",
                            onValueChange = { gender ->
                                onPersonalDetailsChange(personalDetails.copy(gender = gender))
                            }
                        )
                    }
                }

                8 -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = personalDetails.currentHeight,
                            onValueChange = { height ->
                                onPersonalDetailsChange(personalDetails.copy(currentHeight = height))
                            },
                            label = { Text("Current Height") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.weight(.1f))
                        OutlinedTextField(
                            value = personalDetails.currentWeight,
                            onValueChange = { weight ->
                                onPersonalDetailsChange(personalDetails.copy(currentWeight = weight))
                            },
                            label = { Text("Current Weight") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                else -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "This will be your login credentials",
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center
                        )

                        OutlinedTextField(
                            value = personalDetails.username,
                            onValueChange = { username ->
                                onPersonalDetailsChange(personalDetails.copy(username = username))
                            },
                            label = { Text("Create a username") }
                        )
                        OutlinedTextField(
                            value = personalDetails.password,
                            onValueChange = { password ->
                                onPersonalDetailsChange(personalDetails.copy(password = password))
                            },
                            label = { Text("Create a password") }
                        )
                    }
                }
            }

        }

    }
}

@Composable
fun EmergencyContactComponent(
    emergencyContact: EmergencyContact,
    onEmergencyContactChange: (EmergencyContact) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "We need an Emergency Contact.")
        OutlinedTextField(
            value = emergencyContact.relation,
            onValueChange = { relation ->
                onEmergencyContactChange(emergencyContact.copy(relation = relation))
            },
            label = { Text("Relation") }
        )
        OutlinedTextField(
            value = emergencyContact.name,
            onValueChange = { name ->
                onEmergencyContactChange(emergencyContact.copy(name = name))
            },
            label = { Text("Name") }
        )
        OutlinedTextField(
            value = emergencyContact.number,
            onValueChange = { phone ->
                onEmergencyContactChange(emergencyContact.copy(number = phone))
            },
            label = { Text("Phone Number") }
        )
        OutlinedTextField(
            value = emergencyContact.profession,
            onValueChange = { profession ->
                onEmergencyContactChange(emergencyContact.copy(profession = profession))
            },
            label = { Text("Profession") }
        )
    }
}

@Composable
fun DisabilityComponent(disability: Disability, onDisabilityChange: (Disability) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(text = "Disability Information", style = MaterialTheme.typography.headlineMedium)
        Text(text = "It's important for us to know! ", style = MaterialTheme.typography.headlineSmall)


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Do you have any disability?")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Yes",
                    color = if (disability.hasDisability) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.width(16.dp))
                Switch(
                    modifier = Modifier.semantics { contentDescription = "disability switch" },
                    checked = disability.hasDisability,
                    onCheckedChange = {
                        onDisabilityChange(disability.copy(hasDisability = !disability.hasDisability))
                    }
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = "No",
                    color = if (!disability.hasDisability) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
        }
        if (disability.hasDisability) {
            OutlinedTextField(
                value = disability.about,
                onValueChange = { about ->
                    onDisabilityChange(disability.copy(about = about))
                },
                label = { Text("Tell us about your disability") },
                singleLine = false
            )
            OutlinedTextField(
                value = disability.doctorName,
                onValueChange = { name ->
                    onDisabilityChange(disability.copy(doctorName = name))
                },
                label = { Text("Doctor Name") }
            )
            OutlinedTextField(
                value = disability.doctorContact,
                onValueChange = { phone ->
                    onDisabilityChange(disability.copy(doctorContact = phone))
                },
                label = { Text("Doctor Contact") }
            )
        }
    }
}

@Composable
fun PARQComponent(parq: PARQ, onParqChange: (PARQ) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Disability Information")
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(Modifier.weight(.9f)) { Text(text = parq.question1) }
            Row(Modifier.weight(.1f)) {
                Switch(
                    checked = parq.answer1,
                    onCheckedChange = { onParqChange(parq.copy(answer1 = !parq.answer1)) }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(Modifier.weight(.9f)) { Text(text = parq.question2) }
            Row(Modifier.weight(.1f)) {
                Switch(
                    checked = parq.answer2,
                    onCheckedChange = { onParqChange(parq.copy(answer2 = !parq.answer2)) }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(Modifier.weight(.9f)) { Text(text = parq.question3) }
            Row(Modifier.weight(.1f)) {
                Switch(
                    checked = parq.answer3,
                    onCheckedChange = { onParqChange(parq.copy(answer3 = !parq.answer3)) }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(Modifier.weight(.9f)) { Text(text = parq.question4) }
            Row(Modifier.weight(.1f)) {
                Switch(
                    checked = parq.answer4,
                    onCheckedChange = { onParqChange(parq.copy(answer4 = !parq.answer4)) }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(Modifier.weight(.9f)) { Text(text = parq.question5) }
            Row(Modifier.weight(.1f)) {
                Switch(
                    checked = parq.answer5,
                    onCheckedChange = { onParqChange(parq.copy(answer5 = !parq.answer5)) }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(Modifier.weight(.9f)) { Text(text = parq.question6) }
            Row(Modifier.weight(.1f)) {
                Switch(
                    checked = parq.answer6,
                    onCheckedChange = { onParqChange(parq.copy(answer6 = !parq.answer6)) }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(Modifier.weight(.9f)) { Text(text = parq.question7) }
            Row(Modifier.weight(.1f)) {
                Switch(
                    checked = parq.answer7,
                    onCheckedChange = { onParqChange(parq.copy(answer7 = !parq.answer7)) }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(Modifier.weight(.9f)) { Text(text = parq.question8) }
            Row(Modifier.weight(.1f)) {
                Switch(
                    checked = parq.answer8,
                    onCheckedChange = { onParqChange(parq.copy(answer8 = !parq.answer8)) }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(Modifier.weight(.9f)) { Text(text = parq.question9) }
            Row(Modifier.weight(.1f)) {
                Switch(
                    checked = parq.answer9,
                    onCheckedChange = { onParqChange(parq.copy(answer9 = !parq.answer9)) }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(Modifier.weight(.9f)) {
                Text(text = parq.question10)

            }
            Row(Modifier.weight(.1f)) {
                Switch(
                    checked = parq.answer10,
                    onCheckedChange = { onParqChange(parq.copy(answer10 = !parq.answer10)) },
                    modifier = Modifier
                )
            }

        }
    }
}

@Composable
fun ReferralComponent(referral: String, onReferralChange: (String) -> Unit) {

    val isReferred = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Tell us how you know about us!")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Were you referred by a member?")
            Switch(
                checked = isReferred.value,
                onCheckedChange = {
                    isReferred.value = !isReferred.value
                }
            )
        }

        if (isReferred.value) {
            OutlinedTextField(
                value = referral,
                onValueChange = { newValue ->
                    onReferralChange(newValue)
                },
                label = { Text("Member's username") }
            )
        } else {
            OutlinedTextField(
                value = referral,
                onValueChange = { newValue ->
                    onReferralChange(newValue)
                },
                label = { Text("Other. Please mention") }
            )
        }
    }
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

