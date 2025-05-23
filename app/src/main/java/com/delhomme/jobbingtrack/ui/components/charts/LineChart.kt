package com.delhomme.jobbingtrack.ui.components.charts

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import java.time.LocalDate

@Composable
fun LineChart(
    data: List<Pair<LocalDate, Int>>,
    label: String
) {
    AndroidView(
        factory = { ctx ->
            com.github.mikephil.charting.charts.LineChart(ctx).apply {
                description.isEnabled = false
                // ici tu pourras initialiser ton LineData / LineDataSet
            }
        }
    )
}