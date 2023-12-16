package com.shankarlohar.teamvinayak.ui.newuserside.component.newuser.sub

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.ExperimentalPagerApi
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.GymInfo
import com.shankarlohar.teamvinayak.ui.common.AutoSlidingCarousel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun About(gymInfo: GymInfo, openDialog: MutableState<Boolean>) {

    val images = listOf(
        "https://firebasestorage.googleapis.com/v0/b/team-vinayak.appspot.com/o/vinayak_main_1.jpg?alt=media&token=e5af93d1-0cc4-4618-be2d-9418ec4b80c1",
        "https://firebasestorage.googleapis.com/v0/b/team-vinayak.appspot.com/o/vinayak_main_2.jpg?alt=media&token=77f32971-cf8e-4f8e-a9ff-de40950607d3"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Use rememberImagePainter to load the image from the URL
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder // Image to display in case of an error
                (LocalContext.current).data(data = gymInfo.logo)
                .apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)
                    placeholder(R.drawable.vinayak_multi_gym_no_background) // Placeholder image while loading
                    error(R.drawable.maintanance) // Image to display in case of an error
                    scale(Scale.FILL) // Scale type
                }).build()
        )
        val modifier = Modifier.padding(2.dp)
        val fontSize = 10.sp
        if (openDialog.value) {
            // Display the alert dialog when openDialog is true
            AlertDialog(onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or presses the back button
                openDialog.value = false
            }, text = {
                // Display the main text content of the dialog
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(gymInfo.name)
                                Spacer(modifier = modifier)
                                Text(gymInfo.genderPreference)
                            }
                        },
                    )
                }, content = { innerPadding ->
                    Column(
                        Modifier.padding(innerPadding),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = modifier)
                            Image(
                                painter = painter,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                                    .clip(MaterialTheme.shapes.medium)
                                    .padding(4.dp),
                                contentScale = ContentScale.Fit
                            )
                            Spacer(modifier = modifier)
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ElevatedCard(modifier = modifier) {
                                Text(gymInfo.address)
                            }
                            Spacer(modifier = modifier)
                            ElevatedCard(modifier = modifier) { Text(stringResource(R.string.email) + gymInfo.email) }
                            Spacer(modifier = modifier)
                            ElevatedCard(modifier = modifier) {
                                Row {
                                    Text(
                                        stringResource(R.string.phone), fontSize = fontSize
                                    )
                                    Text(gymInfo.phone[0], fontSize = fontSize)
                                    Spacer(modifier = modifier)
                                    Text(gymInfo.phone[1], fontSize = fontSize)
                                    Spacer(modifier = modifier)
                                    Text(gymInfo.phone[2], fontSize = fontSize)
                                }
                            }
                        }

                        Spacer(modifier = modifier)
                        Spacer(modifier = modifier)

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ElevatedCard {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(stringResource(R.string.late_fee) + gymInfo.lateFee.toString())
                                    Spacer(modifier = modifier)
                                    Text(stringResource(R.string.locker_rental) + gymInfo.lockerRental.toString())
                                    Spacer(modifier = modifier)
                                    Text(stringResource(R.string.monthly_fee) + gymInfo.monthlyFee.toString())
                                    Spacer(modifier = modifier)
                                    Text(stringResource(R.string.membership_holding_fee) + gymInfo.membershipHoldingFree.toString())
                                }
                            }
                        }

                        Spacer(modifier = modifier)
                        Spacer(modifier = modifier)

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val selected = remember{ mutableStateOf(false) }

                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (gymInfo.openToday) Color(0xFF4CAF50) // Green for open
                                    else Color(0xFFF44336) // Red for closed
                                )
                                .clickable { selected.value = !selected.value }
                            ) { // Use background color to represent open/closed state
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = if (gymInfo.openToday) stringResource(R.string.gym_is_open_today) else stringResource(
                                            R.string.gym_is_closed_today
                                        ),
                                        color = Color.White, // White text for contrast
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                    )
                                    if (selected.value) {
                                        InputChip(
                                            selected = true,
                                            onClick = {},
                                            label = { Text(gymInfo.timing[0].substring(20)) },
                                        )
                                        InputChip(
                                            selected = true,
                                            onClick = {},
                                            label = { Text(gymInfo.timing[1]) },
                                        )
                                    }
                                }
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier.padding(16.dp),
                                shape = RoundedCornerShape(16.dp),
                            ) {
                                AutoSlidingCarousel(
                                    itemsCount = images.size,
                                    itemContent = { index ->
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(images[index])
                                                .build(),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.height(300.dp)
                                        )
                                    }
                                )
                            }
                        }
                    }

                })

            }, confirmButton = {
                // Display a single confirm button with the text "OK"
                Button(onClick = {
                    // Dismiss the dialog when the user clicks the confirm button
                    openDialog.value = false
                }) {
                    Text(stringResource(R.string.close))
                }
            })
        }
    }


}

