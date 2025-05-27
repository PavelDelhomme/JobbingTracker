package com.delhomme.jobbingtrack.ui.major.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.data.local.entities.ProfileEntity
import com.delhomme.jobbingtrack.data.viewmodel.ProfileViewModel
import com.delhomme.jobbingtrack.ui.components.ConfirmDialog
import com.delhomme.jobbingtrack.utils.DialogType
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun ProfileScreen(
    profileId: String? = null,
    viewModel: ProfileViewModel = viewModel()
) {
    val profiles by viewModel.allProfiles.observeAsState(emptyList())
    val profile = profiles.firstOrNull { it.id == profileId }

    var dialogType by remember { mutableStateOf<DialogType?>(null) }
    var dialogVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        profile?.let {
            Text("ID : ${it.id}", style = MaterialTheme.typography.titleMedium)
            Text("Sujet : ${it.subject}", style = MaterialTheme.typography.bodyLarge)
            Text("Date : ${it.inscriptionDateTime.toFormattedDate()}", style = MaterialTheme.typography.bodyMedium)
            Text("Notes : ${it.notes ?: "—"}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = {
                        dialogType = DialogType.ARCHIVE
                        dialogVisible = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(Icons.Default.DownloadDone, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Archiver")
                }

                Button(
                    onClick = {
                        dialogType = DialogType.DELETE
                        dialogVisible = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Supprimer")
                }
            }
        } ?: Text("Aucun profil sélectionné", style = MaterialTheme.typography.bodyMedium)
    }

    if (dialogVisible && profile != null) {
        ConfirmDialog(
            title = if (dialogType == DialogType.ARCHIVE) "Archiver le profil ?" else "Supprimer définitivement ?",
            text = if (dialogType == DialogType.ARCHIVE)
                "Voulez-vous vraiment archiver ce profil ?"
            else
                "Voulez-vous vraiment supprimer ce profil de façon définitive ?",
            onConfirm = {
                if (dialogType == DialogType.ARCHIVE) {
                    viewModel.archive(profile.id)
                } else {
                    viewModel.delete(profile.id)
                }
                dialogVisible = false
            },
            onDismiss = {
                dialogVisible = false
            }
        )
    }
}
