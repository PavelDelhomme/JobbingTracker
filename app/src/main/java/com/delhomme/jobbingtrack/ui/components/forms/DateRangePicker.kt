package com.delhomme.jobbingtrack.ui.components.forms

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.time.Instant
import java.time.ZoneId

@Composable
fun DateRangePicker(
    start: Instant,
    end: Instant,
    onStartPicked: (Instant)->Unit,
    onEndPicked:   (Instant)->Unit
) {
    val context = LocalContext.current
    Row(verticalAlignment = Alignment.CenterVertically) {
        TextButton(onClick = {
            // ouvrir DatePickerDialog pour `start`, puis appeler onStartPicked
        }) {
            Text(start.atZone(ZoneId.systemDefault()).toLocalDate().toString())
        }
        Text(" – ")
        TextButton(onClick = {
            // ouvrir DatePickerDialog pour `end`, puis appeler onEndPicked
        }) {
            Text(end.atZone(ZoneId.systemDefault()).toLocalDate().toString())
        }
        Spacer(Modifier.weight(1f))
        IconButton(onClick = { /* rafraîchir */ }) {
            Icon(Icons.Default.Refresh, contentDescription = "Actualiser")
        }
    }
}
