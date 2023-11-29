package com.shankarlohar.teamvinayak.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Attendance(
    val date: MutableState<String> = mutableStateOf(""),
    val start: MutableState<String> = mutableStateOf(""),
    val end: MutableState<String> = mutableStateOf(""),
    var type: MutableList<String> = mutableListOf<String>(),
    var part: MutableList<String> = mutableListOf<String>(),
    val trainer: MutableState<String> = mutableStateOf("")
)

data class AttendanceModel(
    val date: String = "",
    val start: String = "",
    val end: String = "",
    val type: List<String> = emptyList(),
    val part: List<String> = emptyList(),
    val trainer: String = ""
)

