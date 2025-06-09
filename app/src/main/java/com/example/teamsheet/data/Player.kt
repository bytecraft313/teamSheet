package com.example.teamsheet.data

enum class Team { A, B, NONE }

data class Player(
    val id: Int,
    val name: String,
    var isRegular: Boolean = true,
    var team: Team = Team.NONE
)
