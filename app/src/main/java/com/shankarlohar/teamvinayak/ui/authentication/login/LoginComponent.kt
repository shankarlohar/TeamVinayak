package com.shankarlohar.teamvinayak.ui.authentication.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shankarlohar.teamvinayak.MainViewModel
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.ui.authentication.AuthViewModel


@Composable
fun LoginPage(
    authViewModel: AuthViewModel,
    mainViewModel: MainViewModel,
    onJoinNowClick: () -> Unit = {},
    onStartClick: () -> Unit = {}
) {
    val gymInfo by mainViewModel.gymInfo.collectAsState()

    Column() {
        gymInfo?.let {
            LoginMainCard(
                authViewModel = authViewModel,
                logoUrl = it.logo,
                onStartClick = onStartClick
            )
            LoginBgCard(
                monthlyFee = it.monthlyFee,
                onJoinNowClick = onJoinNowClick
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginMainCard(
    authViewModel: AuthViewModel,
    logoUrl:String,
    onStartClick: () -> Unit = {}
) {
    val emailState = remember {
        mutableStateOf(TextFieldValue(authViewModel.name.value))
    }
    val passState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    Surface(
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .fillMaxHeight(0.9f)
            .fillMaxWidth(),
        shape = RoundedCornerShape(60.dp)
            .copy(
                topStart = ZeroCornerSize,
                topEnd = ZeroCornerSize
            )
    ) {
        Column(
            modifier = Modifier
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val modifier = Modifier
                .padding(8.dp)

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(logoUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.gym_name),
                modifier = modifier
                    .height(200.dp)
            )

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
                    keyboardType = KeyboardType.Number
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
                Text(
                    text = stringResource(R.string.i_forgot_my_credentials),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .clickable{}
                )
            }

            Spacer(
                modifier = modifier
            )

            Button(
                onClick = onStartClick,
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
                    text = stringResource(R.string.lets_go),
                )
            }
        }
    }
}


@Composable
fun LoginBgCard(
    monthlyFee: Int,
    onJoinNowClick: () -> Unit = {}
) {

    val signupText = buildAnnotatedString {
        append(stringResource(R.string.not_a_member_yet))
        withStyle(SpanStyle(color = MaterialTheme.colorScheme.error)) {
            append(
                stringResource(R.string.join_now)
            )
        }
    }

    Surface(
        color = MaterialTheme.colorScheme.errorContainer,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        shape = RoundedCornerShape(60.dp)
            .copy(
                bottomStart = ZeroCornerSize,
                bottomEnd = ZeroCornerSize
            ),
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = signupText,
                color = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .clickable {
                        onJoinNowClick()
                    },
            )
            Text(
                text = "Just @" + monthlyFee + "₹ per month.",
                modifier = Modifier.padding( bottom = 8.dp)
            )
        }
    }
}
