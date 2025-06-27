package com.delhomme.jobbingtrack.feature.calendar.utils

import androidx.compose.ui.graphics.Color


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