package com.shankarlohar.teamvinayak.ui.clientside.hiddenpanel

import android.widget.Toast
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.User
import com.shankarlohar.teamvinayak.ui.clientside.component.attendance.AttendanceComponent
import com.shankarlohar.teamvinayak.ui.clientside.component.message.MessageComponent
import com.shankarlohar.teamvinayak.ui.clientside.component.notifications.NotificationsComponent
import com.shankarlohar.teamvinayak.ui.clientside.component.payment.PaymentComponent
import com.shankarlohar.teamvinayak.ui.clientside.component.profile.ProfileComponent
import com.shankarlohar.teamvinayak.ui.clientside.component.settings.MoreComponent
import com.shankarlohar.teamvinayak.ui.navigation.ClientMenuAction
import com.shankarlohar.teamvinayak.ui.navigation.ClientPanelNavigation
import com.shankarlohar.teamvinayak.ui.navigation.MenuState
import com.shankarlohar.teamvinayak.util.Steps
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel
import com.shankarlohar.teamvinayak.viewmodel.UserViewModel
import kotlin.math.roundToInt


@Composable
fun ClientPanelComponent(
    authViewModel: AuthViewModel,
    navController: NavHostController,
    userViewModel: UserViewModel
) {

    val isForMen = remember { mutableStateOf(true) }


    var panelNavScreen by remember { mutableStateOf(ClientPanelNavigation.Attendance) }

    var currentState by remember { mutableStateOf(MenuState.COLLAPSED) }
    val updateAnim = updateTransition(currentState, label = "MenuState")
    val context = LocalContext.current

    LaunchedEffect(authViewModel.getUid()) {
        userViewModel.fetchPersonalDetails(authViewModel.getUid())
    }

    val userDetails by userViewModel.personalDetails.observeAsState()


    val scale = updateAnim.animateFloat(
        transitionSpec = {
            when {
                MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                    tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                }
                MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                    tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                }
                else -> {
                    snap()
                }
            }
        }, label = ""
    ) {
        when (it) {
            MenuState.EXPANDED -> 0.7f
            MenuState.COLLAPSED -> 1f
        }
    }
    val transitionOffset = updateAnim.animateOffset({
        when {
            MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            }
            MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            }
            else -> {
                snap()
            }
        }
    }, label = "") {
        when (it) {
            MenuState.EXPANDED -> Offset(750f, 60f)
            MenuState.COLLAPSED -> Offset(0f, 0f)
        }
    }

    val alphaMenu = updateAnim.animateFloat({
        when {
            MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                tween(durationMillis = 300)
            }
            MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                tween(durationMillis = 300)
            }
            else -> {
                snap()
            }
        }
    }, label = "") {
        when (it) {
            MenuState.EXPANDED -> 1f
            MenuState.COLLAPSED -> 0.5f
        }
    }

    val roundness = updateAnim.animateDp({
        when {
            MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                tween(durationMillis = 300)
            }
            MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                tween(durationMillis = 300)
            }
            else -> {
                snap()
            }
        }
    }, label = "") {
        when (it) {
            MenuState.EXPANDED -> 20.dp
            MenuState.COLLAPSED -> 0.dp
        }
    }

    val menuOffset = updateAnim.animateOffset({
        when {
            MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            }
            MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            }
            else -> {
                snap()
            }
        }
    }, label = "") {
        when (it) {
            MenuState.EXPANDED -> Offset(0f, 0f)
            MenuState.COLLAPSED -> Offset(-100f, 0f)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {

        //side menu
        MenuComponent(
            isForMen = isForMen,
            user = userDetails,
            Modifier
                .offset {
                    IntOffset(
                        menuOffset.value.x.roundToInt(),
                        menuOffset.value.y.roundToInt()
                    )
                }
                .alpha(alphaMenu.value),
        ) {
            when (it) {
                is ClientMenuAction.MenuSelected -> {
                    panelNavScreen = it.menu
                }
                ClientMenuAction.LOGOUT -> {
                    authViewModel.logoutMember{ success ->
                        if (success) {
                            navController.navigate(Steps.CHOICE.name)
                        } else {
                            Toast.makeText(context, "Logout failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else -> {
                    currentState = MenuState.COLLAPSED
                }
            }
            currentState = MenuState.COLLAPSED
        }

        // stack layer 0
        Box(
            modifier = Modifier
                .fillMaxSize()
                .scale(scale.value - 0.05f)
                .offset {
                    IntOffset(
                        transitionOffset.value.x.toInt() - 50,
                        transitionOffset.value.y.toInt()
                    )
                }
                .background(
                    MaterialTheme.colorScheme.onPrimary.copy(alpha = .90f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(8.dp)
                .alpha(alphaMenu.value)
        )
        //stack layer 1
        Box(
            modifier = Modifier
                .fillMaxSize()
                .scale(scale.value - 0.08f)
                .offset {
                    IntOffset(
                        transitionOffset.value.x.toInt() - 100,
                        transitionOffset.value.y.toInt()
                    )
                }
                .background(
                    MaterialTheme.colorScheme.onPrimary.copy(.5f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(8.dp)
                .alpha(alphaMenu.value)
        )
        // dashboard content
        Column(modifier = Modifier
            .fillMaxSize()
            .scale(scale.value)
            .offset {
                IntOffset(
                    transitionOffset.value.x.toInt(),
                    transitionOffset.value.y.toInt()
                )
            }
            .clip(shape = RoundedCornerShape(roundness.value))
            .background(color = MaterialTheme.colorScheme.primaryContainer)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.menu),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            currentState = when (currentState) {
                                MenuState.EXPANDED -> MenuState.COLLAPSED
                                MenuState.COLLAPSED -> MenuState.EXPANDED
                            }
                        }
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = panelNavScreen.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
            )
            when (panelNavScreen) {
                ClientPanelNavigation.Attendance -> {
                    AttendanceComponent()
                }
                ClientPanelNavigation.Profile -> {
                    ProfileComponent()
                }
                ClientPanelNavigation.Payment -> {
                    PaymentComponent()
                }
                ClientPanelNavigation.Message -> {
                    MessageComponent()
                }
                ClientPanelNavigation.Notification -> {
                    NotificationsComponent()
                }
                ClientPanelNavigation.More -> {
                    MoreComponent()
                }
            }


        }

    }
}

@Composable
fun MenuComponent(
    isForMen: MutableState<Boolean>,
    user: User?,
    modifier: Modifier, menuAction: (ClientMenuAction) -> Unit) {

    Column(modifier = modifier.padding(18.dp), verticalArrangement = Arrangement.Center) {

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {


            user?.let {details ->

                Text(
                    text = details.name,
                    fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    Icons.Filled.CheckCircle,
                    contentDescription = stringResource(R.string.membership_active),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

            }
        }

        Spacer(modifier = Modifier.weight(1f))

        LazyColumn {

            items(ClientPanelNavigation.values()) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 26.dp, bottom = 16.dp)
                        .clickable {
                            menuAction(ClientMenuAction.MenuSelected(it))
                        }
                ) {
                    Image(
                        painterResource(it.icon),
                        contentDescription = it.title,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = it.title,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp),
                        fontWeight = FontWeight.Medium
                    )
                }

            }
        }

        Spacer(modifier = Modifier.weight(1f))

        //ui switch
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                checked = isForMen.value,
                onCheckedChange = { isChecked ->
                    isForMen.value = isChecked
                }
            )
        }

        //logout
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .clickable {
                    menuAction(ClientMenuAction.LOGOUT)
                }
        ) {
            Icon(
                Icons.Filled.ExitToApp,
                contentDescription = stringResource(R.string.logout)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = stringResource(R.string.logout),
                color = MaterialTheme.colorScheme.onError,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Medium
            )
        }

        // signature
        Text(
            text = stringResource(R.string.creator),
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = .4f),
            fontSize = 8.sp,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Medium,
        )

    }

}

