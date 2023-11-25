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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountStatus(openBottomSheet: MutableState<Boolean>, authViewModel: AuthViewModel){

    var skipPartiallyExpanded by remember { mutableStateOf(false) }
    var edgeToEdgeEnabled by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val account = remember{ mutableStateOf("") }

    val loginAccount = {
        authViewModel.loginMember(email = username,password = password){ result, uid ->
            if (result){
                if (uid != null) {
                    account.value = uid
                    Log.d("bootomsheetlogin","yes logged in"+account.value)
                }else{
                    Log.d("bootomsheetlogin","no user data")
                    Toast.makeText(context,"No user data found", Toast.LENGTH_LONG).show()
                }
            }else{
                Log.d("bootomsheetlogin","not logged in")
                Toast.makeText(context,"Something went wrong", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Sheet content
    if (openBottomSheet.value) {
        val windowInsets = if (edgeToEdgeEnabled)
            WindowInsets(0) else BottomSheetDefaults.windowInsets

        ModalBottomSheet(
            onDismissRequest = { openBottomSheet.value = false },
            sheetState = bottomSheetState,
            windowInsets = windowInsets
        ) {

            if (account.value == ""){
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Spacer(modifier = Modifier.padding(8.dp))

                    Text(text = "Fill the username and passcode as set during the registration process.")

                    Spacer(modifier = Modifier.padding(8.dp))

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = {
                            Text(
                                text = stringResource(R.string.user_id)
                            )
                        },
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
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
                                Log.d("bootomsheetlogin","trying logged in")
                                loginAccount()
                            }
                        ) {
                            Text("Get Account Info")
                        }
                    }
                }
            }else{
                Column() {

                    LaunchedEffect(scope){
                        scope.launch { bottomSheetState.expand() }.invokeOnCompletion {
                            if (!bottomSheetState.hasPartiallyExpandedState) {
                                skipPartiallyExpanded = true
                            }
                        }
                    }

                    Text(account.value)

                }
            }



        }
    }
}