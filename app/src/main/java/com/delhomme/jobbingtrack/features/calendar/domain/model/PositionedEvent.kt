package com.delhomme.jobbingtrack.features.calendar.domain.model


data class PositionedEvent(
    val event: Event,
    val column: Int,
    val totalColumns: Int
)
