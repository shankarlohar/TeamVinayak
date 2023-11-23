package com.shankarlohar.teamvinayak.model

data class GymInfo(
    val address: String = "",
    val email: String = "",
    val genderPreference: String = "",
    val lateFee: Int = 0,
    val lockerRental: Int = 0,
    val logo: String = "",
    val membershipHoldingFree: Int = 0,
    val monthlyFee: Int = 0,
    val name: String = "",
    val openToday: Boolean = false,
    val phone: List<String> = emptyList(),
    val timing: List<String> = emptyList(),
    val totalMembers: Int = 0
)


