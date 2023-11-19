package com.shankarlohar.teamvinayak.ui.clientside.hiddenpannel

import com.shankarlohar.teamvinayak.R

enum class PanelMenu(val title: String, val icon: Int) {
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

sealed class PanelMenuAction {
    object CLOSE : PanelMenuAction()
    object LOGOUT : PanelMenuAction()
    object SETTINGS : PanelMenuAction()
    data class MenuSelected(val menu: PanelMenu) : PanelMenuAction()
}

enum class MenuState {
    EXPANDED, COLLAPSED
}