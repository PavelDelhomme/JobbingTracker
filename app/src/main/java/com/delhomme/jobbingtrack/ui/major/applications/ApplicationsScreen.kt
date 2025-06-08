package com.delhomme.jobbingtrack.ui.major.applications

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.delhomme.jobbingtrack.data.local.entities.application.ApplicationEntity
import com.delhomme.jobbingtrack.data.local.entities.company.CompanyEntity
import com.delhomme.jobbingtrack.ui.components.ConfirmDialog
import com.delhomme.jobbingtrack.ui.components.lists.ListScreen
import com.delhomme.jobbingtrack.utils.DialogType
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun ApplicationsScreen(
    navController: NavController,
    applications: List<ApplicationEntity>,
    companies: List<CompanyEntity>,
    onItemClick: (ApplicationEntity) -> Unit,
    onEdit: (ApplicationEntity) -> Unit,
    onArchive: (ApplicationEntity) -> Unit,
    onDelete: (ApplicationEntity) -> Unit,
    onAddClick: () -> Unit
) {
    var dialogState by remember { mutableStateOf<ApplicationEntity?>(null) }
    var dialogType by remember { mutableStateOf<DialogType?>(null) }

    val entById = remember(companies) { companies.associateBy { it.id } }

    Box(modifier = Modifier.fillMaxSize()) {
        val sortedCandidatures = applications.sortedByDescending { it.applicationDate }
        val visibleCandidature = sortedCandidatures.filter { !it.isArchived }

        ListScreen(
            dateProvider = { it.applicationDate.toFormattedDate() },
            titleProvider = { it.title },
            centerInfoProvider = { it.applicationStatus },
            bottomLeftInfoProvider = { entById[it.companyId]?.name ?: "Entreprise inconnue" },
            items = applications.filter { !it.isArchived },
            onItemClick = onItemClick,
            onEdit =  { onEdit(it) },
            onArchive = {
                dialogType = DialogType.ARCHIVE
                dialogState = it
            },
            onDelete = {
                dialogType = DialogType.DELETE
                dialogState = it
            }
        )
        dialogState?.let { cand ->
            ConfirmDialog(
                title = if (dialogType == DialogType.ARCHIVE) "Archiver ?" else "Supprimer ?",
                text = if (dialogType == DialogType.ARCHIVE)
                            "Voulez-vous vraiment archiver « ${cand.title} » ?"
                        else
                            "Voulez-vous vraiment supprimer « ${cand.title} » ?",
                onConfirm = {
                    if (dialogType == DialogType.ARCHIVE) onArchive(cand) else onDelete(cand)
                },
                onDismiss = {
                    dialogState = null
                    dialogType = null
                }
            )
        }

        FloatingActionButton(
            onClick = { onAddClick() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Ajouter Candidature")
        }

    }
}

