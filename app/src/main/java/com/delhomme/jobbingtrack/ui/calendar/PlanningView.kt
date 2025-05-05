package com.delhomme.jobbingtrack.ui.calendar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Composable
fun PlanningView(
    date: LocalDate,
    events: List<Evenement>
) {
    val hours = (0..23).toList()
    val eventsByHour = remember(events) {
        events.groupBy { event ->
            Instant.ofEpochMilli(event.startDate)
                .atZone(ZoneId.systemDefault())
                .hour
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = hours) { hour ->
            val hourEvents = eventsByHour[hour] ?: emptyList()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = String.format("%02d:00", hour),
                    style = MaterialTheme.typography.bodyMedium
                )
                hourEvents.forEach { event ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = event.title, style = MaterialTheme.typography.titleSmall)
                            event.description?.let {
                                Text(text = it, style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
        }
    }
}
