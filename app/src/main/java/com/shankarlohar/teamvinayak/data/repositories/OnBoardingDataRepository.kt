package com.shankarlohar.teamvinayak.data.repositories

import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.data.model.OnBoardingModel

class OnBoardingDataRepository {
    companion object{
        fun getData(): List<OnBoardingModel>{
            return listOf(
                OnBoardingModel(R.drawable.intro1, R.string.onBoardingTitle1, R.string.onBoardingText1),
                OnBoardingModel(R.drawable.intro2, R.string.onBoardingTitle2, R.string.onBoardingText2),
                OnBoardingModel(R.drawable.intro3, R.string.onBoardingTitle3, R.string.onBoardingText3)
            )
        }
    }
}