package com.delhomme.jobbingtrack.events.ui.weeks


import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.LocalDate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.delhomme.jobbingtrack.commons.entities.CalendarViewType
import com.delhomme.jobbingtrack.events.viewmodels.EventViewModel
import kotlin.collections.map


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
