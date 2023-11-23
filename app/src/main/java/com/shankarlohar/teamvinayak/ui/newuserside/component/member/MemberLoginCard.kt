package com.shankarlohar.teamvinayak.ui.newuserside.component.member

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import com.shankarlohar.teamvinayak.util.Steps
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberLoginCard(
    navController: NavController,
    authViewModel: AuthViewModel
){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val modifier = Modifier
            .padding(8.dp)

        val emailState = remember{ mutableStateOf("") }
        val passState = remember{ mutableStateOf("") }

        Spacer(
            modifier = modifier
        )


        OutlinedTextField(
            value = emailState.value,
            onValueChange = {emailState.value = it},
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
                authViewModel.loginMember(emailState.value,passState.value)
                { success, errorMessage ->
                    if (success) {
                        navController.navigate(Steps.CLIENT.name)
                    } else {
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
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