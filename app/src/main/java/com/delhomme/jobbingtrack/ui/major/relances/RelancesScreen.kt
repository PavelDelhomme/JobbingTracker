package com.delhomme.jobbingtrack.ui.major.relances

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.delhomme.jobbingtrack.data.classes.Relance
import com.delhomme.jobbingtrack.data.local.entities.ContactEntity
import com.delhomme.jobbingtrack.data.local.entities.RelanceEntity
import com.delhomme.jobbingtrack.data.viewmodel.RelanceViewModel
import com.delhomme.jobbingtrack.ui.components.ListScreen
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun RelancesScreen(
    relances: List<RelanceEntity>,
    relancesVm: RelanceViewModel,
    onItemClick: (RelanceEntity) -> Unit,
    onEdit: (ContactEntity) -> Unit,
    onArchive: (ContactEntity) -> Unit,
    onDelete: (ContactEntity) -> Unit,
    onAddClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val sorted = relances.sortedByDescending { it.date }
        val visible = sorted.filter { !it.isArchived }

        ListScreen(
            dateProvider           = { it.date.toFormattedDate() },
            titleProvider          = { it.type ?: "Type inconnu" },
            centerInfoProvider     = { it.responseStatus ?: "Statut inconnu" },
            bottomLeftInfoProvider = { "Entreprise : ${it.companyId}" },
            items                  = visible,
            onItemClick            = onItemClick
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
