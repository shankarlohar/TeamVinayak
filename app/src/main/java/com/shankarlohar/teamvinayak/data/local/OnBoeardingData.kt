package com.shankarlohar.teamvinayak.data.local

import com.shankarlohar.teamvinayak.model.TermsAndConditionsModel

val onBoardingData = mutableListOf<TermsAndConditionsModel>().apply {
    add(
        TermsAndConditionsModel(
            section = "section 1",
            content = listOf("This is s random content 1", "This is another random content 1")
        )
    )
    add(
        TermsAndConditionsModel(
            section = "section 2",
            content = listOf("This is s random content 2", "This is another random content 2")
        )
    )
    add(
        TermsAndConditionsModel(
            section = "section 3",
            content = listOf("This is s random content 3", "This is another random content 3")
        )
    )

}