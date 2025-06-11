package com.delhomme.jobbingtrack.interviews.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.delhomme.jobbingtrack.commons.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.commons.ui.dialogs.ConfirmDialog
import com.delhomme.jobbingtrack.commons.ui.lists.ListScreen
import com.delhomme.jobbingtrack.companies.entities.CompanyEntity
import com.delhomme.jobbingtrack.interviews.enumes.InterviewStyle
import com.delhomme.jobbingtrack.interviews.enumes.InterviewType
import com.delhomme.jobbingtrack.utils.DialogType
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun InterviewsScreen(
    interviews: List<InterviewWithContacts>,
    companies: List<CompanyEntity>,
    onItemClick: (InterviewWithContacts) -> Unit,
    onEdit: (InterviewWithContacts) -> Unit,
    onArchive: (InterviewWithContacts) -> Unit,
    onDelete: (InterviewWithContacts) -> Unit
) {
    var dialogState by remember { mutableStateOf<InterviewWithContacts?>(null) }
    var dialogType by remember { mutableStateOf<DialogType?>(null) }

    val companyById = remember(companies) { companies.associateBy { it.id } }

    Box(modifier = Modifier.fillMaxSize()) {
        val sortedInterviews = interviews.sortedByDescending { it.interview.dateTime }
        val visibleInterviews = sortedInterviews.filter { !it.interview.base.isArchived }

        ListScreen(
            dateProvider = { it.interview.dateTime.toFormattedDate() },
            titleProvider = { (it.interview.type ?: InterviewType.UNKNOWN).toString() },
            centerInfoProvider = { (it.interview.style ?: InterviewStyle.UNDECIDED).toString() },
            bottomLeftInfoProvider = {
                companyById[it.interview.companyId]?.name ?: "Entreprise inconnue"
            },
            items = visibleInterviews,
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