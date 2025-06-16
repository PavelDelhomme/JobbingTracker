package com.delhomme.jobbingtrack.datas.entities.dashboards

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart as MPLineChart
import java.time.LocalDate

@Composable
fun LineChart(
    data: List<Pair<LocalDate, Int>>,
    label: String
) {
    val context = LocalContext.current
    AndroidView<MPLineChart>(
        factory = { ctx: Context ->
            MPLineChart(ctx).apply {
                // on désactive la description par défaut
                this.description.isEnabled = false
                // TODO : buildez votre LineData ici et faites `data = yourLineData`
            }
        },
        update = { chart ->
            // si vous voulez mettre à jour le graphique quand `data` change,
            // faites-le ici : chart.data = … ; chart.invalidate()
        }
    )
}


class ChartCard {
}



class StatCard {
}