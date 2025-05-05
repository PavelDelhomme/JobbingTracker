package com.delhomme.jobbingtrack.ui.calendar.weeks

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.classes.Evenement
import com.delhomme.jobbingtrack.ui.components.CalendarViewType
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun WeeklyCalendarViewWithEvents(
    selectedDate: LocalDate,
    events: List<Evenement>,
    onDateSelected: (LocalDate, CalendarViewType?) -> Unit
) {
    val startOfWeek = selectedDate.with(DayOfWeek.MONDAY)
    val days = (0L..6L).map { startOfWeek.plusDays(it) }
    val hours = (0..23).toList()
    val eventsByDay = remember(events) {
        events.groupBy { Instant.ofEpochMilli(it.startDate).atZone(ZoneId.systemDefault()).toLocalDate() }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val columnWidth = maxWidth / 7
        val rowHeight = maxHeight / 24

        Column {
            Row {
                Spacer(modifier = Modifier.width(50.dp))
                days.forEach { day ->
                    Box(
                        modifier = Modifier
                            .width(columnWidth)
                            .height(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = day.dayOfWeek.name.take(3))
                    }
                }
            }

            for (hour in hours) {
                Row(modifier = Modifier.height(rowHeight)) {
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "${hour.toString().padStart(2, '0')}:00",
                            fontSize = MaterialTheme.typography.bodySmall.fontSize
                        )
                    }

                    days.forEach { day ->
                        val hourEvents = eventsByDay[day]?.filter {
                            Instant.ofEpochMilli(it.startDate).atZone(ZoneId.systemDefault()).hour == hour
                        } ?: emptyList()

                        Box(
                            modifier = Modifier
                                .width(columnWidth)
                                .fillMaxHeight()
                                .border(0.5.dp, MaterialTheme.colorScheme.outline)
                        ) {
                            Column {
                                hourEvents.forEach { event ->
                                    Text(
                                        text = event.title,
                                        style = MaterialTheme.typography.labelSmall,
                                        modifier = Modifier.padding(2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
