package com.delhomme.jobbingtrack.features.calendar.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarTopBar(
    selectedDate: LocalDate,
    onGoToToday: () -> Unit
) {
    val dayOfWeek = selectedDate.dayOfWeek.getDisplayName(java.time.format.TextStyle.SHORT, Locale.getDefault())
    val month = selectedDate.month.getDisplayName(java.time.format.TextStyle.FULL, Locale.getDefault()).replaceFirstChar { it.uppercase() }
    val day = selectedDate.dayOfMonth

    TopAppBar(
        title = {
            Text("$dayOfWeek $day $month")
        },
        actions = {
            IconButton(onClick = { onGoToToday() }) {
                Icon(
                    imageVector = Icons.Default.Today,
                    contentDescription = "Aller à aujourd'hui")
            }
        }
    )
}
