package com.shankarlohar.teamvinayak.ui.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shankarlohar.teamvinayak.R

@Preview(showBackground = true)
@Composable
fun SignupPage() {
    Box {
        SignupBgCard()
        SignupMainCard()
    }
}

@Composable
fun SignupBgCard() {
    val signupText = buildAnnotatedString {
        append("Page ")
        withStyle(SpanStyle(color = MaterialTheme.colorScheme.error)) {
            append("1/10.")
        }
    }
    Surface(color = MaterialTheme.colorScheme.errorContainer, modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = signupText,
                color = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .clickable {},
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupMainCard() {
    val emailState = remember { mutableStateOf(TextFieldValue("shankarlohar")) }
    val passState = remember { mutableStateOf(TextFieldValue("")) }
    Surface(
        color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier
            .height(550.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(60.dp).copy(topStart = ZeroCornerSize, topEnd = ZeroCornerSize)
    ) {
        Column(
            modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
            Image(painter = painterResource(id = R.drawable.vinayak_multi_gym_logo), contentDescription = null, Modifier.height(200.dp))
            Spacer(modifier = Modifier.padding(16.dp))
            OutlinedTextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                label = {
                    Text(text = "VMGC Id")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Leading Icon") },
                modifier = modifier
            )
            Spacer(modifier = Modifier.padding(6.dp))
            OutlinedTextField(
                value = passState.value,
                onValueChange = { passState.value = it },
                label = { Text(text = "Passcode") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Leading Icon") },
                modifier = modifier
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            CompositionLocalProvider() {
                Text(
                    text = "Read all terms and conditions.",
                    textAlign = TextAlign.End,
                    modifier = Modifier.clickable{}
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Button(
                onClick = {}, modifier = modifier,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "Make me a member!")
            }
            Spacer(modifier = Modifier.padding(vertical = 5.dp))

        }
    }
}
