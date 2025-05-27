package com.delhomme.jobbingtrack.ui.major.relances

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.RelanceViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.components.items.DetailItemCard
import com.delhomme.jobbingtrack.ui.major.candidatures.SectionTitle
import com.delhomme.jobbingtrack.utils.toFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RelanceDetailScreen(
    relanceId: String,
    navController: NavController,
    userId: String,
    relVm: RelanceViewModel = viewModel(),
    candVm: CandidatureViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel()
) {
    // 1) VM + chargement
    val allRelances by relVm.getAllForUser(userId).observeAsState(emptyList())
    val allCands by candVm.activeForUser(userId).observeAsState(emptyList())
    val allContacts by contactVm.activeForUser(userId).observeAsState(emptyList())

    val rel = allRelances.firstOrNull { it.id == relanceId } ?: return
    val candidature = allCands.firstOrNull { it.id == rel.candidatureId }
    val contact = allContacts.firstOrNull { it.id == rel.contactId }


    BackHandler { navController.popBackStack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Détail Relance") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("${Routes.EDIT_RELANCE}/${rel.id}")
                    }) {
                        Icon(Icons.Default.Edit, "Modifier")
                    }
                    IconButton(onClick = {
                        relVm.archive(listOf(rel.id), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.DeleteForever, "Archiver")
                    }
                    IconButton(onClick = {
                        relVm.delete(listOf(rel.id.toString()), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.DeleteForever, "Supprimer définitivement")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Type : ${rel.type ?: "Non spécifié"}")
            Text("Statut de réponse : ${rel.responseStatus ?: "Non spécifié"}")
            Text("Date : ${rel.date.toFormattedDate()}")
            rel.notes?.let { Text("Notes              : $it") }

            candidature?.let {
                SectionTitle("Candidature liée")
                DetailItemCard(
                    title = it.title,
                    subtitle = it.applicationStatus.toString(),
                    onClick = { navController.navigate("${Routes.CANDIDATURE_DETAIL}/${it.id}") }
                )
            }

            contact?.let {
                SectionTitle("Contact lié")
                DetailItemCard(
                    title = "${it.firstName} ${it.lastName}",
                    subtitle = it.position ?: "Pas de poste",
                    onClick = { navController.navigate("${Routes.CONTACT_DETAIL}/${it.id}") }
                )
            }
        }
    }
}
