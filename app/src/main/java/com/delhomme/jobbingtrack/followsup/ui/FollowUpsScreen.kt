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
import com.delhomme.jobbingtrack.followsup.entities.FollowUpEntity
import com.delhomme.jobbingtrack.followsup.viewmodels.FollowUpViewModel

import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun FollowUpsScreen(
    followUps: List<FollowUpEntity>,
    followUpVm: FollowUpViewModel,
    onItemClick: (FollowUpEntity) -> Unit,
    onEdit: (FollowUpEntity) -> Unit,
    onArchive: (FollowUpEntity) -> Unit,
    onDelete: (FollowUpEntity) -> Unit,
    onAddClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val sorted = followUps.sortedByDescending { it.date }
        val visible = sorted.filter { !it.base.isArchived }

        ListScreen(
            dateProvider = { it.date.toFormattedDate() },
            titleProvider = { it.type ?: "Type inconnu" },
            centerInfoProvider = { it.responseStatus ?: "Statut inconnu" },
            bottomLeftInfoProvider = { "Entreprise : ${it.companyId}" },
            items = visible,
            onItemClick = onItemClick
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
