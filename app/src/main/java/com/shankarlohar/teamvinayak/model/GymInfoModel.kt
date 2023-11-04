package com.shankarlohar.teamvinayak.data.model

import kotlinx.coroutines.flow.MutableStateFlow

data class GymInfoModel(
    val logo: String = "",
    val name: String = "",
    val email: String = "",
    val address: String = "",
    val genderPreference: String = "",
    val lateFee: Int = 0,
    val lockerRental: Int = 0,
    val monthlyFee: Int = 0,
    val membershipHoldingFree: Int = 0,
    val openToday: Boolean = false,
    val phone: List<String> = emptyList(),
    val timing: List<String> = emptyList()
)