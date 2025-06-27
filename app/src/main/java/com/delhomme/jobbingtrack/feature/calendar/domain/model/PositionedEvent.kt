package com.delhomme.jobbingtrack.feature.calendar.domain.model

import com.delhomme.jobbingtrack.events.Event


data class PositionedEvent(
    val event: Event,
    val column: Int,
    val totalColumns: Int
)
