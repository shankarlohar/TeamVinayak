package com.shankarlohar.teamvinayak.ui.clientside.component.attendance

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.Attendance
import com.shankarlohar.teamvinayak.util.Utils.getCurrentDate
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel
import com.shankarlohar.teamvinayak.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceComponent(
    userViewModel: UserViewModel,
    authViewModel: AuthViewModel
) {
    val currentUser by remember {
        mutableStateOf(authViewModel.getAuth())
    }


    val context = LocalContext.current

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.working_out))

    val todayAttendance by userViewModel.attendanceData.observeAsState()
    val attendanceState by userViewModel.attendanceState.observeAsState()

    val attendanceData = remember {
        mutableStateOf(Attendance())
    }

    val date = remember { mutableStateOf(getCurrentDate().substring(0, 10)) }

    val trainer = remember { mutableStateOf("Ankit Shaw") }

    var endurance by remember { mutableStateOf(false) }
    var strength by remember { mutableStateOf(false) }
    var balance by remember { mutableStateOf(false) }
    var flexibility by remember { mutableStateOf(false) }

    var fullBody by remember { mutableStateOf(false) }
    var chest by remember { mutableStateOf(false) }
    var back by remember { mutableStateOf(false) }
    var arms by remember { mutableStateOf(false) }
    var legs by remember { mutableStateOf(false) }
    var shoulders by remember { mutableStateOf(false) }
    var abdominal by remember { mutableStateOf(false) }
    var neck by remember { mutableStateOf(false) }
    var grip by remember { mutableStateOf(false) }


    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    val attending = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(attending.value) {
        currentUser?.let { userViewModel.fetchTodaysAttendance(it.uid) }
    }


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 128.dp,
        sheetContent = {


            if (!attendanceState!!) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text("Working Out Now", style = MaterialTheme.typography.headlineSmall)

                    LottieAnimation(
                        modifier = Modifier.size(150.dp),
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                    )

                    Text(text = "Workout Type")

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            FilterChip(
                                selected = endurance,
                                onClick = {
                                    endurance = !endurance
                                    if (endurance) {
                                        attendanceData.value.type.add("Endurance")
                                    } else {
                                        attendanceData.value.type.remove("Endurance")
                                    }
                                },
                                label = { Text("Endurance") },
                                leadingIcon = if (endurance) {
                                    {

                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Localized Description",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {

                                    null
                                }
                            )
                            FilterChip(
                                selected = strength,
                                onClick = {
                                    strength = !strength
                                    if (strength) {
                                        attendanceData.value.type.add("Strength")
                                    } else {
                                        attendanceData.value.type.remove("Strength")
                                    }
                                },
                                label = { Text("Strength") },
                                leadingIcon = if (strength) {
                                    {

                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Localized Description",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                }
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            FilterChip(
                                selected = balance,
                                onClick = {
                                    balance = !balance
                                    if (balance) {
                                        attendanceData.value.type.add("Balance")
                                    } else {
                                        attendanceData.value.type.remove("Balance")
                                    }
                                },
                                label = { Text("Balance") },
                                leadingIcon = if (balance) {
                                    {

                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Localized Description",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {

                                    null
                                }
                            )
                            FilterChip(
                                selected = flexibility,
                                onClick = {
                                    flexibility = !flexibility
                                    if (flexibility) {
                                        attendanceData.value.type.add("Flexibility")
                                    } else {
                                        attendanceData.value.type.remove("Flexibility")
                                    }
                                },
                                label = { Text("Flexibility") },
                                leadingIcon = if (flexibility) {
                                    {

                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Localized Description",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {

                                    null
                                }
                            )
                        }
                    }

                    Text(text = "Focused Body Part")
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            FilterChip(
                                selected = fullBody,
                                onClick = {
                                    fullBody = !fullBody
                                    if (fullBody) {
                                        attendanceData.value.part.add("Full Body")
                                    } else {
                                        attendanceData.value.part.remove("Full Body")
                                    }
                                },
                                label = { Text("Full Body") },
                                leadingIcon = if (fullBody) {
                                    {
                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Localized Description",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                }
                            )
                            FilterChip(
                                selected = chest,
                                onClick = {
                                    chest = !chest
                                    if (chest) {
                                        attendanceData.value.part.add("Chest")
                                    } else {
                                        attendanceData.value.part.remove("Chest")
                                    }
                                },
                                label = { Text("Chest") },
                                leadingIcon = if (chest) {
                                    {
                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Localized Description",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                }
                            )
                            FilterChip(
                                selected = back,
                                onClick = {
                                    back = !back
                                    if (back) {
                                        attendanceData.value.part.add("Back")
                                    } else {
                                        attendanceData.value.part.remove("Back")
                                    }

                                },
                                label = { Text("Back") },
                                leadingIcon = if (back) {
                                    {
                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Localized Description",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                }
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            FilterChip(
                                selected = arms,
                                onClick = {
                                    arms = !arms
                                    if (arms) {
                                        attendanceData.value.part.add("Arms")
                                    } else {
                                        attendanceData.value.part.remove("Arms")
                                    }
                                },
                                label = { Text("Arms") },
                                leadingIcon = if (arms) {
                                    {
                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Localized Description",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                }
                            )
                            FilterChip(
                                selected = legs,
                                onClick = {
                                    legs = !legs
                                    if (legs) {
                                        attendanceData.value.part.add("Legs")
                                    } else {
                                        attendanceData.value.part.remove("Legs")
                                    }
                                },
                                label = { Text("Legs") },
                                leadingIcon = if (legs) {
                                    {
                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Localized Description",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                }
                            )
                            FilterChip(
                                selected = shoulders,
                                onClick = {
                                    shoulders = !shoulders
                                    if (shoulders) {
                                        attendanceData.value.part.add("Shoulders")
                                    } else {
                                        attendanceData.value.part.remove("Shoulders")
                                    }
                                },
                                label = { Text("Shoulders") },
                                leadingIcon = if (shoulders) {
                                    {
                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Localized Description",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                }
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            FilterChip(
                                selected = abdominal,
                                onClick = {
                                    abdominal = !abdominal
                                    if (abdominal) {
                                        attendanceData.value.part.add("Abdominal")
                                    } else {
                                        attendanceData.value.part.remove("Abdominal")
                                    }
                                },
                                label = { Text("Abdominal") },
                                leadingIcon = if (abdominal) {
                                    {

                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Localized Description",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                }
                            )
                            FilterChip(
                                selected = neck,
                                onClick = {
                                    neck = !neck
                                    if (neck) {
                                        attendanceData.value.part.add("Neck")
                                    } else {
                                        attendanceData.value.part.remove("Neck")
                                    }
                                },
                                label = { Text("Neck") },
                                leadingIcon = if (neck) {
                                    {
                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Localized Description",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                }
                            )
                            FilterChip(
                                selected = grip,
                                onClick = {
                                    grip = !grip
                                    if (grip) {
                                        attendanceData.value.part.add("Grip")
                                    } else {
                                        attendanceData.value.part.remove("Grip")
                                    }
                                },
                                label = { Text("Grip") },
                                leadingIcon = if (grip) {
                                    {
                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "Localized Description",
                                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                                        )
                                    }
                                } else {
                                    null
                                }
                            )
                        }
                    }

                    Text(text = "Training with: ${trainer.value}")


                    Button(
                        onClick = {
                            scope.launch {
                                if (attending.value) {
                                    attendanceData.value.date.value = date.value
                                    attendanceData.value.end.value = getCurrentDate().substring(11)
                                    attendanceData.value.trainer.value = trainer.value
                                    attending.value = false
                                    currentUser?.let {
                                        userViewModel.uploadAttendance(
                                            attendanceData.value,
                                            it.uid
                                        ) { onDone ->
                                            if (onDone) {
                                                Toast.makeText(
                                                    context,
                                                    "Attendance Done",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Attendance Failed",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        }
                                    }
                                    scaffoldState.bottomSheetState.partialExpand()
                                }
                            }
                        },
                        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                    ) {
                        Icon(
                            if (attending.value) Icons.Filled.Done else Icons.Filled.Warning,
                            contentDescription = "Workout Status",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            if (attending.value) "Finish Workout" else "Not Started Yet"
                        )
                    }

                }
            }
            else{
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text("Today's workout is done", style = MaterialTheme.typography.headlineSmall)
                }

            }


        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            //add some content here
            Card(Modifier.size(width = 300.dp, height = 200.dp)) {
                // Card content
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text("Current Gym Insides", style = MaterialTheme.typography.headlineSmall)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Date: ")
                        Text(text = date.value)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Trainer: ")
                        Text(text = trainer.value)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Crowd: ")
                        Text(text = "32 Men 2 Women")
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Suitable for: ")
                        Text(text = "Male | Female | Both")
                    }
                }
            }

            Card(Modifier.size(width = 300.dp, height = 200.dp)) {

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text("Today's Workout", style = MaterialTheme.typography.headlineSmall)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Started at: ")
                        todayAttendance?.start?.let { Text(text = it.value) }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Completed at: ")
                        todayAttendance?.end?.let { Text(text = it.value) }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Workout Type: ")
                        todayAttendance?.type?.let {
                            LazyRow() {
                                items(it) { item ->
                                    Text(text = item)
                                }
                            }
                        }

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Focused Body Part: ")
                        todayAttendance?.part?.let {
                            LazyRow() {
                                items(it) { item ->
                                    Text(text = item)
                                }
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Trained by: ")
                        todayAttendance?.trainer?.let { Text(text = it.value) }
                    }
                }
            }
            if (!attendanceState!!) {
                Button(
                    onClick = {
                        scope.launch {
                            if (!attending.value) {
                                attendanceData.value.start.value =
                                    getCurrentDate().substring(11)
                                attending.value = true
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                    },
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                ) {
                    Icon(
                        if (attending.value) Icons.Filled.Lock else Icons.Filled.PlayArrow,
                        contentDescription = "Workout Status",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        if (attending.value) "At Workout" else "Start Workout"
                    )
                }
            }
        }
    }
}
