package com.shankarlohar.teamvinayak.ui.navigation

import com.shankarlohar.teamvinayak.R

enum class ClientPanelNavigation(val icon: Int, val title: String) {
    Attendance(R.drawable.attendance, "Give Attendance"),
    Notification(R.drawable.notification, "Check Notification"),
    Message(R.drawable.message, "Send Message"),
    Payment(R.drawable.rupee, "My Payments"),
    Profile(R.drawable.profile, "See My Profile"),
    More(R.drawable.settings, "More"),

}

enum class ClientBottomNavigation(val icon: Int, val title: String){
    Status(R.drawable.status, "Status"),
    Complain(R.drawable.issue, "Complain"),
    Diet(R.drawable.diet, "Diet"),
    Suggestion(R.drawable.idea, "Suggestion"),
    Leaderboard(R.drawable.leaderboard, "Leaderboard")
}

enum class OwnerPanelNavigation(val icon: Int, val title: String) {
    Income(R.drawable.income, "Income"),
    Expense(R.drawable.expense, "Expense"),
    Visitor(R.drawable.visitor, "Save a Visitor"),
    Monthly(R.drawable.monthly_status, "Monthly Status"),
    New(R.drawable.new_member, "Add New Member"),
    Home(R.drawable.home, "Home")
}
enum class OwnerBottomNavigation(val icon: Int, val title: String){
    Attendance(R.drawable.attendance, "Check Attendance"),
    Notification(R.drawable.notification, "Send Notification"),
    Message(R.drawable.message, "Read Message"),
    Payment(R.drawable.rupee, "Analyse Payments"),
    Profile(R.drawable.profile, "Manage Profiles")
}

sealed class OwnerMenuAction {
    object LOGOUT : OwnerMenuAction()
    object CONTROL : OwnerMenuAction()
    data class MenuSelected(val menu: OwnerPanelNavigation) : OwnerMenuAction()
}

sealed class ClientMenuAction {
    object LOGOUT : ClientMenuAction()
    data class MenuSelected(val menu: ClientPanelNavigation) : ClientMenuAction()
}

enum class MenuState {
    EXPANDED, COLLAPSED
}