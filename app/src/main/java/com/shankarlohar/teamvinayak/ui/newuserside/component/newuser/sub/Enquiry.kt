package com.shankarlohar.teamvinayak.ui.newuserside.component.newuser.sub

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.shankarlohar.teamvinayak.model.Enquiry
import com.shankarlohar.teamvinayak.viewmodel.ChooseUserViewModel

@Composable
fun Enquiry(viewModel: ChooseUserViewModel, openDialog: MutableState<Boolean>, context: Context){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val name = remember {
            mutableStateOf("")
        }
        val phone = remember {
            mutableStateOf("")
        }
        val query = remember {
            mutableStateOf("")
        }
        val whatsapp = remember {
            mutableStateOf(true)
        }
        val modifier = Modifier.padding(4.dp)


        if (openDialog.value) {
            // Display the alert dialog when openDialog is true
            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or presses the back button
                    openDialog.value = false
                },
                title = {
                    // Display a title in the dialog
                    Text("Submit your question")
                },
                text = {
                    Column() {
                        Text("Your Name")
                        OutlinedTextField(
                            value = name.value, onValueChange = {name.value = it}
                        )
                        Spacer(modifier = modifier)
                        Text("Your Phone Number")
                        OutlinedTextField(
                            value = phone.value, onValueChange = {phone.value = it}
                        )
                        Text("Your Question in brief")
                        Spacer(modifier = modifier)
                        OutlinedTextField(
                            value = query.value, onValueChange = {query.value = it}
                        )
                        Spacer(modifier = modifier)
                        Text("How you want us to connect to you?:")
                        Row (
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text(text = "What's App", modifier = modifier)
                            RadioButton(selected = whatsapp.value, onClick = {
                                whatsapp.value = true
                            })
                            Text(text = "Call",modifier = modifier)
                            RadioButton(selected = !whatsapp.value, onClick = {
                                whatsapp.value = false
                            })
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (name.value.isEmpty() || phone.value.isEmpty() || query.value.isEmpty()){
                                Toast.makeText(context,"All the three fields are required",Toast.LENGTH_LONG).show()
                            }else{
                                Log.d("enquiry","enquiry viewModel function called")
                                viewModel.saveEnquiryQuestion(
                                    Enquiry(
                                        name.value,
                                        phone.value,
                                        query.value,
                                        if(whatsapp.value) "Connect via What's App" else "Connect via call"
                                    )
                                ){
                                    Log.d("enquiry","data uploaded")

                                }
                                openDialog.value = false
                                Log.d("enquiry","alert dialogue closed")
                            }
                        }
                    ) {
                        Text("Ask")
                    }
                }
            )
        }
    }
}