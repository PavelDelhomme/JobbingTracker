package com.delhomme.jobbingtrack.ui.major.entretiens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.delhomme.jobbingtrack.data.classes.Entretien
import com.delhomme.jobbingtrack.data.classes.EntretienStyle
import com.delhomme.jobbingtrack.data.classes.EntretienType

import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import com.delhomme.jobbingtrack.data.viewmodel.EntretienViewModel
import com.delhomme.jobbingtrack.ui.components.lists.ListScreen
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun EntretiensScreen(
    entretiens: List<Entretien>,
    entretiensVm: EntretienViewModel,
    onEdit: (EntretienWithContacts) -> Unit,
    onArchive: (EntretienWithContacts) -> Unit,
    onDelete: (EntretienWithContacts) -> Unit,
    onItemClick: (EntretienWithContacts) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // note : EntretienWithContacts.entretien.dateTime
        val sorted = entretiens.sortedByDescending { it.dateTime }
        val visible = sorted.filter { !it.isArchived }

        ListScreen(
            dateProvider = { it.dateTime.toFormattedDate() },
            titleProvider = { it.type ?: EntretienType.UNKNOWN },
            centerInfoProvider = { it.style ?: EntretienStyle.UNDECIDED },
            bottomLeftInfoProvider = { "Entreprise : ${it.companyId}" },
            items = visible,
            onItemClick = onItemClick
        )
    }
}
