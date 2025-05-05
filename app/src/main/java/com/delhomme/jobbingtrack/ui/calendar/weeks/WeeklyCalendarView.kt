package com.delhomme.jobbingtrack.ui.calendar.weeks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun WeeklyCalendarView(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val startOfWeek = selectedDate.with(DayOfWeek.MONDAY)
    val workWeek = (0L..4L).map { startOfWeek.plusDays(it) }

    Row(modifier = Modifier.fillMaxSize()) {
        workWeek.forEach { date ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clickable { onDateSelected(date) }
            ) {
                Text(
                    text = date.dayOfWeek.name.take(3),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = date.dayOfMonth.toString(),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}
