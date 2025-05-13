package com.delhomme.jobbingtrack.ui.major.calendar.weeks

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.delhomme.jobbingtrack.data.classes.Evenement
import java.time.LocalDate

@Composable
fun TwoWeekPagerView(
    startDate: LocalDate,
    events: List<Evenement>,
    modifier: Modifier = Modifier
) {
    // Placeholder temporaire
    Text("Vue sur deux semaines à faire à partir du $startDate")
}
