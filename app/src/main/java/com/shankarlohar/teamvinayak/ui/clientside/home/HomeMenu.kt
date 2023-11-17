package com.shankarlohar.teamvinayak.ui.clientside.home

import com.shankarlohar.teamvinayak.R

enum class HomeMenu(val title: String, val icon: Int) {
    ATTENDANCE(
        "Attendance",
            R.drawable.attendance
    ),
    NOTIFICATION(
        "Notification",
        R.drawable.notification
    ),
    MESSAGE(
        "Message",
        R.drawable.message
    ),
    PAYMENT(
        "Payments",
        R.drawable.rupee
    ),
    PROFILE(
        "Profile",
        R.drawable.profile
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