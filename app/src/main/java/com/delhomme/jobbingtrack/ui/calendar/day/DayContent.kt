package com.delhomme.jobbingtrack.ui.calendar.day

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.classes.Evenement
import com.kizitonwose.calendar.core.CalendarDay

@Composable
fun DayContent(
    day: CalendarDay,
    eventsForDay: List<Evenement>,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.3f) else Color.Transparent

    Column(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clickable { onClick() }
            .background(bgColor, shape = MaterialTheme.shapes.small)
            .padding(4.dp)
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(Modifier.height(2.dp))
        eventsForDay.take(3).forEach {
            Text(
                text = it.title,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (eventsForDay.size > 3) {
            Text("...", style = MaterialTheme.typography.bodySmall)
        }
    }
}
