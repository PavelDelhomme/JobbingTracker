package com.delhomme.jobbingtrack.ui.major.entretiens

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.data.classes.Entretien
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import com.delhomme.jobbingtrack.ui.components.ListScreen
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun EntretiensScreen(
    entretiens: List<EntretienWithContacts>,
    onItemClick: (Entretien) -> Unit,
) {
    val sortedEntretiens = entretiens.sortedByDescending { it.dateTime }
    val visibleEntretiens = sortedEntretiens.filter { !it.isArchived }

    ListScreen(
        dateProvider = { it.dateTime.toFormattedDate() },
        titleProvider = { it.type?.name ?: "Type inconnu" },
        centerInfoProvider = { it.style?.name ?: "Style inconnu" },
        bottomLeftInfoProvider = { "Entreprise ID: ${it.companyId}" },
        items = visibleEntretiens,
        onItemClick = onItemClick
    )
}
