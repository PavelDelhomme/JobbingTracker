package com.delhomme.jobbingtrack.ui.calendar.day

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.classes.Evenement
import com.delhomme.jobbingtrack.ui.calendar.EventCard
import com.delhomme.jobbingtrack.ui.calendar.computeOverlappingEvents
import kotlinx.coroutines.delay
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun InteractiveDayView(
    date: LocalDate,
    events: List<Evenement>,
    modifier: Modifier = Modifier
) {
    val minScale = 0.5f
    val maxScale = 2.5f
    var scale by remember { mutableStateOf(1f) }
    val verticalScroll = rememberScrollState()

    val currentTime by produceState(initialValue = LocalDateTime.now()) {
        while (true) {
            value = LocalDateTime.now()
            delay(1000)
        }
    }

    val nowHour = currentTime.hour + currentTime.minute / 60f
    val redLineOffsetDp = (nowHour * 60 * scale).dp

    LaunchedEffect(Unit) {
        if (date == LocalDate.now()) {
            verticalScroll.scrollTo((redLineOffsetDp.value - 200).coerceAtLeast(0f).toInt())
        }
    }

    val dayEvents = remember(events) {
        events.filter {
            Instant.ofEpochMilli(it.startDate).atZone(ZoneId.systemDefault()).toLocalDate() == date
        }
    }
    val positionedEvents = computeOverlappingEvents(dayEvents)

    var selectedEvent by remember { mutableStateOf<Evenement?>(null) }

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(verticalScroll)
                .pointerInput(Unit) {
                    detectTransformGestures { _, _, zoom, _ ->
                        scale = (scale * zoom).coerceIn(minScale, maxScale)
                    }
                }
        ) {
            val totalMinutes = 24 * 60
            val totalHeightDp = (totalMinutes * scale).dp

            // Fond avec lignes horaires
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(totalHeightDp)
            ) {
                for (hour in 0..23) {
                    val y = hour * 60 * scale
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, y.toFloat()),
                        end = Offset(size.width, y.toFloat()),
                        strokeWidth = 1.dp.toPx()
                    )
                }
            }

            // Étiquettes d'heure à gauche
            Column(
                modifier = Modifier
                    .height(totalHeightDp)
                    .width(48.dp)
                    .absoluteOffset(x = 0.dp)
            ) {
                for (hour in 0..23) {
                    val height = (60 * scale).dp
                    Box(
                        modifier = Modifier.height(height),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Text(
                            text = String.format("%02d:00", hour),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            // Événements positionnés
            Box(modifier = Modifier
                .padding(start = 48.dp)
                .height(totalHeightDp)
            ) {
                positionedEvents.forEach { positioned ->
                    val startTime = Instant.ofEpochMilli(positioned.event.startDate)
                        .atZone(ZoneId.systemDefault())
                        .toLocalTime()
                    val endTime = positioned.event.endDate?.let {
                        Instant.ofEpochMilli(it)
                            .atZone(ZoneId.systemDefault())
                            .toLocalTime()
                    } ?: startTime.plusMinutes(30)

                    val startMinutes = startTime.hour * 60 + startTime.minute
                    val endMinutes = endTime.hour * 60 + endTime.minute
                    val durationMinutes = (endMinutes - startMinutes).coerceAtLeast(15)

                    val topOffsetDp = (startMinutes * scale).dp
                    val heightDp = (durationMinutes * scale).dp
                    val columnWidthFraction = 1f / positioned.totalColumns

                    Box(
                        modifier = Modifier
                            .absoluteOffset(y = topOffsetDp, x = (positioned.column * (columnWidthFraction * 100)).dp)
                            .fillMaxWidth(columnWidthFraction)
                            .height(heightDp)
                            .padding(2.dp)
                            .clickable { selectedEvent = positioned.event }
                    ) {
                        Text(
                            text = positioned.event.title,
                            style = MaterialTheme.typography.labelMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

        // Ligne rouge de l’heure actuelle
        if (date == LocalDate.now()) {
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .offset(y = redLineOffsetDp)
                .height(2.dp)
            ) {
                drawLine(
                    color = Color.Red,
                    start = Offset(0f, 1f),
                    end = Offset(size.width, 1f),
                    strokeWidth = 2.dp.toPx()
                )
            }
        }

        // Détails d’événement
        selectedEvent?.let { event ->
            AlertDialog(
                onDismissRequest = { selectedEvent = null },
                title = { Text(text = event.title) },
                text = {
                    Column {
                        Text("Début : ${Instant.ofEpochMilli(event.startDate).atZone(ZoneId.systemDefault()).toLocalTime()}")
                        event.endDate?.let {
                            Text("Fin : ${Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalTime()}")
                        }
                        event.description?.let {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(it)
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { selectedEvent = null }) {
                        Text("Fermer")
                    }
                }
            )
        }
    }
}
