package com.delhomme.jobbingtrack.etc.core.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/*
data class PositionedEvent(
    val event: Event,
    val column: Int,
    val totalColumns: Int
)

fun computeOverlappingEvents(events: List<Event>): List<PositionedEvent> {
    val positionedEvents = mutableListOf<PositionedEvent>()

    val sorted = events.sortedBy { it.startDate }
    val active = mutableListOf<Pair<Event, Int>>() // Pair(event, column)

    for (event in sorted) {
        val start = event.startDate
        val end = event.endDate ?: (start?.plus(30 * 60 * 1000)) // default 30 min

        active.removeIf { (activeEvent, _) ->
            val aEnd = activeEvent.endDate ?: (activeEvent.startDate?.plus(30 * 60 * 1000))
            aEnd!! <= start?.toLong()!!
        }

        val usedColumns = active.map { it.second }.toSet()
        val freeColumn = (0..Int.MAX_VALUE).first { it !in usedColumns }

        active.add(Pair(event, freeColumn))
        positionedEvents.add(PositionedEvent(event, freeColumn, active.size))
    }

    return positionedEvents
}




@Composable
fun EventCard(
    events: List<Event>,
    interviews: List<InterviewWithContacts>,
    userId: String,
    event: Event,
    relatedInterview: InterviewWithContacts? = null,
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
                    "Interviews" -> {
                        relatedContacts.forEach { contact ->
                            Text("${contact.firstName} ${contact.lastName}")
                            Text(contact.phone ?: contact.email ?: "Pas de contact")
                        }
                    }
                    "Calls" -> {
                        Text(event.description ?: "Aucune description", style = MaterialTheme.typography.bodySmall)
                    }
                    "FollowUps" -> {
                        Text("Relance")
                        Text(event.description ?: "")
                    }
                    "Applications" -> {
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




fun getEventColor(type: String): Color {
    return when (type) {
        "Applications" -> Color(0xFFFFF176)
        "FollowUps" -> Color(0xFFFF8A65)
        "Interviews" -> Color(0xFF4FC3F7)
        "Calls" -> Color(0xFFA1887F)
        "Forecast" -> Color(0xFFCE93D8)
        else -> Color(0xFFB0BEC5)
    }
}
 */