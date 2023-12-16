package com.shankarlohar.teamvinayak.ui.clientside.component.profile

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.Disability
import com.shankarlohar.teamvinayak.model.EmergencyContact
import com.shankarlohar.teamvinayak.model.Membership
import com.shankarlohar.teamvinayak.model.PersonalDetails
import com.shankarlohar.teamvinayak.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ProfileComponent(userViewModel: UserViewModel) {

    val userData by userViewModel.userData.observeAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            userData?.let {
                UserDetails(
                    personalDetails = userData!!.personalDetails
                )
//                PersonalDetailsView(userData!!.personalDetails)
//                DisabilityCard(disability = userData!!.disability)
//                EmergencyContactView(userData!!.emergencyContact)
//                MembershipView(userData!!.membership)
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
private fun UserDetails(personalDetails: PersonalDetails, context: Context = LocalContext.current) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
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

        // User's image
        Image(
            modifier = Modifier
                .size(72.dp)
                .clip(shape = CircleShape),
            painter = painter,
            contentDescription = stringResource(R.string.your_image)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(weight = 3f, fill = false)
                    .padding(start = 16.dp)
            ) {

                // User's name
                Text(
                    text = personalDetails.fullName,
                    style = TextStyle(
                        fontSize = 22.sp,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                // User's email
                Text(
                    text = personalDetails.email,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray,
                        letterSpacing = (0.8).sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Edit button
            IconButton(
                modifier = Modifier
                    .weight(weight = 1f, fill = false),
                onClick = {
                    Toast.makeText(context, "Edit Button", Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit Details",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

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

