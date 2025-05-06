package com.delhomme.jobbingtrack.ui.calendar.day

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.delhomme.jobbingtrack.data.classes.Evenement
import com.google.accompanist.pager.*
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DailyPagerView(
    initialDate: LocalDate,
    events: List<Evenement>,
    onDateChange: (LocalDate) -> Unit
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
            events = events.filter {
                Instant.ofEpochMilli(it.startDate).atZone(ZoneId.systemDefault()).toLocalDate() == pageDate
            }
        )
    }
}
