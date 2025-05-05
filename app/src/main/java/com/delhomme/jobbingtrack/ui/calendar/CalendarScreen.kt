package com.delhomme.jobbingtrack.ui.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import java.time.Instant
import java.time.ZoneId

@Composable
fun CalendarScreen(navController: NavController) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "📅 ${currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentMonth.year}",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))


        // Grille des jours
        MonthGrid(
            month = currentMonth,
            onDayClick = { date ->
                // on pourrait ici naviguer vers une vue détail par jour
                println("Date sélectionnée : $date")
            }
        )


        Spacer(modifier = Modifier.height(16.dp))

        Text("📌 Événements du mois", style = MaterialTheme.typography.titleMedium)

        // Afficher les événements filtrés du mois
        MonthlyEventsList(month = currentMonth)
    }
}


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
