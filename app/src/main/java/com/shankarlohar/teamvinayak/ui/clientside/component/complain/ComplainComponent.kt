package com.shankarlohar.teamvinayak.ui.clientside.component.complain

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.shankarlohar.teamvinayak.model.Complain
import com.shankarlohar.teamvinayak.ui.common.ExpandableCard
import com.shankarlohar.teamvinayak.ui.common.FancyIndicator
import com.shankarlohar.teamvinayak.util.Utils.getCurrentDate
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel
import com.shankarlohar.teamvinayak.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplainComponent(
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel
) {
    val context = LocalContext.current

    val complains by userViewModel.complains.observeAsState()

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
                    onClick = {
                        state = index
                        if (index == 1){
                            userViewModel.fetchComplains(authViewModel.getAuth()?.uid){
                                if (!it){
                                    Toast.makeText(context,"Complain fetch failed",Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    },
                    text = { Text(title) }
                )
            }
        }
        when(state){
            0 -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    val heading = remember {
                        mutableStateOf("")
                    }
                    val description = remember {
                        mutableStateOf("")
                    }
                    Column{
                        Text(text = "Heading")
                        Spacer(modifier = Modifier.padding(2.dp))
                        TextField(
                            value = heading.value,
                            onValueChange = {
                                heading.value = it
                            },
                            singleLine = true
                        )
                    }
                    Column{
                        Text(text = "Description")
                        Spacer(modifier = Modifier.padding(2.dp))
                        TextField(
                            value = description.value,
                            onValueChange = {
                                description.value = it
                            },
                            singleLine = false,
                            modifier = Modifier.height(200.dp)
                        )
                    }
                    Button(onClick = {
                        val dateTime = getCurrentDate()
                        val complain = authViewModel.getAuth()?.let {
                            Complain(
                                subject = heading.value,
                                description = description.value,
                                user = it.uid,
                                date = dateTime.substring(0,10),
                                time = dateTime.substring(11)
                            )
                        }
                        userViewModel.addComplain(complain){isDone ->
                            if (isDone){
                                heading.value = ""
                                description.value = ""
                                Toast.makeText(context,"Complain Success",Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(context,"Complain Failed",Toast.LENGTH_SHORT).show()
                            }
                        }

                    }) {
                        Text(text = "Submit Complain")
                    }
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ){
                    if (complains != null){
                        items(complains!!){
                            ExpandableCard(
                                title = it.subject,
                                description = it.description,
                                time = it.time,
                                date = it.date,
                                from = if (it.response.isEmpty()) "Pending" else it.response
                            )
                        }
                    }

                }
            }
        }


    }
}