package com.shankarlohar.teamvinayak.ui.clientside.component.bottomnav

import com.shankarlohar.teamvinayak.R

enum class BottomNav(val route: String, val icon: Int, val title: String) {
    Status("status", R.drawable.status, "Status"),
    Complain("complain", R.drawable.issue, "Complain"),
    Diet("diet", R.drawable.diet, "Diet"),
    Suggestion("suggestion", R.drawable.idea, "Suggestion"),
    Leaderboard("leaderboard", R.drawable.leaderboard, "Leaderboard")
}