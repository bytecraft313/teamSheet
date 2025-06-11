package com.example.teamsheet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.teamsheet.data.Player

@Composable
fun PlayerRow(player: Player, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(player.name, style = MaterialTheme.typography.bodyLarge)
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Remove player"
            )
        }
    }
}

@Composable
fun MainScreen() {
    var playerName by remember { mutableStateOf("") }
    var isReserve by remember { mutableStateOf(false) }
    var players by remember { mutableStateOf(listOf<Player>()) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Player display area
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp) // Leave space for input section
        ) {
            Text("Main", style = MaterialTheme.typography.titleMedium)
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(players.filter { !it.isReserve }) { player ->
                    PlayerRow(player) {
                        players = players.filterNot { it.id == player.id }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Reserve", style = MaterialTheme.typography.titleMedium)
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(players.filter { it.isReserve }) { player ->
                    PlayerRow(player) {
                        players = players.filterNot { it.id == player.id }
                    }
                }
            }
        }

        // Input area fixed at the bottom
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = playerName,
                    onValueChange = { playerName = it },
                    label = { Text("Player Name") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (playerName.isNotBlank()) {
                            val newPlayer = Player(
                                id = (players.maxOfOrNull { it.id } ?: 0) + 1,
                                name = playerName,
                                isReserve = isReserve
                            )
                            players = players + newPlayer
                            playerName = ""
                            isReserve = false
                        }
                    }
                ) {
                    Text("Add")
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Checkbox(
                    checked = isReserve,
                    onCheckedChange = { isReserve = it }
                )
                Text("Reserve Player")
            }
        }
    }
}
