package com.delhomme.jobbingtrack.followsup.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.commons.ui.lists.ListScreen
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpStatusEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpTypeEntity
import com.delhomme.jobbingtrack.datas.viewmodels.FollowUpViewModel

import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun FollowUpsScreen(
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
