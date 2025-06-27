package com.delhomme.jobbingtrack.features.calendar.presentation.ui.week.weekly

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.features.calendar.domain.model.Event
import com.delhomme.jobbingtrack.features.calendar.utils.computeOverlappingEvents
import com.delhomme.jobbingtrack.features.calendar.utils.enums.CalendarViewType
import kotlinx.coroutines.delay
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.collections.get


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun WeeklySliding3DayView(
    initialDate: LocalDate,
    events: List<Event>,
    onDateSelected: (LocalDate, CalendarViewType?) -> Unit
) {
    var currentStartDate by remember { mutableStateOf(initialDate) }

    val days = (0L..2L).map { currentStartDate.plusDays(it) }
    val eventsByDay = remember(events) {
        events.groupBy {
            Instant.ofEpochMilli(it.startDate ?: 0L).atZone(ZoneId.systemDefault()).toLocalDate()
        }
    }

    var scaleY by remember { mutableStateOf(1f) }
    val minScale = 0.5f
    val maxScale = 3f
    val outlineColor = MaterialTheme.colorScheme.outline
    val scrollState = rememberScrollState()
    val density = LocalDensity.current
    val currentTime by produceState(LocalDateTime.now()) {
        while (true) {
            value = LocalDateTime.now()
            delay(60000) // mise à jour toutes les minutes
        }
    }

    val baseRowHeight = 60.dp
    val totalMinutes = 24 * 60
    val scaledRowHeightPx = with(density) { (baseRowHeight * scaleY).toPx() }
    val columnWidth = with(density) { (LocalDensity.current.run { 1f / 3f * LocalDensity.current.density * 360f }).dp } // facultatif si tu ne connais pas la largeur

    val redLineOffset = (currentTime.hour * 60 + currentTime.minute) * scaledRowHeightPx / 60

    var selectedEvent by remember { mutableStateOf<Event?>(null) }

    val totalHeightPx = totalMinutes * scaledRowHeightPx
    val totalHeightDp = with(density) { totalHeightPx.toDp() }

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        // Header
        Row(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(56.dp)) // Espace pour les heures
            days.forEach { day ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(32.dp)
                        .clickable { onDateSelected(day, CalendarViewType.DAY) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "${day.dayOfWeek.name.take(3)} ${day.dayOfMonth}")
                }
            }
        }

        // Scrollable grid
        Row {
            // Colonne des heures
            Column(modifier = Modifier.width(56.dp)) {
                for (hour in 0..23) {
                    Box(
                        modifier = Modifier
                            .height(with(density) { (60 * scaleY).dp })
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "%02d:00".format(hour),
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(end = 4.dp, top = 2.dp)
                        )
                    }
                }
            }

            // Grille + évènement
            Box(
                modifier = Modifier
                    .height(totalHeightDp)
                    .fillMaxWidth()
            ) {
                // Grid lines
                Canvas(modifier = Modifier.matchParentSize()) {
                    for (i in 1 until 3) {
                        val x = size.width * i / 3
                        drawLine(
                            color = outlineColor,
                            start = Offset(x, 0f),
                            end = Offset(x, size.height),
                            strokeWidth = 1f
                        )
                    }
                    for (h in 1 until 24) {
                        val y = h * 60 * scaledRowHeightPx
                        drawLine(
                            color = outlineColor,
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = 1f
                        )
                    }
                    if (days.contains(LocalDate.now())) {
                        drawLine(
                            color = Color.Red,
                            start = Offset(0f, redLineOffset),
                            end = Offset(size.width, redLineOffset),
                            strokeWidth = 2.dp.toPx()
                        )
                    }
                }

                // Events
                days.forEachIndexed { index, day ->
                    val dayEvents = eventsByDay[day] ?: emptyList()
                    val positionedEvent = computeOverlappingEvents(dayEvents)
                    positionedEvent.forEach { positioned  ->
                        // Positionnement des événements côte à côte sans chevauchement visuel
                        val event = positioned.event
                        val columnSpan = 1f / positioned.totalColumns
                        val eventWidth = columnWidth * columnSpan
                        val xOffset = columnWidth * index + eventWidth * positioned.column

                        val start = Instant.ofEpochMilli(event.startDate ?: 0L)
                            .atZone(ZoneId.systemDefault()).toLocalTime()
                        val end = event.endDate?.let {
                            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalTime()
                        } ?: start.plusMinutes(30)

                        val startMinutes = start.hour * 60 + start.minute
                        val duration = (end.hour * 60 + end.minute - startMinutes).coerceAtLeast(15)

                        val topOffset = with(density) { (startMinutes * scaledRowHeightPx).toDp() }
                        val height = with(density) { (duration * scaledRowHeightPx).toDp() }

                        Box(
                            modifier = Modifier
                                .absoluteOffset(
                                    x = xOffset,
                                    y = topOffset
                                )
                                .width(eventWidth)
                                .height(height)
                                .padding(1.dp)
                                .background(
                                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
                                    RoundedCornerShape(4.dp)
                                )
                                .clickable { selectedEvent = event }
                        ) {
                            Text(
                                event.title,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(2.dp)
                            )
                        }
                    }
                }
                // Popup
                selectedEvent?.let { event ->
                    AlertDialog(
                        onDismissRequest = { selectedEvent = null },
                        title = { Text("${event.type}: ${event.title}") },
                        text = { Text(event.description ?: "") },
                        confirmButton = {
                            TextButton(onClick = { selectedEvent = null }) {
                                Text("Fermer")
                            }
                        }
                    )
                }
            }
        }
    }

}