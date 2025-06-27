package com.delhomme.jobbingtrack.features.calendar.presentation.ui.planning

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.events.ui.EventPlanningCard
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@Composable
fun PlanningView(
    date: LocalDate,
    events: List<EventEntity>,
    modifier: Modifier = Modifier,
) {
    val groupedEvents = remember(events) {
        events.groupBy {
            Instant.ofEpochMilli(it.startDate!!.toLong()).atZone(ZoneId.systemDefault()).toLocalDate()
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