package com.delhomme.jobbingtrack.ui.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.ui.calendar.day.DailyPagerView
import com.delhomme.jobbingtrack.ui.calendar.monthly.MonthlyCalendarView
import com.delhomme.jobbingtrack.ui.calendar.weeks.TwoWeekPagerView
import com.delhomme.jobbingtrack.ui.calendar.weeks.WeeklyPagerView
import com.delhomme.jobbingtrack.ui.components.CalendarViewType
import java.time.LocalDate


@Composable
fun CalendarScreenContent(
    currentDate: LocalDate,
    filterStates: Map<String, Boolean>,
    onFilterChange: (String, Boolean) -> Unit,
    calendarViewType: CalendarViewType,
    onDateSelected: (LocalDate, CalendarViewType?) -> Unit
) {
    val filteredEvents = remember(filterStates) {
        FakeDataProvider.evenements.filter { event ->
            filterStates[event.type] == true
        }
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
                onDateSelected = onDateSelected
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