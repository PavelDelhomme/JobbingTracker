package com.delhomme.jobbingtrack.ui.major.calendar.weeks

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.classes.events.Event
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun TwoWeekCalendarView(
    startDate: LocalDate,
    events: List<Event>,
    modifier: Modifier = Modifier
) {
    val days = (0L until 14L).map { startDate.plusDays(it) }
    val hours = (0..23).toList()

    // Group events by day
    val eventsByDay = remember(events) {
        events.groupBy { event ->
            Instant.ofEpochMilli(event.startDate ?: 0L).atZone(ZoneId.systemDefault()).toLocalDate()
        }
    }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val columnWidth = maxWidth / 7
        val rowHeight = maxHeight / 24

        // Grid
        Column {
            // Header: days
            Row {
                Spacer(modifier = Modifier.width(50.dp)) // for hour labels
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

            // Rows: hours
            for (hour in hours) {
                Row(modifier = Modifier.height(rowHeight)) {
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(text = "${hour.toString().padStart(2, '0')}:00", fontSize = MaterialTheme.typography.bodySmall.fontSize)
                    }

                    days.forEach { day ->
                        Box(
                            modifier = Modifier
                                .width(columnWidth)
                                .fillMaxHeight()
                                .border(0.5.dp, MaterialTheme.colorScheme.outline)
                        ) {
                            val dayEvents = eventsByDay[day]?.filter {
                                Instant.ofEpochMilli(it.startDate ?: 0L)
                                    .atZone(ZoneId.systemDefault()).hour == hour
                            } ?: emptyList()

                            Column {
                                dayEvents.forEach { event ->
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
