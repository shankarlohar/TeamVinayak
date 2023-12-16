package com.shankarlohar.teamvinayak.ui.clientside.component.profile

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shankarlohar.teamvinayak.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class OptionsData(
    val icon: ImageVector,
    val title:String,
    val subTitle: String
)

val optionsList: ArrayList<OptionsData> = ArrayList()



@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun ProfileEcommerce(context: Context = LocalContext.current) {

    // This indicates if the optionsList has data or not
    // Initially, the list is empty. So, its value is false.
    var listPrepared by remember {
        mutableStateOf(false)
    }
    optionsList.clear()
    prepareOptionsData()
    listPrepared = true

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {

        }
    }

    if (listPrepared) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {

            item {
                // User's image, name, email and edit button
                UserDetails(context = context)
            }

            // Show the options
            items(optionsList) { item ->
                OptionsItemStyle(item = item, context = context)
            }

        }
    }
}

// This composable displays user's image, name, email and edit button
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
private fun UserDetails(context: Context = LocalContext.current) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // User's image
        Image(
            modifier = Modifier
                .size(72.dp)
                .clip(shape = CircleShape),
            painter = painterResource(id = R.drawable.vinayak_multi_gym_no_background),
            contentDescription = "Your Image"
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
                    text = "Victoria Steele",
                    style = TextStyle(
                        fontSize = 22.sp,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                // User's email
                Text(
                    text = "email123@email.com",
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

// Row style for options
@Composable
private fun OptionsItemStyle(item: OptionsData = optionsList[0], context: Context = LocalContext.current) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = true) {
                Toast
                    .makeText(context, item.title, Toast.LENGTH_SHORT)
                    .show()
            }
            .padding(all = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Icon
        Icon(
            modifier = Modifier
                .size(32.dp),
            imageVector = item.icon,
            contentDescription = item.title,
            tint = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(weight = 3f, fill = false)
                    .padding(start = 16.dp)
            ) {

                // Title
                Text(
                    text = item.title,
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )

                Spacer(modifier = Modifier.height(2.dp))

                // Sub title
                Text(
                    text = item.subTitle,
                    style = TextStyle(
                        fontSize = 14.sp,
                        letterSpacing = (0.8).sp,
                        color = Color.Gray
                    )
                )

            }

            // Right arrow icon
            Icon(
                modifier = Modifier
                    .weight(weight = 1f, fill = false),
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = item.title,
                tint = Color.Black.copy(alpha = 0.70f)
            )
        }

    }
}

private fun prepareOptionsData() {

    val appIcons = Icons.Outlined

    optionsList.add(
        OptionsData(
            icon = appIcons.Person,
            title = "Account",
            subTitle = "Manage your account"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.ShoppingCart,
            title = "Orders",
            subTitle = "Orders history"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.Home,
            title = "Addresses",
            subTitle = "Your saved addresses"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.AddCircle,
            title = "Saved Cards",
            subTitle = "Your saved debit/credit cards"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.Settings,
            title = "Settings",
            subTitle = "App notification settings"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.Call,
            title = "Help Center",
            subTitle = "FAQs and customer support"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.Build,
            title = "Offers and Coupons",
            subTitle = "Offers and coupon codes for you"
        )
    )

    optionsList.add(
        OptionsData(
            icon = appIcons.FavoriteBorder,
            title = "Wishlist",
            subTitle = "Items you saved"
        )
    )
}