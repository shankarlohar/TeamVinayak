package com.shankarlohar.teamvinayak.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.shankarlohar.teamvinayak.R

@Composable
fun CartComponent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //add some content here
        Image(
            painter = painterResource(id = R.drawable.shopping_cart),
            contentDescription = "Cart"
        )
    }
}