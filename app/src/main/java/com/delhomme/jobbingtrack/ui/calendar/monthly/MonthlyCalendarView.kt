package com.delhomme.jobbingtrack.ui.calendar.monthly

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.classes.Evenement
import com.delhomme.jobbingtrack.ui.calendar.day.DayContent
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun MonthlyCalendarView(
    selectedDate: LocalDate,
    events: List<Evenement>,
    onDateSelected: (LocalDate) -> Unit
) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(12) }
    val endMonth = remember { currentMonth.plusMonths(12) }
    val daysOfWeek = remember { daysOfWeek() }

    val groupedEvents = remember(events) {
        events.groupBy {
            Instant.ofEpochMilli(it.startDate).atZone(ZoneId.systemDefault()).toLocalDate()
        }
    }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )

    VerticalCalendar(
        state = state,
        dayContent = { day ->
            val eventsForDay = groupedEvents[day.date] ?: emptyList()
            DayContent(
                day,
                eventsForDay,
                isSelected = day.date == selectedDate,
                onClick = { onDateSelected(day.date) }
            )
        },
        monthHeader = { month ->
            Text(
                text = month.yearMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
    )
}
