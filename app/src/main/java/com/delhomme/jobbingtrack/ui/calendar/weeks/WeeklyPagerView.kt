package com.delhomme.jobbingtrack.ui.calendar.weeks

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.ui.components.CalendarViewType
import java.time.LocalDate


@Composable
fun WeeklyPagerView(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate, CalendarViewType?) -> Unit
) {
    WeeklySliding3DayView(
        initialDate = selectedDate,
        events = FakeDataProvider.evenements,
        onDateSelected = onDateSelected
    )
}
