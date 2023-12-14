package com.shankarlohar.teamvinayak.ui.clientside.component.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.Disability
import com.shankarlohar.teamvinayak.model.EmergencyContact
import com.shankarlohar.teamvinayak.model.Membership
import com.shankarlohar.teamvinayak.model.PersonalDetails
import com.shankarlohar.teamvinayak.viewmodel.UserViewModel

@Composable
fun ProfileComponent(userViewModel: UserViewModel) {

    val userData by userViewModel.userData.observeAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            userData?.let {
                PersonalDetailsView(userData!!.personalDetails)
                DisabilityCard(disability = userData!!.disability)
                EmergencyContactView(userData!!.emergencyContact)
                MembershipView(userData!!.membership)
            }

        }
    }


}

@Composable
fun PersonalDetailsView(
    personalDetails: PersonalDetails,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Load and display image
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder // Image to display in case of an error
                    (LocalContext.current).data(data = personalDetails.picture)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        placeholder(R.drawable.vinayak_multi_gym_no_background) // Placeholder image while loading
                        error(R.drawable.maintanance) // Image to display in case of an error
                        scale(Scale.FILL) // Scale type
                    }).build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .padding(4.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Display other details
            Text(
                text = personalDetails.fullName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = personalDetails.username,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = personalDetails.fullAddress,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = personalDetails.mobile,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = personalDetails.dateOfBirth,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = personalDetails.gender,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = personalDetails.aadhaarNumber,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = personalDetails.currentWeight,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = personalDetails.currentHeight,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = personalDetails.email,
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun EmergencyContactView(contact: EmergencyContact) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Emergency Contact",
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = contact.contactName,
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = contact.contactNumber,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
fun DisabilityCard(
    disability: Disability,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp)),
    ) {


            if (disability.hasDisability) { // Show disability info only if true
                Text(
                    text = "Disability Information",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = disability.about,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "Doctor:",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "${disability.doctorName} - ${disability.doctorContact}",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
}



@Composable
fun MembershipView(membership: Membership) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Membership Information",

            )
            Text(
                text = "Status:",

            )
            Text(
                text = membership.status.toString(), // Convert Status enum to user-friendly label

            )
            Text(
                text = "Details:",

            )
            Text(
                text = membership.details.toString(), // Convert Details enum to user-friendly label

            )
        }
    }
}

