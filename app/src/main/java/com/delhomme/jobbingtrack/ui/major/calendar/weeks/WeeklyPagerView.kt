package com.delhomme.jobbingtrack.ui.major.calendar.weeks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.data.local.entities.EventEntity
import com.delhomme.jobbingtrack.data.viewmodel.EventViewModel
import com.delhomme.jobbingtrack.ui.components.navigation.CalendarViewType
import java.time.LocalDate


@Composable
fun WeeklyPagerView(
    selectedDate: LocalDate,
    eventViewModel: EventViewModel = viewModel(),
    userId: String,
    onDateSelected: (LocalDate, CalendarViewType?) -> Unit
) {
    val events by eventViewModel.eventsForUser(userId).observeAsState(emptyList())

    WeeklySliding3DayView(
        initialDate = selectedDate,
        events = EventViewModel.getEventsForUser(userId),
        onDateSelected = onDateSelected
    )
}
