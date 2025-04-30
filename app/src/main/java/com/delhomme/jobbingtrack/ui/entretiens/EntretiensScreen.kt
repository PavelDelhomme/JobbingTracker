package com.delhomme.jobbingtrack.ui.entretiens

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.data.classes.Entretien
import com.delhomme.jobbingtrack.ui.components.ListScreen
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun EntretiensScreen(
    entretiens: List<Entretien>,
    onItemClick: (Entretien) -> Unit,
) {
    val sortedEntretiens = entretiens.sortedByDescending { it.dateTime }
    val visibleEntretiens = sortedEntretiens.filter { !it.isArchived }

    ListScreen(
        dateProvider = { it.dateTime.toFormattedDate() },
        titleProvider = { it.type.name },
        centerInfoProvider = { it.style.name },
        bottomLeftInfoProvider = { "Entreprise ID: ${it.companyId}" },
        items = visibleEntretiens,
        onItemClick = onItemClick
    )
}
