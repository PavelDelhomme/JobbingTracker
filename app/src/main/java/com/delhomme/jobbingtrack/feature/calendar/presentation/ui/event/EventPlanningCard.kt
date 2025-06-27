package com.delhomme.jobbingtrack.feature.calendar.presentation.ui.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@Composable
fun EventPlanningCard(event: EventEntity) {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val zone = ZoneId.systemDefault()

    val start = Instant.ofEpochMilli(event.startDate?.toLong() ?: 0).atZone(zone).format(formatter)
    val end = event.endDate?.let {
        Instant.ofEpochMilli(it).atZone(zone).format(formatter)
    } ?: "??:??"

    val cardColor = getEventColor(event.type)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(Modifier.padding(10.dp)) {
            Text("${event.type}: ${event.title}", style = MaterialTheme.typography.titleSmall)
            Text("$start - $end", style = MaterialTheme.typography.bodySmall)

            event.description?.let {
                Spacer(Modifier.height(4.dp))
                Text(it, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}