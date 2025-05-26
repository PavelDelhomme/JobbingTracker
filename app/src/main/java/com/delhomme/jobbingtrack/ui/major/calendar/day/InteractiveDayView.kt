package com.delhomme.jobbingtrack.ui.major.calendar.day

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.classes.Evenement
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import com.delhomme.jobbingtrack.data.local.entities.EventEntity
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity
import com.delhomme.jobbingtrack.ui.major.calendar.event.EventCard
import com.delhomme.jobbingtrack.ui.major.calendar.computeOverlappingEvents
import com.delhomme.jobbingtrack.utils.mappers.toDomain
import kotlinx.coroutines.delay
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun InteractiveDayView(
    date: LocalDate,
    eventEntities: List<EventEntity>,
    userId: String,
    entretiens: List<EntretienWithContacts> = emptyList(),
    contacts: List<ContactEntity> = emptyList(),
    appels: List<AppelEntity> = emptyList(),
    relances: List<RelanceEntity> = emptyList(),
    candidatures: List<CandidatureEntity> = emptyList(),
    modifier: Modifier = Modifier
) {
    val minScale = 0.5f
    val maxScale = 2.5f
    var scale by remember { mutableStateOf(1f) }
    val scrollState = rememberScrollState()

    val density = LocalDensity.current

    val currentTime by produceState(LocalDateTime.now()) {
        while (true) {
            value = LocalDateTime.now()
            delay(60000) // update chaque minute suffit
        }
    }

    val events = eventEntities.map { it.toDomain() }

    val positionedEvents = remember(events) {
        computeOverlappingEvents(
            events.filter {
                Instant.ofEpochMilli(it.startDate).atZone(ZoneId.systemDefault()).toLocalDate() == date
            }
        )
    }

    var selectedEvent by remember { mutableStateOf<Evenement?>(null) }

    val totalHeightDp = (24 * 60 * scale).dp
    val redLineOffset = ((currentTime.hour * 60 + currentTime.minute) * scale).dp

// Scroll initial corrigé vers l'heure actuelle avec gestion d'offset en pixels correcte
    LaunchedEffect(date, scale) {
        if (date == LocalDate.now()) {
            val initialOffsetPx = with(density) { (redLineOffset - 200.dp).toPx() }
            scrollState.scrollTo(initialOffsetPx.coerceAtLeast(0f).toInt())
        } else {
            scrollState.scrollTo(0)
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, _ ->
                    scale = (scale * zoom).coerceIn(minScale, maxScale)
                }
            }
            .verticalScroll(scrollState)
    ){
        Row(modifier = Modifier.fillMaxWidth()) {
            // Colonnes des heures à gauche
            Column(Modifier.width(56.dp)) {
                for (hour in 0..23) {
                    Box(
                        Modifier
                            .height((60 * scale).dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            "%02d:00".format(hour),
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(end = 4.dp, top = 2.dp)
                        )
                    }
                }
            }
            // Zone des événements
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(totalHeightDp)
            ) {
                val density = LocalDensity.current
                val boxWidthPx = constraints.maxWidth.toFloat()

                Canvas(Modifier.matchParentSize()) {
                    for (hour in 0..24) {
                        val y = hour * 60 * scale * density.density
                        drawLine(
                            Color.LightGray,
                            Offset(0f, y),
                            Offset(size.width, y),
                            strokeWidth = 1.dp.toPx()
                        )
                    }
                    if (date == LocalDate.now()) {
                        val redLineY = redLineOffset.toPx()
                        drawLine(
                            Color.Red,
                            Offset(0f, redLineY),
                            Offset(size.width, redLineY),
                            strokeWidth = 2.dp.toPx()
                        )
                    }
                }

                positionedEvents.forEach { positioned ->
                    val startInstant = Instant.ofEpochMilli(positioned.event.startDate?.toLong() ?: 0)
                        .atZone(ZoneId.systemDefault()).toLocalTime()
                    val endInstant = positioned.event.endDate?.let {
                        Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalTime()
                    } ?: startInstant.plusMinutes(30)

                    val startMinutes = startInstant.hour * 60 + startInstant.minute
                    val durationMinutes = (endInstant.hour * 60 + endInstant.minute - startMinutes)
                        .coerceAtLeast(15)

                    val topOffset = (startMinutes * scale).dp
                    val baseMinHeight = 30.dp
                    val minEventHeightDp = if (scale < 1f) baseMinHeight / scale else baseMinHeight
                    val calculatedEventHeight = (durationMinutes * scale).dp
                    val eventHeight = maxOf(minEventHeightDp, calculatedEventHeight)

                    val eventWidthFraction = 1f / positioned.totalColumns
                    val eventWidth = with(density) { (boxWidthPx * eventWidthFraction).toDp() }
                    val xOffset = with(density) {
                        (boxWidthPx * positioned.column * eventWidthFraction).toDp()
                    }

                    Box(
                        modifier = Modifier
                            .absoluteOffset(
                                x = xOffset,
                                y = topOffset
                            )
                            .width(eventWidth)
                            .height(eventHeight)
                            .padding(2.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clickable { selectedEvent = positioned.event }
                    ) {
                        EventCard(
                            event = positioned.event,
                            startDate = positioned.event.startDate ?: 0L,
                            endDate = positioned.event.endDate ?: 0L,
                            events = events,
                            entretiens = entretiens, // ou filtered
                            relatedEntretien = relatedEntretien?.entretien,
                            relatedContacts = relatedContacts,
                            userId = userId ?: "",
                            modifier = Modifier.fillMaxSize(),
                            compact = eventHeight < 50.dp
                        )
                    }

                }
            }
        }


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
