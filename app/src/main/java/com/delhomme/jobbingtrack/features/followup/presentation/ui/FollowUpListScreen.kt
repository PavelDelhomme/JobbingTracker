package com.delhomme.jobbingtrack.features.followup.presentation.ui

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
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpStatusEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpTypeEntity
import com.delhomme.jobbingtrack.features.followup.presentation.viewmodel.FollowUpViewModel
import com.delhomme.jobbingtrack.ui.shared.ListScreen


@Composable
fun FollowUpListScreen(
    followUps: List<FollowUpEntity>,
    followUpVm: FollowUpViewModel,
    onItemClick: (FollowUpEntity) -> Unit,
    onEdit: (FollowUpEntity) -> Unit,
    onArchive: (FollowUpEntity) -> Unit,
    onDelete: (FollowUpEntity) -> Unit,
    onAddClick: () -> Unit,
    types: List<FollowUpTypeEntity>,
    statuses: List<FollowUpStatusEntity>,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val sorted = followUps.sortedByDescending { it.date }
        val visible = sorted.filter { !it.base.isArchived }

        ListScreen(
            dateProvider = { it.date.toFormattedDate() },
            titleProvider = { fu ->
                types.find { t -> t.id == fu.typeId }?.label ?: "Type inconnu"
            },
            centerInfoProvider = { fu ->
                statuses.find { s -> s.id == fu.statusId }?.label ?: "Statut inconnu"
            },
            bottomLeftInfoProvider = { fu -> "Entreprise : ${fu.companyId}" },
            items = visible,
            onItemClick = onItemClick,
            onEdit = onEdit,
            onArchive = onArchive,
            onDelete = onDelete,
        )

        FloatingActionButton(
            onClick = onAddClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Ajouter une relance")
        }
    }
}
