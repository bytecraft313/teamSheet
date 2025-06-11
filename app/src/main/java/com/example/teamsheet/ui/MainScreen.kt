package com.example.teamsheet.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import com.example.teamsheet.data.Player

@Composable
fun MainScreen() {
    var playerName by remember { mutableStateOf("") }
    var isReserve by remember { mutableStateOf(false) }
    var players by remember { mutableStateOf(listOf<Player>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = playerName,
            onValueChange = { playerName = it },
            label = { Text("Player Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isReserve,
                onCheckedChange = { isReserve = it }
            )
            Text("Reserve Player")
        }

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
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Player")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(players) { player ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${player.name} ${if (player.isReserve) "(Reserve)" else ""}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    IconButton(onClick = {
                        players = players.filterNot { it.id == player.id }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove player"
                        )
                    }
                }
            }
        }

