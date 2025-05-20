package com.delhomme.jobbingtrack.ui.major.entretiens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Archive
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
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntretienViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.components.DetailItemCard
import com.delhomme.jobbingtrack.ui.major.candidatures.SectionTitle
import com.delhomme.jobbingtrack.utils.toFormattedDate
import com.delhomme.jobbingtrack.utils.toFormattedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntretienDetailScreen(
    entretienId: String,
    navController: NavController,
    onBackClick: () -> Unit,
    entVm: EntretienViewModel = viewModel(),
    candVms: CandidatureViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
) {
    // 1) On observe la liste des Entretiens+Contacts
    val allEntsWithContacts by entVm.entretiens.observeAsState(emptyList<EntretienWithContacts>())
    // 2) On trouve notre entretienWithContacts
    val ewc = allEntsWithContacts.firstOrNull { it.entretien.id == entretienId } ?: return
    val entretien = ewc.entretien
    val participants  = ewc.contacts

    // 3) On charge la candidature liée
    val allCands      by candVms.candidatures.observeAsState(emptyList())
    val candidatureOpt = allCands.firstOrNull { it.id == entretien.candidatureId }

    BackHandler { onBackClick() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(entretien.candidatureId) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("${Routes.EDIT_ENTRETIEN}/${entretien.id}")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier")
                    }
                    IconButton(onClick = {
                        entVm.archive(entretien.id)
                        onBackClick()
                    }) { Icon(Icons.Default.Archive, contentDescription = "Archiver") }

                    IconButton(onClick = {
                        entVm.delete(entretien.id)
                        onBackClick()
                    }) { Icon(Icons.Default.DeleteForever, contentDescription = "Supprimer définitivement") }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(text = "Informations sur l'entreprise", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Type : ${entretien.type ?: "Non spécifié"}")
                Text(text = "Style  : ${entretien.style ?: "Non spécifié"}")
                Text(text = "Date et heure  : ${entretien.dateTime.toFormattedDateTime()}")
                Text(text = "Adresse : ${entretien.location ?: "Non spécifiée"}")
                Text(text = "Tests requis  : ${entretien.testsNeeded}")
                Text(text = "Deadline des test : ${entretien.testsDeadline ?: "Non spécifiée"}")
                Text(text = "Notes d'interview : ${entretien.interviewNotes ?: "Non spécifiée"}")
                Text(text = "Durée : ${entretien.durationMinutes ?: "Non spécifiée"}")
                Text(text = "Notes pré entretien : ${entretien.preInterviewNotes ?: "Non spécifiée"}")
                Text(text = "Notes post entretien : ${entretien.postInterviewNotes ?: "Non spécifiée"}")
                Text(text = "Date de retour : ${entretien.returnDate ?: "Non spécifiée"}")
            }

            candidatureOpt?.let { cand ->
                item {
                    SectionTitle("Candidature liée")
                    DetailItemCard(
                        title = cand.title,
                        subtitle = cand.applicationStatus,
                        onClick = {
                            navController.navigate("${Routes.CANDIDATURE_DETAIL}/${cand.id}")
                        }
                    )
                }
            }

            // — Participants (contacts)
            if (participants.isNotEmpty()) {
                item { SectionTitle("Contacts liés") }
                items(participants) { contact ->
                    DetailItemCard(
                        title    = "${contact.firstName} ${contact.lastName}",
                        subtitle = contact.position ?: "—",
                        onClick  = {
                            navController.navigate("${Routes.CONTACT_DETAIL}/${contact.id}")
                        }
                    )
                }
            }
        }
    }
}
