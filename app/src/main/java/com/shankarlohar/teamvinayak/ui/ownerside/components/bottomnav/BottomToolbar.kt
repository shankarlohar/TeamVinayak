package com.shankarlohar.teamvinayak.ui.ownerside.components.bottomnav

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shankarlohar.teamvinayak.ui.navigation.OwnerBottomNavigation


@Composable
fun OwnerBottomToolbar(screen: MutableState<OwnerBottomNavigation>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        OwnerBottomNavigation.values().forEach { nav ->
            Image(
                painter = painterResource(id = nav.icon),
                contentDescription = "search",
                modifier = Modifier
                    .padding(4.dp)
                    .size(36.dp)
                    .padding(6.dp)
                    .clickable {
                        screen.value = nav
                    },
                colorFilter = if (screen.value == nav) {
                    ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                } else {
                    ColorFilter.tint(Color.LightGray)
                }
            )
        }
    }
}