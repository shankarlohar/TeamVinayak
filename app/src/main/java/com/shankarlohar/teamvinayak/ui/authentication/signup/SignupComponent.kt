package com.shankarlohar.teamvinayak.ui.authentication.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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


@Composable
fun SignupMainCard() {
    Text(text = "this is to be done", modifier = Modifier.fillMaxSize())
}
