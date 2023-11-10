package com.shankarlohar.teamvinayak.model

import androidx.compose.ui.graphics.Color
import com.shankarlohar.teamvinayak.R

data class ChoiceScreenDataModel(
    val resId: Int,
    val title: String,
    val description: String,
    val price: String,
    val color: Color,
){
    companion object {

        val listOfShoes = mutableListOf<ChoiceScreenDataModel>().apply {
            add(
                ChoiceScreenDataModel(
                    resId = R.drawable.join_now,
                    title = "Join Now!",
                    description = "Airmax 100",
                    price = "₹ 15,000",
                    color = Color(0xFFeb4658)
                )
            )
            add(
                ChoiceScreenDataModel(
                    resId = R.drawable.join_now,
                    title = "Member Login",
                    description = "Epic React 2",
                    price = "₹ 20,000",
                    color = Color(0xFF6887cb)
                )
            )
            add(
                ChoiceScreenDataModel(
                    resId = R.drawable.join_now,
                    title = "Admin Login",
                    description = "Hovr 2022",
                    price = "₹ 10,000",
                    color = Color(0xFFfe7153)
                )
            )
        }

        val categories = listOf("New", "Members", "Admin")
    }

}

