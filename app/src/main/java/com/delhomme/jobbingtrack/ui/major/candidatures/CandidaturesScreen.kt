package com.delhomme.jobbingtrack.ui.major.candidatures

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.delhomme.jobbingtrack.data.classes.Candidature
import com.delhomme.jobbingtrack.data.local.entities.CandidatureEntity
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.components.ListScreen
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun CandidaturesScreen(
    navController: NavController,
    candidatures: List<CandidatureEntity>,
    entreprises: List<EntrepriseEntity>,
    onItemClick: (CandidatureEntity) -> Unit,
    onEdit: (CandidatureEntity) -> Unit,
    onArchive: (CandidatureEntity) -> Unit,
    onDelete: (CandidatureEntity) -> Unit,
    onAddClick: () -> Unit
) {
    val entById = remember(entreprises) { entreprises.associateBy { it.id } }

    Box(modifier = Modifier.fillMaxSize()) {
        val sortedCandidatures = candidatures.sortedByDescending { it.applicationDate }
        val visibleCandidature = sortedCandidatures.filter { !it.isArchived }

        ListScreen(
            dateProvider           = { it.applicationDate.toFormattedDate() },
            titleProvider          = { it.title },
            centerInfoProvider     = { it.applicationStatus },
            bottomLeftInfoProvider = { entById[it.companyId]?.name ?: "Entreprise inconnue" },
            items = candidatures.filter { !it.isArchived },
            onItemClick            = onItemClick,
            onEdit                  =  { onEdit(it) },
            onArchive                = { onArchive(it) },
            onDelete                = { onDelete(it) },
        )

        FloatingActionButton(
            onClick = { onAddClick() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Ajouter Candidature")
        }

    }
}
