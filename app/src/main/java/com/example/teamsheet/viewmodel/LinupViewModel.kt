package com.example.teamsheet.viewmodel

import androidx.lifecycle.ViewModel
import com.example.teamsheet.data.Player
import com.example.teamsheet.data.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LineupViewModel : ViewModel() {
    private val _players = MutableStateFlow<List<Player>>(listOf(
        Player(1, "Alice"),
        Player(2, "Bob"),
        Player(3, "Charlie", false), // Reserved
        Player(4, "Diana")
    ))
    val players: StateFlow<List<Player>> = _players

    fun assignPlayerToTeam(playerId: Int, team: Team) {
        _players.value = _players.value.map {
            if (it.id == playerId) it.copy(team = team) else it
        }
    }
}
