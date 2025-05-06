package com.delhomme.jobbingtrack.ui.calendar.event

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.classes.*
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun EventCard(event: Evenement, modifier: Modifier = Modifier, compact: Boolean = false) {
    val start = Instant.ofEpochMilli(event.startDate)
        .atZone(ZoneId.systemDefault())
        .toLocalTime()
        .format(DateTimeFormatter.ofPattern("HH:mm"))

    val end = event.endDate?.let {
        Instant.ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalTime()
            .format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    Card(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
    ) {
        Column(
            modifier = Modifier.padding(if (compact) 4.dp else 10.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                "${event.type}: ${event.title}",
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1
            )
            Text(
                "$start - ${end ?: "??:??"}",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1
            )

            if (!compact) {
                when (event.type) {
                    "Entretiens" -> {
                        val entretien = FakeDataProvider.entretiens.find { it.id == event.relatedObjectId }
                        entretien?.let {
                            val contacts = FakeDataProvider.contacts.filter { c -> c.id in it.contacts }
                            contacts.forEach { contact ->
                                Text("${contact.firstName} ${contact.lastName}", style = MaterialTheme.typography.bodySmall)
                                Text(contact.phone ?: contact.email ?: "Pas de contact", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                    "Appels", "Relances", "Candidatures" -> {
                        Text(event.description ?: "Aucune description", style = MaterialTheme.typography.bodySmall)
                    }
                    else -> {
                        event.description?.let {
                            Text(it, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
