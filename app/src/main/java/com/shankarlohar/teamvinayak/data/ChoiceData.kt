package com.shankarlohar.teamvinayak.data

import androidx.compose.ui.graphics.Color
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.ChoiceScreenDataModel

val choiceScreens = mutableListOf<ChoiceScreenDataModel>().apply {
        add(
            ChoiceScreenDataModel(
                resId = R.drawable.join_now,
                title = R.string.join_now,
                description = R.string.vinayak_multi_gym,
                price = R.string.unisex,
                color = Color(0xFFeb4658)
            )
        )
        add(
            ChoiceScreenDataModel(
                resId = R.drawable.member_login,
                title = R.string.members_login,
                description = R.string.team_vinayak,
                price = R.string.unisex,
                color = Color(0xFF6887cb)
            )
        )
        add(
            ChoiceScreenDataModel(
                resId = R.drawable.admin_login,
                title = R.string.admin_only,
                description = R.string.let_s_make_everyone_fit,
                price = R.string.unisex,
                color = Color(0xFFfe7153)
            )
        )
    }

    val choiceCategories = listOf("New", "Members", "Admin")