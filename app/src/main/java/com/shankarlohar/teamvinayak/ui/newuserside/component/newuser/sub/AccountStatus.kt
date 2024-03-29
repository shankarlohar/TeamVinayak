package com.shankarlohar.teamvinayak.ui.newuserside.component.newuser.sub

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.shankarlohar.teamvinayak.ui.common.UserCard
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel
import com.shankarlohar.teamvinayak.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountStatus(
    openBottomSheet: MutableState<Boolean>,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel
) {

    val compositionForm by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.form))


    var skipPartiallyExpanded by remember { mutableStateOf(false) }
    var edgeToEdgeEnabled by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val context = LocalContext.current
    var username = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    val account = remember { mutableStateOf("") }


    val loginAccount = {

        if (username.value.isNotEmpty() and password.value.isNotEmpty()) {
            userViewModel.fetchUserEmail(username.value) { email ->
                Log.d("username", email)
                authViewModel.loginMember(email = email, password = password.value) { result, uid ->
                    if (result) {
                        if (uid != null) {
                            account.value = uid
                            Log.d("bootomsheetlogin", "yes logged in" + account.value)
                            userViewModel.fetchUserData(account.value)
                        } else {
                            Log.d("bootomsheetlogin", "no user data")
                            Toast.makeText(context, "No user data found", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Log.d("bootomsheetlogin", "not logged in")
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            Toast.makeText(context, "Username and Password is required.", Toast.LENGTH_SHORT)
                .show()
        }

    }

    // Sheet content
    if (openBottomSheet.value) {
        val windowInsets = if (edgeToEdgeEnabled)
            WindowInsets(0) else BottomSheetDefaults.windowInsets

        ModalBottomSheet(
            onDismissRequest = {
                openBottomSheet.value = false
                Log.d("bottomsheetvalue",openBottomSheet.value.toString())
                if (account.value.isNotEmpty()){
                    Log.d("bootomsheetlogin","trying log out")
                    authViewModel.logoutMember {
                        if (it){
                            Toast.makeText(context,"Login as a member to access the account", Toast.LENGTH_LONG).show()
                            account.value = ""
                            username.value = ""
                            password.value = ""
                        }
                    }
                }
            },
            sheetState = bottomSheetState,
            windowInsets = windowInsets
        ) {

            if (account.value == "") {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    LottieAnimation(
                        modifier = Modifier.size(150.dp),
                        composition = compositionForm,
                        iterations = LottieConstants.IterateForever,
                    )

                    Spacer(modifier = Modifier.padding(8.dp))

                    Text(text = "Fill the username and passcode as set during the registration process.")

                    Spacer(modifier = Modifier.padding(8.dp))

                    OutlinedTextField(
                        value = username.value,
                        onValueChange = { username.value = it },
                        label = {
                            Text(
                                text = stringResource(R.string.user_id)
                            )
                        },
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    OutlinedTextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = {
                            Text(
                                text = stringResource(R.string.passcode)
                            )
                        },
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Button(
                            // Note: If you provide logic outside of onDismissRequest to remove the sheet,
                            // you must additionally handle intended state cleanup, if any.
                            onClick = {
                                Log.d("bootomsheetlogin", "trying logged in")
                                loginAccount()
                            }
                        ) {
                            Text("Get Account Info")
                        }
                    }
                }
            } else {
                val userData by userViewModel.userData.observeAsState()

                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder // Image to display in case of an error
                        (LocalContext.current).data(data = userData?.personalDetails?.picture)
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                            placeholder(R.drawable.vinayak_multi_gym_no_background) // Placeholder image while loading
                            error(R.drawable.maintanance) // Image to display in case of an error
                            scale(Scale.FIT) // Scale type
                        }).build()
                )

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
                    UserCard(
                        painter = painter,
                        fullName = userData?.personalDetails?.fullName,
                        uid = userData?.uid,
                        membershipStatus = userData?.membership?.status,
                        registrationStatus = userData?.membership?.details,
                        formSubmissionTime = userData?.membership?.formSubmission
                        )
                }
            }
        }
    }
}