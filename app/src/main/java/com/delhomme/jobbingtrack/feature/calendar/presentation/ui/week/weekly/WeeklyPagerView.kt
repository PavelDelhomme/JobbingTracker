package com.delhomme.jobbingtrack.feature.calendar.presentation.ui.week.weekly

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.events.ui.weeks.WeeklySliding3DayView
import java.time.LocalDate


@Composable
fun WeeklyPagerView(
    selectedDate: LocalDate,
    eventViewModel: EventViewModel = viewModel(),
    userId: String,
    onDateSelected: (LocalDate, CalendarViewType?) -> Unit
) {
    val eventEntities by eventViewModel.eventsForUser(userId).observeAsState(emptyList())
    val events = remember(eventEntities) { eventEntities.map { it.toDomain() } }

    WeeklySliding3DayView(
        initialDate = selectedDate,
        events = events,
        onDateSelected = onDateSelected
    )
}
