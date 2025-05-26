package com.delhomme.jobbingtrack.ui.major.appels

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

import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import com.delhomme.jobbingtrack.data.viewmodel.AppelViewModel
import com.delhomme.jobbingtrack.ui.components.lists.ListScreen
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun AppelsScreen(
    appels: List<AppelEntity>,
    appelsVm: AppelViewModel,
    onItemClick: (AppelEntity) -> Unit,
    onEdit: (AppelEntity) -> Unit,
    onArchive: (AppelEntity) -> Unit,
    onDelete: (AppelEntity) -> Unit,
    onAddClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        val sorted = appels.sortedByDescending { it.dateTime }
        val visible = sorted.filter { !it.isArchived }

        ListScreen(
            dateProvider = { it.dateTime.toFormattedDate() },
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
