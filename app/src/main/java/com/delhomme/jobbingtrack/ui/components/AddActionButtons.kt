package com.delhomme.jobbingtrack.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AddActionButtons(
    onAddFollowUp: () -> Unit,
    onAddCall: () -> Unit,
    onAddInterview: () -> Unit,
    onAddContact: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            if (expanded) {
                SmallFloatingActionButton(onClick = { expanded = false; onAddFollowUp() }) {
                    Icon(Icons.Default.Alarm, contentDescription = "Ajouter Relance")
                }
                SmallFloatingActionButton(onClick = { expanded = false; onAddCall() }) {
                    Icon(Icons.Default.Phone, contentDescription = "Ajouter Appel")
                }
                SmallFloatingActionButton(onClick = { expanded = false; onAddInterview() }) {
                    Icon(Icons.Default.Chat, contentDescription = "Ajouter Entretien")
                }
                SmallFloatingActionButton(onClick = { expanded = false; onAddContact() }) {
                    Icon(Icons.Default.Person, contentDescription = "Ajouter Contact")
                }
            }

            FloatingActionButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter")
            }
        }
    }
}
