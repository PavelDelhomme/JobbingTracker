package com.delhomme.jobbingtrack.ui.events.day


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.delhomme.jobbingtrack.datas.entities.applications.ApplicationEntity
import com.delhomme.jobbingtrack.datas.entities.calls.CallEntity
import com.delhomme.jobbingtrack.datas.entities.contacts.ContactEntity
import com.delhomme.jobbingtrack.datas.entities.events.EventEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewWithContacts
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Composable
fun DailyPagerView(
    initialDate: LocalDate,
    events: List<EventEntity>,
    onDateChange: (LocalDate) -> Unit,
    userId: String,
    interviews: List<InterviewWithContacts> = emptyList(),
    contacts: List<ContactEntity> = emptyList(),
    applications: List<ApplicationEntity> = emptyList(),
    calls: List<CallEntity> = emptyList(),
    followsUp: List<FollowUpEntity> = emptyList(),
) {
    val pagerState = rememberPagerState(initialPage = 1000, pageCount = 3) // Centre fictif
    val currentDate by remember {
        derivedStateOf { initialDate.plusDays((pagerState.currentPage - 1000).toLong()) }
    }

    LaunchedEffect(currentDate) {
        onDateChange(currentDate)
    }

    HorizontalPager(
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
            interviews = interviews,
            contacts = contacts,
            applications = applications,
            calls = calls,
            followsUp = followsUp,
        )
    }
}