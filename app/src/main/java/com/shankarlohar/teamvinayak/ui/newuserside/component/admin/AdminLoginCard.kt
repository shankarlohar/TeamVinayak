package com.shankarlohar.teamvinayak.ui.newuserside.component.admin

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
fun AdminLoginCard(
    navController: NavController,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel,
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

        val name = remember{ mutableStateOf("") }
        val password = remember{ mutableStateOf("") }

        Spacer(
            modifier = modifier
        )


        OutlinedTextField(
            value = name.value,
            onValueChange = {name.value = it},
            label = {
                Text(
                    text = stringResource(R.string.name)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            leadingIcon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = stringResource(R.string.name)
                )
            }
        )

        Spacer(
            modifier = modifier
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = {
                Text(
                    text = stringResource(R.string.password)
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
                    text = stringResource(R.string.forgot_your_credentials),
                    textAlign = TextAlign.End,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = stringResource(R.string.click_here),
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
                    if (userData?.role == Role.ADMIN) {
                        navController.navigate(Steps.OWNER.name)
                        Toast.makeText(context, "Already logged in.", Toast.LENGTH_SHORT)
                            .show()
                    }else{
                        authViewModel.logoutMember {  }
                        Toast.makeText(context, "Only admin can login here.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                else{
                    if (name.value.isNotEmpty() and password.value.isNotEmpty()){
                        userViewModel.fetchUserEmail(name.value){email ->
                            Log.d("username",email)
                            authViewModel.loginMember(email, password.value)
                            { success, user ->
                                if (success) {
                                    if (user != null) {
                                        userViewModel.fetchUserData(user)
                                        if (userData?.role == Role.ADMIN) {
                                            navController.navigate(Steps.OWNER.name)
                                        }else{
                                            authViewModel.logoutMember {  }
                                            Toast.makeText(context, "Only admin can login here.", Toast.LENGTH_SHORT)
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
                text = stringResource(R.string.get_in),
            )
        }
    }
}