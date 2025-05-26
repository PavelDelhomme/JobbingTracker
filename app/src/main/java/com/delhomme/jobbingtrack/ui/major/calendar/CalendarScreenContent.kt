package com.delhomme.jobbingtrack.ui.major.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.data.viewmodel.EventViewModel
import com.delhomme.jobbingtrack.ui.major.calendar.day.DailyPagerView
import com.delhomme.jobbingtrack.ui.major.calendar.monthly.MonthlyCalendarView
import com.delhomme.jobbingtrack.ui.major.calendar.weeks.TwoWeekPagerView
import com.delhomme.jobbingtrack.ui.major.calendar.weeks.WeeklyPagerView
import com.delhomme.jobbingtrack.ui.components.navigation.CalendarViewType
import java.time.LocalDate


@Composable
fun CalendarScreenContent(
    currentDate: LocalDate,
    filterStates: Map<String, Boolean>,
    onFilterChange: (String, Boolean) -> Unit,
    calendarViewType: CalendarViewType,
    onDateSelected: (LocalDate, CalendarViewType?) -> Unit,
    userId: String,
    eventViewModel: EventViewModel = viewModel()
) {
    val allEvents by eventViewModel.eventsForUser(userId).observeAsState(emptyList())

    val filteredEvents = remember(allEvents, filterStates) {
        allEvents.filter { filterStates[it.type] == true}
    }

    Column {
        CalendarTopBar(
            selectedDate = currentDate,
            onGoToToday = {
                onDateSelected(LocalDate.now(), CalendarViewType.DAY)
            }
        )

        when (calendarViewType) {
            CalendarViewType.DAY -> DailyPagerView(
                initialDate = currentDate,
                events = filteredEvents
            ) { newDate ->
                onDateSelected(newDate, null)
            }

            CalendarViewType.WEEK -> WeeklyPagerView(
                selectedDate = currentDate,
                onDateSelected = onDateSelected,
                filteredEvents = filteredEvents,
                userId = userId,
            )

            CalendarViewType.TWO_WEEKS -> TwoWeekPagerView(
                startDate = currentDate,
                events = filteredEvents,
                modifier = Modifier.fillMaxSize()
            )

            CalendarViewType.MONTH -> MonthlyCalendarView(
                selectedDate = currentDate,
                events = filteredEvents,
                onDateSelected = { clickedDate ->
                    onDateSelected(clickedDate, CalendarViewType.DAY)
                }
            )

            CalendarViewType.PLANNING -> PlanningView(
                date = currentDate,
                events = filteredEvents
            )
        }
    }
}