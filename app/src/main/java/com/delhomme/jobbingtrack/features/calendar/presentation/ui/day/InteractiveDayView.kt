package com.delhomme.jobbingtrack.features.calendar.presentation.ui.day

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.find
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.core.utils.toDomain
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import com.delhomme.jobbingtrack.features.calendar.domain.model.Event
import com.delhomme.jobbingtrack.features.calendar.presentation.ui.event.EventCard
import com.delhomme.jobbingtrack.features.calendar.utils.computeOverlappingEvents
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewWithContacts
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
    interviews: List<InterviewWithContacts> = emptyList(),
    contacts: List<ContactEntity> = emptyList(),
    calls: List<CallEntity> = emptyList(),
    followsUp: List<FollowUpEntity> = emptyList(),
    applications: List<ApplicationEntity> = emptyList(),
    modifier: Modifier = Modifier
)
{
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
                Instant.ofEpochMilli(it.startDate ?: 0L).atZone(ZoneId.systemDefault()).toLocalDate() == date
            }
        )
    }

    var selectedEvent by remember { mutableStateOf<Event?>(null) }

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
                        val relatedInterview = positioned.event.relatedObjectId?.let { id ->
                            interviews.find { it.interview.id == id }
                        }
                        val relatedContacts = relatedInterview?.contacts ?: emptyList()

                        EventCard(
                            event = positioned.event,
                            events = events,
                            userId = userId,
                            interviews = interviews,
                            relatedInterview = relatedInterview,
                            relatedContacts = relatedContacts,
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
