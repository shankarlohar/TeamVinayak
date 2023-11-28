package com.shankarlohar.teamvinayak.ui.clientside.component.attendance

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.AttendanceModel
import com.shankarlohar.teamvinayak.util.Utils
import com.shankarlohar.teamvinayak.util.Utils.getCurrentDate
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AttendanceComponent(
) {
    val context = LocalContext.current

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.working_out))


    val date = remember{ mutableStateOf(getCurrentDate().substring(0,10)) }
    val start = remember{ mutableStateOf(AttendanceModel().start) }
    val end = remember{ mutableStateOf(AttendanceModel().end) }
    val part = remember{ mutableStateListOf<String>() }
    val type = remember{ mutableStateListOf<String>() }
    val trainer = remember{ mutableStateOf("Ankit Shaw") }

    var endurance by remember { mutableStateOf(false) }
    var stregth by remember { mutableStateOf(false) }
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


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 128.dp,
        sheetContent = {


            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                Text("Working Out Now", style = MaterialTheme.typography.headlineSmall)

                Text("Started at : ${start.value}")


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
                    ){
                        FilterChip(
                            selected = endurance,
                            onClick = {
                                endurance = !endurance
                                if (endurance){
                                    type.add("Endurance")
                                }else{
                                    type.remove("Endurance")
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
                            selected = stregth,
                            onClick = {
                                stregth = !stregth
                                if (stregth){
                                    type.add("Strength")
                                }else{
                                    type.remove("Strength")
                                }
                            },
                            label = { Text("Strength") },
                            leadingIcon = if (stregth) {
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
                                if (balance){
                                    type.add("Balance")
                                }else{
                                    type.remove("Balance")
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
                                if (flexibility){
                                    type.add("Flexibility")
                                }else{
                                    type.remove("Flexibility")
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
                    ){
                        FilterChip(
                            selected = fullBody,
                            onClick = {
                                fullBody = !fullBody
                                if (fullBody){
                                    part.add("Full Body")
                                }else{
                                    part.remove("Full Body")
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
                                if (chest){
                                    part.add("Chest")
                                }else{
                                    part.remove("Chest")
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
                                if (back){
                                    part.add("Back")
                                }else{
                                    part.remove("Back")
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
                    ){
                        FilterChip(
                            selected = arms,
                            onClick = {
                                arms = !arms
                                if (arms){
                                    part.add("Arms")
                                }else{
                                    part.remove("Arms")
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
                                if (legs){
                                    part.add("Legs")
                                }else{
                                    part.remove("Legs")
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
                                if (shoulders){
                                    part.add("Shoulders")
                                }else{
                                    part.remove("Shoulders")
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
                    ){
                        FilterChip(
                            selected = abdominal,
                            onClick = {
                                abdominal = !abdominal
                                if (abdominal){
                                    part.add("Abdominal")
                                }else{
                                    part.remove("Abdominal")
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
                                if (neck){
                                    part.add("Neck")
                                }else{
                                    part.remove("Neck")
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
                                if (grip){
                                    part.add("Grip")
                                }else{
                                    part.remove("Grip")
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
                    onClick = { scope.launch {
                        if (attending.value) {
                            end.value = getCurrentDate().substring(11)
                            attending.value = false

                            val attendance = AttendanceModel(
                                date =  date.value,
                                start = start.value,
                                end = end.value,
                                type = type,
                                part = part,
                                trainer = trainer.value
                            )
                            scaffoldState.bottomSheetState.partialExpand()
                    } } },
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
                // Card content
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
                        Text(text =  start.value)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Completed at: ")
                        Text(text =  end.value)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Workout Type: ")
                        LazyRow(){
                            items(type){item ->
                                Text(text = item)
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
                        LazyRow(){
                            items(part){item ->
                                Text(text = item)
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
                        Text(text = trainer.value)
                    }

                }
            }

            Button(
                onClick = {
                    scope.launch {
                        if (!attending.value) {
                            start.value = getCurrentDate().substring(11)
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
