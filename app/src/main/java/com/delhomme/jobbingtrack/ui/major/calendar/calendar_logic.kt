package com.delhomme.jobbingtrack.ui.major.calendar

import com.delhomme.jobbingtrack.data.classes.Evenement

data class PositionedEvent(
    val event: Evenement,
    val column: Int,
    val totalColumns: Int
)

fun computeOverlappingEvents(events: List<Evenement>): List<PositionedEvent> {
    val positionedEvents = mutableListOf<PositionedEvent>()

    val sorted = events.sortedBy { it.startDate }
    val active = mutableListOf<Pair<Evenement, Int>>() // Pair(event, column)

    for (event in sorted) {
        val start = event.startDate
        val end = event.endDate ?: (start + 30 * 60 * 1000) // default 30 min

        active.removeIf { (activeEvent, _) ->
            val aEnd = activeEvent.endDate ?: (activeEvent.startDate + 30 * 60 * 1000)
            aEnd <= start
        }

        val usedColumns = active.map { it.second }.toSet()
        val freeColumn = (0..Int.MAX_VALUE).first { it !in usedColumns }

        active.add(Pair(event, freeColumn))
        positionedEvents.add(PositionedEvent(event, freeColumn, active.size))
    }

    return positionedEvents
}
