package com.shankarlohar.teamvinayak.data

import androidx.compose.ui.graphics.Color
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.ChoiceScreenDataModel

val choiceScreens = mutableListOf<ChoiceScreenDataModel>().apply {
        add(
            ChoiceScreenDataModel(
                bottomImage = R.drawable.join_now,
                title = R.string.join_now,
                heading = R.string.vinayak_multi_gym,
                subHeading = R.string.unisex,
                color = Color(0xFFeb4658)
            )
        )
        add(
            ChoiceScreenDataModel(
                bottomImage = R.drawable.member_login,
                title = R.string.members_login,
                heading = R.string.team_vinayak,
                subHeading = R.string.who_will_top_this_weeks_leaderboard,
                color = Color(0xFF6887cb)
            )
        )
        add(
            ChoiceScreenDataModel(
                bottomImage = R.drawable.admin_login,
                title = R.string.admin_only,
                heading = R.string.let_s_make_everyone_fit,
                subHeading = R.string.what_s_special_today,
                color = Color(0xFFfe7153)
            )
        )
    }

    val choiceCategories = listOf("New", "Members", "Admin")