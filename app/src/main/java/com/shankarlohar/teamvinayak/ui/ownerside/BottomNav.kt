package com.shankarlohar.teamvinayak.ui.ownerside

import com.shankarlohar.teamvinayak.R

enum class BottomNav(val route: String, val icon: Int, val title: String) {
    Attendance("attendance", R.drawable.attendance, "Attendance"),
    Notification("notification", R.drawable.notification, "Notification"),
    Message("message", R.drawable.message, "Message"),
    Payment("payment", R.drawable.rupee, "Payment"),
    Profile("profile", R.drawable.profile, "Profile")
}