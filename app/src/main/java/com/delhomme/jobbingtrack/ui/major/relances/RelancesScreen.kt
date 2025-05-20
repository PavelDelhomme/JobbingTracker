package com.delhomme.jobbingtrack.ui.major.relances

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.data.classes.Relance
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity
import com.delhomme.jobbingtrack.ui.components.ListScreen
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun RelancesScreen(
    relances: List<RelanceEntity>,
    onItemClick: (Relance) -> Unit,
) {
    val sortedRelances = relances.sortedByDescending { it.date }
    val visibleRelances = sortedRelances.filter { !it.isArchived }

    ListScreen(
        dateProvider = { it.date.toFormattedDate() },
        titleProvider = { it.type?.name ?: "Type inconnu" },
        centerInfoProvider = { it.responseStatus?.name ?: "Statut inconnu" },
        bottomLeftInfoProvider = { "Entreprise ID: ${it.companyId}" },
        items = visibleRelances,
        onItemClick = onItemClick
    )
}
