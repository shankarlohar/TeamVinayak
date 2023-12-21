package com.shankarlohar.teamvinayak.ui.newuserside.component.newuser.sub

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.Enquiry
import com.shankarlohar.teamvinayak.model.FaqItem
import com.shankarlohar.teamvinayak.ui.common.FancyIndicator
import com.shankarlohar.teamvinayak.util.Utils.getCurrentDate
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel
import com.shankarlohar.teamvinayak.viewmodel.ChooseUserViewModel
import com.shankarlohar.teamvinayak.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Enquiry(
    viewModel: ChooseUserViewModel,
    openDialog: MutableState<Boolean>,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel,
    context: Context = LocalContext.current
) {

    val faqItems by viewModel.faqs.collectAsState()

    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("FAQ", "Ask!")

    val name = remember {
        mutableStateOf("")
    }
    val phone = remember {
        mutableStateOf("")
    }
    val query = remember {
        mutableStateOf("")
    }
    val whatsapp = remember {
        mutableStateOf(true)
    }
    val modifier = Modifier.padding(4.dp)

    var skipPartiallyExpanded by remember { mutableStateOf(false) }
    var edgeToEdgeEnabled by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val compositionQuestion by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.question))
    val compositionDone by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.done))
    val compositionQuestions by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.chatsupport))

    val asked = remember {
        mutableStateOf(false)
    }
    val openAccountBottomSheet = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        if (openDialog.value) {
            val windowInsets = if (edgeToEdgeEnabled)
                WindowInsets(0) else BottomSheetDefaults.windowInsets

            ModalBottomSheet(
                onDismissRequest = { openDialog.value = false },
                sheetState = bottomSheetState,
                windowInsets = windowInsets
            ) {

                SecondaryTabRow(
                    selectedTabIndex = state,
                    indicator = {
                        FancyIndicator(
                            MaterialTheme.colorScheme.primary,
                            Modifier.tabIndicatorOffset(it[state])
                        )
                    }
                ) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = state == index,
                            onClick = {
                                state = index

                            },
                            text = { Text(title) }
                        )
                    }
                }
                when (state) {
                    0 -> {
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {

                            LaunchedEffect(scope) {
                                scope.launch { bottomSheetState.expand() }.invokeOnCompletion {

                                    if (!bottomSheetState.hasPartiallyExpandedState) {
                                        skipPartiallyExpanded = true
                                    }
                                }
                            }

                            LottieAnimation(
                                modifier = Modifier.size(150.dp),
                                composition = compositionQuestions,
                                iterations = LottieConstants.IterateForever,
                            )

                            // faq here
                            Text(text = "Frequently Asked Questions.")
                            Spacer(modifier = modifier)
                            Spacer(modifier = modifier)

                            LazyColumn {
                                items(faqItems) { item ->
                                    FaqItemRow(item,modifier)
                                }
                            }
                        }

                    }

                    else -> {
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
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
                            LottieAnimation(
                                modifier = Modifier.size(150.dp),
                                composition = compositionQuestion,
                                iterations = LottieConstants.IterateForever,
                            )
                            // ask
                            Text(text = "Ask a question here!")

                            Column(
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text("Your Name")
                                OutlinedTextField(
                                    value = name.value, onValueChange = { name.value = it }
                                )
                                Spacer(modifier = modifier)
                                Text("Your Phone Number")
                                OutlinedTextField(
                                    value = phone.value, onValueChange = { phone.value = it }
                                )
                                Text("Your Question in brief")
                                Spacer(modifier = modifier)
                                OutlinedTextField(
                                    value = query.value, onValueChange = { query.value = it }
                                )
                                Spacer(modifier = modifier)
                                Text("How you want us to connect to you?:")
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(text = "What's App", modifier = modifier)
                                    RadioButton(selected = whatsapp.value, onClick = {
                                        whatsapp.value = true
                                    })
                                    Text(text = "Call", modifier = modifier)
                                    RadioButton(selected = !whatsapp.value, onClick = {
                                        whatsapp.value = false
                                    })
                                }
                                Button(onClick = {
                                    if (name.value.isEmpty() || phone.value.isEmpty() || query.value.isEmpty()) {
                                        Toast.makeText(
                                            context,
                                            "All the three fields are required",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {
                                        Log.d("enquiry", "enquiry viewModel function called")
                                        viewModel.saveEnquiryQuestion(
                                            Enquiry(
                                                name.value,
                                                phone.value,
                                                query.value,
                                                if (whatsapp.value) "Connect via What's App" else "Connect via call",
                                                getCurrentDate(),
                                                "Not Seen"
                                            )
                                        ) {
                                            if (it) {
                                                asked.value = true
                                                Log.d("enquiry", "data uploaded")
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Something went wrong",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }

                                        }
                                        openDialog.value = false
                                        Log.d("enquiry", "alert dialogue closed")
                                    }

                                }) {
                                    Text(text = "Request a callback")
                                }
                            }
                        }

                    }
                }
            }
        }

        AccountStatus(
            openBottomSheet = openAccountBottomSheet,
            authViewModel = authViewModel,
            userViewModel = userViewModel
        )

        if (asked.value) {
            // Display the alert dialog when openDialog is true
            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or presses the back button
                    asked.value = false
                },
                title = {
                    // Display a title in the dialog
                    Text("We will get back to you!")
                },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("We have your question processing...")
                        LottieAnimation(
                            modifier = Modifier.size(150.dp),
                            composition = compositionDone,
                            iterations = LottieConstants.IterateForever,
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            asked.value = false
                        }
                    ) {
                        Text("Okay")
                    }
                }
            )
        }
    }
}

@Composable
fun FaqItemRow(item: FaqItem, modifier: Modifier) {
    Column {
        Text(text = item.question, style = MaterialTheme.typography.headlineSmall)
        Text(text = item.answer, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = modifier)
    }
}