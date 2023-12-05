package com.shankarlohar.teamvinayak.ui.newuserside.component.member

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.util.Role
import com.shankarlohar.teamvinayak.util.Steps
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel
import com.shankarlohar.teamvinayak.viewmodel.UserViewModel

@Composable
fun MemberLoginCard(
    navController: NavController,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel
){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val userData by userViewModel.userData.observeAsState()


        val modifier = Modifier
            .padding(8.dp)

        val username = remember{ mutableStateOf("") }
        val passState = remember{ mutableStateOf("") }

        Spacer(
            modifier = modifier
        )


        OutlinedTextField(
            value = username.value,
            onValueChange = {username.value = it},
            label = {
                Text(
                    text = stringResource(R.string.user_id)
                )
            },
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

        Spacer(
            modifier = modifier
        )

        OutlinedTextField(
            value = passState.value,
            onValueChange = { passState.value = it },
            label = {
                Text(
                    text = stringResource(R.string.passcode)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            leadingIcon = {
                Icon(
                    Icons.Filled.Lock,
                    contentDescription = stringResource(R.string.passcode)
                )
            },
        )

        Spacer(
            modifier = modifier
        )

        CompositionLocalProvider() {
            Row{
                Text(
                    text = "Forgot your credentials?",
                    textAlign = TextAlign.End,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "Click here",
                    textAlign = TextAlign.End,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .clickable{}
                )
            }
        }


        Button(
            onClick = {
                if (authViewModel.getAuth()!= null){
                    userViewModel.fetchUserData(authViewModel.getAuth()!!.uid)
                    if (userData?.role == Role.MEMBER) {
                        navController.navigate(Steps.CLIENT.name)
                        Toast.makeText(context, "Already logged in.", Toast.LENGTH_SHORT)
                            .show()
                    }else{
                        authViewModel.logoutMember {  }
                        Toast.makeText(context, "Only members can login here.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                else{
                    if (username.value.isNotEmpty() and passState.value.isNotEmpty()){
                        userViewModel.fetchUserEmail(username.value){email ->
                            Log.d("username",email)
                            authViewModel.loginMember(email, passState.value)
                            { success, user ->
                                if (success) {
                                    if (user != null) {
                                        userViewModel.fetchUserData(user)
                                        if (userData?.role == Role.MEMBER) {
                                            navController.navigate(Steps.CLIENT.name)
                                        }else{
                                            authViewModel.logoutMember {  }
                                            Toast.makeText(context, "Only members can login here.", Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                    }
                                    else {
                                        Toast.makeText(context, "User Data Not Found.", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                } else {
                                    Toast.makeText(context, user, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }else{
                        Toast.makeText(context, "Username and Password is required.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            },
            colors = ButtonDefaults
                .buttonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
            contentPadding = PaddingValues(16.dp),
            shape = CircleShape,
            modifier = modifier
                .size(90.dp)
        ) {
            Text(
                text = stringResource(R.string.let_s_go),
            )
        }
    }
}