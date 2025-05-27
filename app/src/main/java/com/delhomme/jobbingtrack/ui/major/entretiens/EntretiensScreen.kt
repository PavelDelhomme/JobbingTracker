package com.delhomme.jobbingtrack.ui.major.entretiens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.delhomme.jobbingtrack.data.classes.EntretienStyle
import com.delhomme.jobbingtrack.data.classes.EntretienType
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import com.delhomme.jobbingtrack.ui.components.ConfirmDialog
import com.delhomme.jobbingtrack.ui.components.lists.ListScreen
import com.delhomme.jobbingtrack.utils.DialogType
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun EntretiensScreen(
    entretiens: List<EntretienWithContacts>,
    entreprises: List<EntrepriseEntity>,
    onItemClick: (EntretienWithContacts) -> Unit,
    onEdit: (EntretienWithContacts) -> Unit,
    onArchive: (EntretienWithContacts) -> Unit,
    onDelete: (EntretienWithContacts) -> Unit
) {
    var dialogState by remember { mutableStateOf<EntretienWithContacts?>(null) }
    var dialogType by remember { mutableStateOf<DialogType?>(null) }

    val entById = remember(entreprises) { entreprises.associateBy { it.id } }

    Box(modifier = Modifier.fillMaxSize()) {
        val sortedEntretiens = entretiens.sortedByDescending { it.entretien.dateTime }
        val visibleEntretiens = sortedEntretiens.filter { !it.entretien.isArchived }

        ListScreen(
            dateProvider = { it.entretien.dateTime.toFormattedDate() },
            titleProvider = { (it.entretien.type ?: EntretienType.UNKNOWN).toString() },
            centerInfoProvider = { (it.entretien.style ?: EntretienStyle.UNDECIDED).toString() },
            bottomLeftInfoProvider = {
                entById[it.entretien.companyId]?.name ?: "Entreprise inconnue"
            },
            items = visibleEntretiens,
            onItemClick = onItemClick,
            onEdit = { onEdit(it) },
            onArchive = {
                dialogType = DialogType.ARCHIVE
                dialogState = it
            },
            onDelete = {
                dialogType = DialogType.DELETE
                dialogState = it
            }
        )

        dialogState?.let { entretien ->
            ConfirmDialog(
                title = if (dialogType == DialogType.ARCHIVE) "Archiver ?" else "Supprimer ?",
                text = if (dialogType == DialogType.ARCHIVE)
                    "Voulez-vous vraiment archiver cet entretien ?"
                else
                    "Voulez-vous vraiment supprimer cet entretien ?",
                onConfirm = {
                    if (dialogType == DialogType.ARCHIVE) onArchive(entretien)
                    else onDelete(entretien)
                },
                onDismiss = {
                    dialogState = null
                    dialogType = null
                }
            )
        }
    }
}