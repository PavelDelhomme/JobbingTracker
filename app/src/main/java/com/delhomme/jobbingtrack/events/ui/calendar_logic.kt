package com.delhomme.jobbingtrack.events.ui

import com.delhomme.jobbingtrack.events.Event


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
