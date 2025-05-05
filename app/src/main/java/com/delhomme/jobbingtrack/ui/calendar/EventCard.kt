package com.delhomme.jobbingtrack.ui.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.classes.Evenement
import java.time.Instant
import java.time.ZoneId

@Composable
fun EventCard(event: Evenement, modifier: Modifier = Modifier) {
    val start = Instant.ofEpochMilli(event.startDate).atZone(ZoneId.systemDefault()).toLocalTime()
    val end = event.endDate?.let { Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalTime() }

    Card(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            Text("${event.title} — ${event.description ?: ""}", style = MaterialTheme.typography.labelMedium)
            Text("${event.type}: ${event.title}", style = MaterialTheme.typography.labelMedium)
            Text("${start} - ${end ?: ""}", style = MaterialTheme.typography.bodySmall)
            event.description?.let {
                Text(it, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}