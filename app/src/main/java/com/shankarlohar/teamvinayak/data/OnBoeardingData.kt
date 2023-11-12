package com.shankarlohar.teamvinayak.data

import com.shankarlohar.teamvinayak.model.OnBoardingModel

val onBoardingData = mutableListOf<OnBoardingModel>().apply {
    add(
        OnBoardingModel(
            section = "section 1",
            content = listOf("This is s random content 1", "This is another random content 1")
        )
    )
    add(
        OnBoardingModel(
            section = "section 2",
            content = listOf("This is s random content 2", "This is another random content 2")
        )
    )
    add(
        OnBoardingModel(
            section = "section 3",
            content = listOf("This is s random content 3", "This is another random content 3")
        )
    )

}