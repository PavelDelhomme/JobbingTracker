package com.delhomme.jobbingtrack.ui.major.entreprises

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.data.classes.Entreprise
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import com.delhomme.jobbingtrack.ui.components.ListScreen

@Composable
fun EntreprisesScreen(
    entreprises: List<EntrepriseEntity>,
    onItemClick: (Entreprise) -> Unit,
    onAddClick: (Contact) -> Unit
) {
    val sortedEntreprises = entreprises.sortedBy { it.name.lowercase() }
    val visibleEntreprises = sortedEntreprises.filter { !it.isArchived }

    ListScreen(
        dateProvider = { null },
        titleProvider = { it.name },
        centerInfoProvider = { it.type ?: "" },
        bottomLeftInfoProvider = { it.phone ?: it.email ?: "" },
        items = visibleEntreprises,
        onItemClick = onItemClick
    )
}
