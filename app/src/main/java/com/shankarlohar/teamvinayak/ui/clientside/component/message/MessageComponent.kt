package com.shankarlohar.teamvinayak.ui.clientside.component.message

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shankarlohar.teamvinayak.R

@Composable
fun MessageComponent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.chatsupport))

        LottieAnimation(
            modifier = Modifier.size(150.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )

        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = stringResource(R.string.coming_soon))
    }
}