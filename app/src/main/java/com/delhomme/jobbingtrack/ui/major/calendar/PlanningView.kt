package com.delhomme.jobbingtrack.ui.major.calendar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.classes.Evenement
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import com.delhomme.jobbingtrack.ui.major.calendar.event.getEventColor
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun PlanningView(
    date: LocalDate,
    events: List<Evenement>,
    modifier: Modifier = Modifier,
) {
    val groupedEvents = remember(events) {
        events.groupBy {
            Instant.ofEpochMilli(it.startDate).atZone(ZoneId.systemDefault()).toLocalDate()
        }.toSortedMap()
    }

    val today = LocalDate.now()
    val listState = rememberLazyListState()

    // Scroll automatique amélioré vers aujourd'hui ou la prochaine date disponible
    LaunchedEffect(groupedEvents) {
        val todayIndex = groupedEvents.keys.indexOf(today)
        if (todayIndex >= 0) {
            listState.scrollToItem(todayIndex, scrollOffset = 0)
        } else {
            val nextAvailableIndex = groupedEvents.keys.indexOfFirst { it.isAfter(today) }
            if (nextAvailableIndex >= 0) {
                listState.scrollToItem(nextAvailableIndex, scrollOffset = 0)
            } else {
                listState.scrollToItem(0, scrollOffset = 0)
            }
        }
    }


    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            groupedEvents.forEach { (day, dayEvents) ->
                item {
                    Text(
                        text = day.format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy")),
                        style = if (day == today)
                            MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
                        else
                            MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
                    )
                }
                items(dayEvents.sortedBy { it.startDate }) { event ->
                    EventPlanningCard(event)
                }
            }
        }

        // Bouton flottant pour revenir à aujourd'hui
        val showButton by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex != groupedEvents.keys.indexOf(today)
            }
        }

        if (showButton) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        val todayIndex = groupedEvents.keys.indexOf(today)
                        if (todayIndex >= 0) {
                            listState.animateScrollToItem(todayIndex, scrollOffset = 0)
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text("Aujourd'hui")
            }
        }
    }

}


@Composable
fun EventPlanningCard(event: Evenement) {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val zone = ZoneId.systemDefault()

    val start = Instant.ofEpochMilli(event.startDate).atZone(zone).format(formatter)
    val end = event.endDate?.let {
        Instant.ofEpochMilli(it).atZone(zone).format(formatter)
    } ?: "??:??"

    val cardColor = getEventColor(event.type)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(Modifier.padding(10.dp)) {
            Text("${event.type}: ${event.title}", style = MaterialTheme.typography.titleSmall)
            Text("$start - $end", style = MaterialTheme.typography.bodySmall)

            event.description?.let {
                Spacer(Modifier.height(4.dp))
                Text(it, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}