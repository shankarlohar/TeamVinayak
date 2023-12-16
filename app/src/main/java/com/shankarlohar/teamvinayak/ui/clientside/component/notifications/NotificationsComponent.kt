package com.shankarlohar.teamvinayak.ui.clientside.component.notifications

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shankarlohar.teamvinayak.ui.common.ExpandableCard
import com.shankarlohar.teamvinayak.ui.common.NotificationCard
import com.shankarlohar.teamvinayak.viewmodel.UserViewModel

@Composable
fun NotificationsComponent(userViewModel: UserViewModel) {


    val notifications by userViewModel.notifications.observeAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
            if (notifications != null){
                items(notifications!!){
                    NotificationCard(it)
                }
            }

        }

}