package com.shankarlohar.teamvinayak.model

data class AttendanceModel(
    val date: String = "",
    val start: String = "",
    val end: String = "",
    val type: List<String> = emptyList(),
    val part: List<String> = emptyList(),
    val trainer: String = ""
)
