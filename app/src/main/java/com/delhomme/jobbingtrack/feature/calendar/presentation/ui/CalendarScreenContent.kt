package com.delhomme.jobbingtrack.feature.calendar.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.events.ui.CalendarTopBar
import com.delhomme.jobbingtrack.events.ui.PlanningView
import com.delhomme.jobbingtrack.events.ui.day.DailyPagerView
import com.delhomme.jobbingtrack.events.ui.monthly.MonthlyCalendarView
import com.delhomme.jobbingtrack.events.ui.weeks.TwoWeekPagerView
import com.delhomme.jobbingtrack.events.ui.weeks.WeeklyPagerView
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
    val interviewViewModel: InterviewViewModel = viewModel()
    val contactViewModel: ContactViewModel = viewModel()
    val callViewModel: CallViewModel = viewModel()
    val followUpViewModel: FollowUpViewModel = viewModel()
    val applicationViewModel: ApplicationViewModel = viewModel()

    val interviewsWithContacts by remember(userId) {
        interviewViewModel.getAllWithContacts(userId)
    }.observeAsState(initial = emptyList())
    val contacts by contactViewModel.allForUser(userId).observeAsState(emptyList())
    val calls by callViewModel.allForUser(userId).observeAsState(emptyList())
    val followsUps by followUpViewModel.allForUser(userId).observeAsState(emptyList())
    val applications by applicationViewModel.allForUser(userId).observeAsState(emptyList())

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
                events = filteredEvents,
                onDateChange = { newDate -> onDateSelected(newDate, null) },
                userId = userId,
                interviews = interviewsWithContacts,
                contacts = contacts,
                applications = applications,
                calls = calls,
                followsUp = followsUps
            )


            CalendarViewType.WEEK -> WeeklyPagerView(
                selectedDate = currentDate,
                onDateSelected = onDateSelected,
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