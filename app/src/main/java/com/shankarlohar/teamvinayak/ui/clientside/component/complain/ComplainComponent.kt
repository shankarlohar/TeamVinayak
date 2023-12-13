package com.shankarlohar.teamvinayak.ui.clientside.component.complain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.shankarlohar.teamvinayak.ui.common.FancyIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ComplainComponent(
) {
    val context = LocalContext.current
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("New Complain", "View Complains")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

         SecondaryTabRow(
            selectedTabIndex = state,
            indicator = {
                FancyIndicator(
                    MaterialTheme.colorScheme.primary,
                    Modifier.tabIndicatorOffset(it[state])
                )
            }
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(title) }
                )
            }
        }
        when(state){
            0 -> {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Fancy indicator tab first selected",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            else -> {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Fancy indicator tab second selected",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }


    }
}