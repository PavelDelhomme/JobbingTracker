package com.delhomme.jobbingtrack.ui.major.calendar.event

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.delhomme.jobbingtrack.data.classes.*
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun EventCard(
    events: List<Evenement>,
    entretiens: List<EntretienWithContacts>,
    userId: String,
    event: Evenement,
    relatedEntretien: EntretienWithContacts? = null,
    relatedContacts: List<ContactEntity> = emptyList(),
    modifier: Modifier = Modifier,
    compact: Boolean = false
) {
    val start = Instant.ofEpochMilli(event.startDate?.toLong() ?: 0)
        .atZone(ZoneId.systemDefault())
        .toLocalTime()
        .format(DateTimeFormatter.ofPattern("HH:mm"))

    val end = event.endDate?.let {
        Instant.ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalTime()
            .format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    val cardColor = getEventColor(event.type)

    Card(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = cardColor)
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
                        relatedContacts.forEach { contact ->
                            Text("${contact.firstName} ${contact.lastName}")
                            Text(contact.phone ?: contact.email ?: "Pas de contact")
                        }
                    }
                    "Appels", "Relances", "Candidatures" -> {
                        Text(event.description ?: "Aucune description", style = MaterialTheme.typography.bodySmall)
                    }
                    "Relances" -> {
                        Text("Relance")
                        Text(event.description ?: "")
                    }
                    "Candidatures" -> {
                        Text("Candidature")
                        Text(event.title ?: "")
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
