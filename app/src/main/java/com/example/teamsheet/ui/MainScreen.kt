package com.example.teamsheet.ui
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.teamsheet.data.Team
import com.example.teamsheet.ui.components.PlayerList
import com.example.teamsheet.viewmodel.LineupViewModel

@Composable
fun MainScreen(viewModel: LineupViewModel = viewModel()) {
    val players by viewModel.players.collectAsState()

    val teamA = players.filter { it.team == Team.A }
    val teamB = players.filter { it.team == Team.B }
    val unassigned = players.filter { it.team == Team.NONE }

    Row(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
        PlayerList("Team A", teamA) { player ->
            viewModel.assignPlayerToTeam(player.id, Team.B)
        }
        PlayerList("Unassigned", unassigned) { player ->
            viewModel.assignPlayerToTeam(player.id, Team.A)
        }
        PlayerList("Team B", teamB) { player ->
            viewModel.assignPlayerToTeam(player.id, Team.A)
        }
    }
}
