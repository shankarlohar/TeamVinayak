package com.shankarlohar.teamvinayak.ui.home

import com.shankarlohar.teamvinayak.R

enum class HomeMenu(val title: String, val icon: Int) {
    HOME("Home", R.drawable.home),
    NOTIFICATION("Notification", R.drawable.notification),
    FAVORITE("Favorite", R.drawable.favourite),
    CART("Cart", R.drawable.shopping_cart),
    PROFILE("Profile", R.drawable.profile),
}

sealed class HomeMenuAction {
    object CLOSE : HomeMenuAction()
    object LOGOUT : HomeMenuAction()
    object SETTINGS : HomeMenuAction()
    data class MenuSelected(val menu: HomeMenu) : HomeMenuAction()
}