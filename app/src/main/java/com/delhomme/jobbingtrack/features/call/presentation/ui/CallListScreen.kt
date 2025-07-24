package com.delhomme.jobbingtrack.features.call.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.core.utils.toFormattedDate
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.call.presentation.viewmodel.CallViewModel
import com.delhomme.jobbingtrack.ui.shared.ListScreen


@Composable
fun CallListScreen(
    calls: List<CallEntity>,
    callsVm: CallViewModel,
    onItemClick: (CallEntity) -> Unit,
    onEdit: (CallEntity) -> Unit,
    onArchive: (CallEntity) -> Unit,
    onDelete: (CallEntity) -> Unit,
    onAddClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        val sorted = calls.sortedByDescending { it.timestamp }
        val visible = sorted.filter { !it.base.isArchived }

        ListScreen(
            dateProvider = { it.timestamp.toFormattedDate() },
            titleProvider = { it.subject },
            centerInfoProvider = { it.contactId ?: it.companyId },
            bottomLeftInfoProvider = { "Entreprise : ${it.companyId}" },
            items = visible,
            onItemClick = onItemClick,
            onEdit = onEdit,
            onArchive = onArchive,
            onDelete = onDelete
        )

        FloatingActionButton(
            onClick = onAddClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Ajouter un appel")
        }
    }
}
