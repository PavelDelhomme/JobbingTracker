package com.delhomme.jobbingtrack.ui.major.calendar.event

import androidx.compose.ui.graphics.Color

fun getEventColor(type: String): Color {
    return when (type) {
        "Candidature" -> Color(0xFFFFF176)
        "Relances" -> Color(0xFFFF8A65)
        "Entretiens" -> Color(0xFF4FC3F7)
        "Appels" -> Color(0xFFA1887F)
        "Prévisionnel" -> Color(0xFFCE93D8)
        else -> Color(0xFFB0BEC5)
    }
}