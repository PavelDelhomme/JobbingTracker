package com.delhomme.jobbingtrack.ui.major.entretiens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import com.delhomme.jobbingtrack.data.viewmodel.EntretienViewModel
import com.delhomme.jobbingtrack.ui.components.lists.ListScreen
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun EntretiensScreen(
    entretiens: List<EntretienWithContacts>,
    entretiensVm: EntretienViewModel,
    onEdit: (ContactEntity) -> Unit,
    onArchive: (ContactEntity) -> Unit,
    onDelete: (ContactEntity) -> Unit,
    onItemClick: (EntretienWithContacts) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // note : EntretienWithContacts.entretien.dateTime
        val sorted = entretiens.sortedByDescending { it.entretien.dateTime }
        val visible = sorted.filter { !it.entretien.isArchived }

        ListScreen(
            dateProvider           = { it.entretien.dateTime.toFormattedDate() },
            titleProvider          = { it.entretien.type ?: "Type inconnu" },
            centerInfoProvider     = { it.entretien.style ?: "Style inconnu" },
            bottomLeftInfoProvider = { "Entreprise : ${it.entretien.companyId}" },
            items                  = visible,
            onItemClick            = onItemClick
        )
    }
}
