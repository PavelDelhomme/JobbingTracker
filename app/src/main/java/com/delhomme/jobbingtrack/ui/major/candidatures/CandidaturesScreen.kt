package com.delhomme.jobbingtrack.ui.major.candidatures

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.classes.Candidature
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.components.ListScreen
import com.delhomme.jobbingtrack.utils.toFormattedDate

@Composable
fun CandidaturesScreen(
    candidatures: List<Candidature>,
    onItemClick: (Candidature) -> Unit,
    onAddClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val sortedCandidatures = candidatures.sortedByDescending { it.applicationDate }
        val visibleCandidature = sortedCandidatures.filter { !it.isArchived }

        ListScreen(
            dateProvider = { candidature -> candidature.applicationDate.toFormattedDate() },
            titleProvider = { candidature -> candidature.title },
            centerInfoProvider = { candidature -> candidature.applicationStatus.name },
            bottomLeftInfoProvider = { candidature -> candidature.companyName },
            items = visibleCandidature,
            onItemClick = { candidature ->
                onItemClick(candidature)
            }

        )

        FloatingActionButton(
            onClick = { onAddClick() },
            modifier = Modifier
                .align(androidx.compose.ui.Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Ajouter Candidature")
        }

    }
}
