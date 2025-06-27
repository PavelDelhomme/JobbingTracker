package com.delhomme.jobbingtrack.features.profil.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.core.network.tokens.TokenManager
import com.delhomme.jobbingtrack.core.utils.DialogType
import com.delhomme.jobbingtrack.features.cvs.presentation.viewmodels.ExperienceViewModel
import com.delhomme.jobbingtrack.features.profil.presentation.viewmodel.ProfilViewModel
import com.delhomme.jobbingtrack.ui.components.ConfirmDialog


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfilScreen(
    profileId: String? = null,
    viewModel: ProfilViewModel = viewModel()
) {
    val context = LocalContext.current
    val userId = TokenManager.getUserId(context) ?: ""

    val experienceVm: ExperienceViewModel = viewModel()
    val experiences by experienceVm.getAllForUser(userId).observeAsState(emptyList())

    experiences.forEach {
        Text("- ${it.title} chez ${it.company}")
    }
    val profile by viewModel.profileForUser(userId).observeAsState()

    var subject by remember { mutableStateOf(profile?.subject ?: "") }
    var dialogType by remember { mutableStateOf<DialogType?>(null) }
    var dialogVisible by remember { mutableStateOf(false) }

    var skills by remember { mutableStateOf(listOf("Kotlin")) }
    var newSkill by remember { mutableStateOf("") }


    Row {
        OutlinedTextField(
            value = newSkill,
            onValueChange = { newSkill = it },
            label = { Text("Ajouter une compétence") }
        )
        Button(onClick = {
            if (newSkill.isNotBlank()) {
                skills = skills + newSkill
                newSkill = ""
            }
        }) {
            Text("Ajouter")
        }
    }
    FlowRow {
        skills.forEach { skill ->
            AssistChip(onClick = {}, label = { Text(skill) })
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        profile?.let {
            Text("ID : ${it.id}", style = MaterialTheme.typography.titleMedium)

            OutlinedTextField(
                value = subject,
                onValueChange = { subject = it },
                label = { Text("Sujet") },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Date : ${it.createdAt.toFormattedDate()}", style = MaterialTheme.typography.bodyMedium)
            Text("Notes : ${it.notes ?: "—"}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = {
                    if (profile != null) {
                        val updated = profile!!.copy(subject = subject, updatedAt = System.currentTimeMillis())
                        viewModel.update(updated)
                    }
                }) {
                    Text("Enregistrer les modifications")
                }

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
                    viewModel.archive(profile!!.id)
                } else {
                    viewModel.delete(profile!!.id)
                }
                dialogVisible = false
            },
            onDismiss = {
                dialogVisible = false
            }
        )
    }
}
