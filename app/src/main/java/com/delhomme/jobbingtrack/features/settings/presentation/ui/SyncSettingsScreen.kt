package com.delhomme.jobbingtrack.features.settings.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.delhomme.jobbingtrack.services.sync.SyncManager
import com.delhomme.jobbingtrack.services.sync.SyncWorker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SyncSettingsScreen(syncManager: SyncManager) {
    val context = LocalContext.current
    var syncInterval by remember { mutableStateOf(syncManager.getSyncInterval().toFloat()) }
    val lastSyncTime = remember { syncManager.getLastSyncTimestamp() }
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Paramètres de synchronisation", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Text("Dernière synchronisation: ${if (lastSyncTime > 0) dateFormat.format(Date(lastSyncTime)) else "Jamais"}")

        Spacer(modifier = Modifier.height(24.dp))

        Text("Intervalle de synchronisation: ${syncInterval.toInt()} heures")

        Slider(
            value = syncInterval,
            onValueChange = { syncInterval = it },
            valueRange = 1f..24f,
            steps = 23,
            onValueChangeFinished = {
                syncManager.setSyncInterval(syncInterval.toLong())
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
        Divider()
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                startManualSync(context)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Synchroniser maintenant")
        }
    }
}

fun startManualSync(context: android.content.Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val syncWorkRequest = OneTimeWorkRequestBuilder<SyncWorker>()
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance(context).enqueue(syncWorkRequest)
}