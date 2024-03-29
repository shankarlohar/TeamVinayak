package com.shankarlohar.teamvinayak.data.local

import androidx.compose.ui.graphics.Color
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.ChooseUserModel

val choiceScreens = mutableListOf<ChooseUserModel>().apply {
        add(
            ChooseUserModel(
                bottomImage = R.drawable.join_now,
                title = R.string.join_now,
                heading = R.string.vinayak_multi_gym,
                subHeading = R.string.unisex,
                color = Color(0xFF000000)
            )
        )
        add(
            ChooseUserModel(
                bottomImage = R.drawable.member_login,
                title = R.string.members_login,
                heading = R.string.team_vinayak,
                subHeading = R.string.who_will_top_this_weeks_leaderboard,
                color = Color(0xFF000000)
            )
        )
        add(
            ChooseUserModel(
                bottomImage = R.drawable.admin_login,
                title = R.string.admin_only,
                heading = R.string.let_s_make_everyone_fit,
                subHeading = R.string.what_s_special_today,
                color = Color(0xFF831F1F)
            )
        )
    }

    val choiceCategories = listOf("New User", "Members", "Admin")