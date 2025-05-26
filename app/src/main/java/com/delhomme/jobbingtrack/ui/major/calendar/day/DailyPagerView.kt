package com.delhomme.jobbingtrack.ui.major.calendar.day

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import com.delhomme.jobbingtrack.data.local.entities.EventEntity
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity
import com.google.accompanist.pager.*
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DailyPagerView(
    initialDate: LocalDate,
    events: List<EventEntity>,
    onDateChange: (LocalDate) -> Unit,
    userId: String,
    entretiens: List<EntretienWithContacts> = emptyList(),
    contacts: List<ContactEntity> = emptyList(),
    candidatures: List<CandidatureEntity> = emptyList(),
    appels: List<AppelEntity> = emptyList(),
    relances: List<RelanceEntity> = emptyList(),
) {
    val pagerState = rememberPagerState(initialPage = 1000) // Centre fictif
    val currentDate by remember {
        derivedStateOf { initialDate.plusDays((pagerState.currentPage - 1000).toLong()) }
    }

    LaunchedEffect(currentDate) {
        onDateChange(currentDate)
    }

    HorizontalPager(
        count = Int.MAX_VALUE,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        val pageDate = initialDate.plusDays((page - 1000).toLong())

        InteractiveDayView(
            date = pageDate,
            eventEntities = events.filter {
                Instant.ofEpochMilli(it.startDate?.toLong() ?: 0)
                    .atZone(ZoneId.systemDefault()).toLocalDate() == pageDate
            },
            userId = userId,
            entretiens = entretiens,
            contacts = contacts,
            candidatures = candidatures,
            appels = appels,
            relances = relances,
        )
    }
}
