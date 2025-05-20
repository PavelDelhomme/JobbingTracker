package com.delhomme.jobbingtrack.ui.major.entreprises

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
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import com.delhomme.jobbingtrack.ui.components.ListScreen


@Composable
fun EntreprisesScreen(
    entreprises: List<EntrepriseEntity>,
    onItemClick: (EntrepriseEntity) -> Unit,
    onAddClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val sorted  = entreprises.sortedBy { it.name.lowercase() }
        val visible = sorted.filter { !it.isArchived && !it.isDeleted }

        ListScreen(
            dateProvider           = { null },
            titleProvider          = { it.name },
            centerInfoProvider     = { it.type ?: "" },
            bottomLeftInfoProvider = { it.phone ?: it.email ?: "" },
            items                  = visible,
            onItemClick            = onItemClick
        )

        FloatingActionButton(
            onClick  = onAddClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Ajouter une entreprise")
        }
    }
}