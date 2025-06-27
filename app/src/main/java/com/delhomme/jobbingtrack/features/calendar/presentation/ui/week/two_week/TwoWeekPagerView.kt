package com.delhomme.jobbingtrack.features.calendar.presentation.ui.week.two_week

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import java.time.LocalDate


@Composable
fun TwoWeekPagerView(
    startDate: LocalDate,
    events: List<EventEntity>,
    modifier: Modifier = Modifier
) {
    // Placeholder temporaire
    Text("Vue sur deux semaines à faire à partir du $startDate")
}
