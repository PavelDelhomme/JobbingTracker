package com.delhomme.jobbingtrack.ui.appels

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.data.classes.Appel
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.ui.components.ListScreen
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun AppelsScreen(
    appels: List<Appel>,
    onItemClick: (Appel) -> Unit,
    onAddClick: (Contact) -> Unit
) {
    val sortedAppels = appels.sortedByDescending { it.dateTime }
    val visibleAppels = sortedAppels.filter { !it.isArchived }

    ListScreen(
        dateProvider = { it.dateTime.toFormattedDate() },
        titleProvider = { it.subject },
        centerInfoProvider = { null },
        bottomLeftInfoProvider = { "Entreprise ID: ${it.companyId}" },
        items = visibleAppels,
        onItemClick = onItemClick
    )
}
