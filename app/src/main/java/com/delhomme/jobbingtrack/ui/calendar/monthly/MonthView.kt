package com.delhomme.jobbingtrack.ui.calendar.monthly

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import java.time.Instant
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import kotlin.collections.forEach


@Composable
fun MonthGrid(month: YearMonth, onDayClick: (LocalDate) -> Unit) {
    val firstDay = month.atDay(1)
    val daysInMonth = month.lengthOfMonth()
    val startDayOfWeek = firstDay.dayOfWeek.value % 7 // Dimanche = 0

    val weeks = mutableListOf<List<LocalDate?>>()
    var week = MutableList(7) { index -> if (index < startDayOfWeek) null else firstDay.plusDays((index - startDayOfWeek).toLong()) }

    var dayCounter = 7 - startDayOfWeek + 1
    while (dayCounter <= daysInMonth) {
        week = MutableList(7) { i ->
            val day = dayCounter + i
            if (day > daysInMonth) null else month.atDay(day)
        }
        weeks.add(week)
        dayCounter += 7
    }

    Column {
        weeks.forEach { weekDays ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                weekDays.forEach { day ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(2.dp)
                            .clickable(enabled = day != null) {
                                if (day != null) onDayClick(day)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = day?.dayOfMonth?.toString() ?: "")
                    }
                }
            }
        }
    }
}


@Composable
fun MonthlyEventsList(month: YearMonth) {
    val events = FakeDataProvider.evenements
        .filter { event ->
            val eventDate = Instant.ofEpochMilli(event.startDate).atZone(ZoneId.systemDefault()).toLocalDate()
            eventDate.month == month.month && eventDate.year == month.year
        }
        .sortedBy { it.startDate }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(events) { event ->
            Card(modifier = Modifier.fillMaxWidth()) {
                ListItem(
                    headlineContent = {
                        Text("${event.title} – ${Instant.ofEpochMilli(event.startDate).atZone(ZoneId.systemDefault()).toLocalDate()}")
                    },
                    supportingContent = {
                        Text("Détails : ${event.description ?: "Sans description"}")
                    }
                )
            }
        }
    }
}
