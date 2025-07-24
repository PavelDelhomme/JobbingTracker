package com.delhomme.jobbingtrack.core.utils

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


/**
 * Agrégation d'une liste d'entités datés par jour.
 */
fun <T> List<T>.countByDay(
    dateSelector: (T) -> Instant,
    start: Instant,
    end: Instant
): List<Pair<LocalDate, Int>> {
    return this
        .asSequence()
        .filter { item ->
            val ts = dateSelector(item).toEpochMilli()
            ts in start.toEpochMilli()..end.toEpochMilli()
        }
        .groupBy { item ->
            dateSelector(item)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        }
        .map { (day, items) -> day to items.size }
        .sortedBy { it.first }
}
