package com.example.teamsheet.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.teamsheet.data.Player
import com.example.teamsheet.data.Team

@Composable
fun PlayerList(
    title: String,
    players: List<Player>,
    onPlayerClick: (Player) -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        for (player in players) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { onPlayerClick(player) }
            ) {
                Text(
                    text = player.name + if (!player.isRegular) " (Res)" else "",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
