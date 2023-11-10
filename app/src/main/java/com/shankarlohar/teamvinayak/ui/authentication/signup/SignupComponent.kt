package com.shankarlohar.teamvinayak.ui.authentication.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.ConsentToExercise
import com.shankarlohar.teamvinayak.model.DoctorDetails
import com.shankarlohar.teamvinayak.model.EmergencyContact
import com.shankarlohar.teamvinayak.model.GymUserModel
import com.shankarlohar.teamvinayak.model.PersonalDetails


@Preview()
@Composable
fun SignupPage() {
    var gymUserModel by remember { mutableStateOf(GymUserModel()) }
    var currentStep by remember { mutableStateOf(0) }
    val steps = listOf(
        "Personal Details",
        "Consent to Exercise",
        "Emergency Contact",
        "Doctor Details",
        "Membership Plan",
        "Declaration"
    )

    Surface(
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(60.dp)
            .copy(
                topStart = ZeroCornerSize,
                topEnd = ZeroCornerSize
            )
    ) {
        Column(
            modifier = Modifier
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Display current step
            Text(
                text = steps[currentStep],
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )

            // Render content based on the current step
            when (currentStep) {
                0 -> PersonalDetailsSection(gymUserModel) { personalDetails ->
                    gymUserModel = gymUserModel.copy(personalDetails = personalDetails)
                    currentStep++
                }
                1 -> ConsentToExerciseSection(gymUserModel) { consentToExercise ->
                    gymUserModel = gymUserModel.copy(consentToExercise = consentToExercise)
                    currentStep++
                }
                2 -> EmergencyContactSection(gymUserModel) { emergencyContact ->
                    gymUserModel = gymUserModel.copy(emergencyContact = emergencyContact)
                    currentStep++
                }
                3 -> DoctorDetailsSection(gymUserModel) { doctorDetails ->
                    gymUserModel = gymUserModel.copy(doctorDetails = doctorDetails)
                    currentStep++
                }
                4 -> MembershipPlanSection(gymUserModel) { membershipPlan ->
                    gymUserModel = gymUserModel.copy(membershipPlan = membershipPlan)
                    currentStep++
                }
                5 -> DeclarationSection(gymUserModel) { declaration ->
                    gymUserModel = gymUserModel.copy(declaration = declaration)
                    // Here you can submit the `gymUser` object to your Firebase database or perform other actions.
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDetailsSection(gymUserModel: GymUserModel, onNext: (PersonalDetails) -> Unit) {
    var personalDetails by remember { mutableStateOf(PersonalDetails()) }
    val modifier = Modifier
        .padding(8.dp)


    val scrollState = rememberScrollState()

    val genderOptions = listOf("Female", "Male")
    var genderOptionsExpanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ){

        Spacer(modifier = modifier)

        // Add fields and input validation here
        OutlinedTextField(
            value = personalDetails.fullName.orEmpty(),
            onValueChange = { personalDetails = personalDetails.copy(fullName = it) },
            label = { Text(stringResource(R.string.full_name)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            leadingIcon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = stringResource(R.string.user_id)
                )
            }
        )
        Spacer(modifier = modifier)

        OutlinedTextField(
            value = personalDetails.address.orEmpty(),
            onValueChange = { personalDetails = personalDetails.copy(address = it) },
            label = { Text(stringResource(R.string.address)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            leadingIcon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = stringResource(R.string.address)
                )
            }
        )


        Spacer(modifier = modifier)

        OutlinedTextField(
            value = stringResource(R.string._91) + personalDetails.mobileNumber.orEmpty(),
            onValueChange = { personalDetails = personalDetails.copy(mobileNumber = it) },
            label = { Text(stringResource(R.string.mobile)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            leadingIcon = {
                Icon(
                    Icons.Filled.Phone,
                    contentDescription = stringResource(R.string.mobile)
                )
            }
        )

        Spacer(modifier = modifier)

        OutlinedTextField(
            value = personalDetails.dateOfBirth.orEmpty(),
            onValueChange = { personalDetails = personalDetails.copy(dateOfBirth = it) },
            label = { Text(stringResource(R.string.date_of_birth)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            leadingIcon = {
                Icon(
                    Icons.Filled.DateRange,
                    contentDescription = stringResource(R.string.date_of_birth)
                )
            }

        )

        Spacer(modifier = modifier)

        Row{
            OutlinedTextField(
                value = personalDetails.weight.toString(),
                onValueChange = {
                    personalDetails = personalDetails
                        .copy(weight = it.toDouble())
                                },
                label = { Text("Weight (KG)") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = modifier.weight(1f)
            )


            OutlinedTextField(
                value = personalDetails.height.toString(),
                onValueChange = {
                    personalDetails = personalDetails.copy(height = it.toInt())
                                },
                label = { Text("Height (CM)") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = modifier)

        ExposedDropdownMenuBox(
            expanded = genderOptionsExpanded,
            onExpandedChange = { genderOptionsExpanded = it },
        ) {
            OutlinedTextField(
                // The `menuAnchor` modifier must be passed to the text field for correctness.
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = personalDetails.gender.orEmpty(),
                onValueChange = {},
                label = { Text("Gender") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = genderOptionsExpanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = genderOptionsExpanded,
                onDismissRequest = { genderOptionsExpanded = false },
            ) {
                genderOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            personalDetails.gender = selectionOption
                            genderOptionsExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

        Spacer(modifier = modifier)

        OutlinedTextField(
            value = personalDetails.disability.orEmpty(),
            onValueChange = { personalDetails = personalDetails.copy(disability = it) },
            label = { Text("Disability") }
        )

        Spacer(modifier = modifier)

        OutlinedTextField(
            value = personalDetails.fitnessGoal.orEmpty(),
            onValueChange = { personalDetails = personalDetails.copy(fitnessGoal = it) },
            label = { Text("Fitness Goal") }
        )

        Spacer(modifier = modifier)


        Button(
            onClick = { onNext(personalDetails) },
            colors = ButtonDefaults
                .buttonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
            contentPadding = PaddingValues(16.dp),
            shape = CircleShape,
            modifier = modifier
                .size(90.dp)
        ) {
            Text("Next")
        }
    }
}

@Composable
fun ConsentToExerciseSection(gymUserModel: GymUserModel, onNext: (ConsentToExercise) -> Unit) {
    var consentToExercise by remember { mutableStateOf(ConsentToExercise()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Add fields and input validation here
        Checkbox(
            checked = consentToExercise.unableToExerciseInPast ?: false,
            onCheckedChange = { consentToExercise = consentToExercise.copy(unableToExerciseInPast = it) },
        )
        Text("Unable to exercise in the past")
        Checkbox(
            checked = consentToExercise.physicianAdvisedAgainstExercising ?: false,
            onCheckedChange = { consentToExercise = consentToExercise.copy(physicianAdvisedAgainstExercising = it) },
        )
        Text("Physician advised against exercising")
        // Add more checkboxes...

        Button(onClick = { onNext(consentToExercise) }) {
            Text("Next")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyContactSection(gymUserModel: GymUserModel, onNext: (EmergencyContact) -> Unit) {
    var emergencyContact by remember { mutableStateOf(EmergencyContact()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Add fields and input validation here
        TextField(
            value = emergencyContact.contactName.orEmpty(),
            onValueChange = { emergencyContact = emergencyContact.copy(contactName = it) },
            label = { Text("Contact Name") }
        )
        TextField(
            value = emergencyContact.relationship.orEmpty(),
            onValueChange = { emergencyContact = emergencyContact.copy(relationship = it) },
            label = { Text("Relationship") }
        )
    }
    // Add more fields...

    Button(onClick = { onNext(emergencyContact) }) {
        Text("Next")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorDetailsSection(gymUserModel: GymUserModel, onNext: (DoctorDetails) -> Unit) {
    var doctorDetails by remember { mutableStateOf(DoctorDetails()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Add fields and input validation here
        TextField(
            value = doctorDetails.doctorName.orEmpty(),
            onValueChange = { doctorDetails = doctorDetails.copy(doctorName = it) },
            label = { Text("Doctor Name") }
        )
        TextField(
            value = doctorDetails.doctorContactNumber.orEmpty(),
            onValueChange = { doctorDetails = doctorDetails.copy(doctorContactNumber = it) },
            label = { Text("Doctor Contact Number") }
        )
    }
    // Add more fields...

    Button(onClick = { onNext(doctorDetails) }) {
        Text("Next")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembershipPlanSection(gymUserModel: GymUserModel, onNext: (String) -> Unit) {
    var membershipPlan by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Add a Dropdown or other input method for selecting the plan
        TextField(
            value = membershipPlan,
            onValueChange = { membershipPlan = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (membershipPlan.isNotBlank()) {
                        onNext(membershipPlan)
                    }
                }
            ),
            singleLine = true,
            label = { Text("Select Membership Plan") }
        )

        Button(onClick = { onNext(membershipPlan) }) {
            Text("Next")
        }
    }
}

@Composable
fun DeclarationSection(gymUserModel: GymUserModel, onComplete: (Boolean) -> Unit) {
    var agreedToTerms by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Add a Checkbox for agreeing to terms and conditions
        Checkbox(
            checked = agreedToTerms,
            onCheckedChange = { agreedToTerms = it },
        )
        Text("I agree to the terms and conditions")

        Button(onClick = {
            onComplete(agreedToTerms)
        }) {
            Text("Submit")
        }
    }
}
