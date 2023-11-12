package com.shankarlohar.teamvinayak.ui.clientside.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

enum class HomeMenu(val title: String, val icon: ImageVector) {
    HOME(
        "Home",
            Icons.Filled.Home
    ),
    NOTIFICATION(
        "Notification",
        Icons.Filled.Notifications
    ),
    FAVORITE(
        "Favorite",
        Icons.Filled.Favorite
    ),
    CART(
        "Cart",
        Icons.Filled.ShoppingCart
    ),
    PROFILE(
        "Profile",
        Icons.Filled.Person
    )
}

sealed class HomeMenuAction {
    object CLOSE : HomeMenuAction()
    object LOGOUT : HomeMenuAction()
    object SETTINGS : HomeMenuAction()
    data class MenuSelected(val menu: HomeMenu) : HomeMenuAction()
}

enum class MenuState {
    EXPANDED, COLLAPSED
}