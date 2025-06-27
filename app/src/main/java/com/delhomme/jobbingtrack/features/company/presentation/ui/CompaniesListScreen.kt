package com.delhomme.jobbingtrack.features.company.presentation.ui

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


@Composable
fun CompaniesListScreen(
    companies: List<CompanyEntity>,
    companiesVm: CompanyViewModel,
    onItemClick: (CompanyEntity) -> Unit,
    onEdit: (CompanyEntity) -> Unit,
    onArchive: (CompanyEntity) -> Unit,
    onDelete: (CompanyEntity) -> Unit,
    onAddClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val sorted  = companies.sortedBy { it.name.lowercase() }
        val visible = sorted.filter { !it.base.isArchived && !it.base.isDeleted }

        ListScreen(
            dateProvider = { null },
            titleProvider = { it.name },
            centerInfoProvider = { it.type ?: "" },
            bottomLeftInfoProvider = { it.phone ?: it.email ?: "" },
            items = visible,
            onItemClick = onItemClick
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