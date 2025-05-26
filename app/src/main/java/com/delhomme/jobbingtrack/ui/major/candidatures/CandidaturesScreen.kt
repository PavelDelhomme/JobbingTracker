package com.delhomme.jobbingtrack.ui.major.candidatures

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

import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import com.delhomme.jobbingtrack.ui.components.ConfirmDialog
import com.delhomme.jobbingtrack.ui.components.lists.ListScreen
import com.delhomme.jobbingtrack.utils.DialogType
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun CandidaturesScreen(
    navController: NavController,
    candidatures: List<CandidatureEntity>,
    entreprises: List<EntrepriseEntity>,
    onItemClick: (CandidatureEntity) -> Unit,
    onEdit: (CandidatureEntity) -> Unit,
    onArchive: (CandidatureEntity) -> Unit,
    onDelete: (CandidatureEntity) -> Unit,
    onAddClick: () -> Unit
) {
    var dialogState by remember { mutableStateOf<CandidatureEntity?>(null) }
    var dialogType by remember { mutableStateOf<DialogType?>(null) }

    val entById = remember(entreprises) { entreprises.associateBy { it.id } }

    Box(modifier = Modifier.fillMaxSize()) {
        val sortedCandidatures = candidatures.sortedByDescending { it.applicationDate }
        val visibleCandidature = sortedCandidatures.filter { !it.isArchived }

        ListScreen(
            dateProvider = { it.applicationDate.toFormattedDate() },
            titleProvider = { it.title },
            centerInfoProvider = { it.applicationStatus },
            bottomLeftInfoProvider = { entById[it.companyId]?.name ?: "Entreprise inconnue" },
            items = candidatures.filter { !it.isArchived },
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

