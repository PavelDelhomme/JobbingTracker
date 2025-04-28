package com.delhomme.jobbingtrack.ui.relances

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.data.classes.Relance
import com.delhomme.jobbingtrack.ui.components.ListScreen
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun RelancesScreen(
    relances: List<Relance>,
    onItemClick: (Relance) -> Unit,
) {
    val sortedRelances = relances.sortedByDescending { it.date }
    ListScreen(
        dateProvider = { it.date.toFormattedDate() },
        titleProvider = { it.type.name },
        centerInfoProvider = { it.responseStatus.name },
        bottomLeftInfoProvider = { "Entreprise ID: ${it.companyId}" },
        items = sortedRelances,
        onItemClick = onItemClick
    )
}
