package com.delhomme.jobbingtrack.features.interview.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.core.utils.getLabelById
import com.delhomme.jobbingtrack.core.utils.toFormattedDateTime
import com.delhomme.jobbingtrack.features.application.presentation.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.features.contact.presentation.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.features.interview.presentation.viewmodel.InterviewViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.shared.DetailItemCard
import com.delhomme.jobbingtrack.ui.shared.SectionTitle
import kotlin.collections.isNotEmpty


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterviewDetailScreen(
    interviewId: String,
    navController: NavController,
    onBackClick: () -> Unit,
    interviewViewModel: InterviewViewModel = viewModel(),
    applicationViewModel: ApplicationViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
    userId: String
) {
    // 1) On observe la liste des Entretiens+Contacts
    val interviewsWithContacts by interviewViewModel.getAllWithContacts(userId).observeAsState(emptyList())
    val interviewWithContacts = interviewsWithContacts.firstOrNull { it.interview.id == interviewId } ?: return
    val interview = interviewWithContacts.interview
    val contactsOfInterview = interviewWithContacts.contacts


    // 2) On charge les données de base
    val allTypes by interviewViewModel.allTypes.observeAsState(emptyList())
    val allStyles by interviewViewModel.allStyles.observeAsState(emptyList())

    // Type et style
    //val typeLabel = allTypes.find { it.id == interview.type_id }?.label ?: "Non spécifié"
    val typeLabel = getLabelById(interview.typeId, allTypes) { it.label }
    //val styleLabel = allStyles.find { it.id == interview.styleId }?.label ?: "Non spécifié"
    val styleLabel = getLabelById(interview.styleId, allStyles) { it.label }

    // 3) On charge la candidature liée
    val allApplications      by applicationViewModel.activeForUser(userId = userId).observeAsState(emptyList())
    val application = allApplications.firstOrNull { it.id == interview.applicationId }
    val allApplicationStatuses by applicationViewModel.allStatuses.observeAsState(emptyList())

    /*val applicationStatusLabel = application?.let { app ->
        allApplicationStatuses.find { it.id == app.applicationStatusId }?.label ?: "Non spécifié"
    }*/

    val applicationStatusLabel = application?.let { app ->
        getLabelById(app.applicationStatusId, allApplicationStatuses) { it.label }
    }

    BackHandler { onBackClick() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(interview.applicationId) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("${Routes.INTERVIEW_EDIT}/${interview.id}")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier")
                    }
                    IconButton(onClick = {
                        interviewViewModel.archive(listOf(interview.id), userId)
                        onBackClick()
                    }) { Icon(Icons.Default.Archive, contentDescription = "Archiver") }

                    IconButton(onClick = {
                        interviewViewModel.delete(listOf(interview.id), userId)
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
                Text(text = "Type : $typeLabel")
                Text(text = "Style  : $styleLabel")
                Text(text = "Date et heure  : ${interview.dateTime.toFormattedDateTime()}")
                Text(text = "Adresse : ${interview.location ?: "Non spécifiée"}")
                Text(text = "Tests requis  : ${interview.testsNeeded}")
                Text(text = "Deadline des test : ${interview.testsDeadline ?: "Non spécifiée"}")
                Text(text = "Notes d'interview : ${interview.interviewNotes ?: "Non spécifiée"}")
                Text(text = "Durée : ${interview.durationMinutes ?: "Non spécifiée"}")
                Text(text = "Notes pré entretien : ${interview.preInterviewNotes ?: "Non spécifiée"}")
                Text(text = "Notes post entretien : ${interview.postInterviewNotes ?: "Non spécifiée"}")
                Text(text = "Date de retour : ${interview.returnDate ?: "Non spécifiée"}")
            }

            application?.let { app ->
                item {
                    SectionTitle("Candidature liée")
                    DetailItemCard(
                        title = app.title,
                        subtitle = applicationStatusLabel,
                        onClick = {
                            navController.navigate("${Routes.APPLICATION_DETAIL}/${app.id}")
                        }
                    )
                }
            }

            // — Participants (contacts)
            if (contactsOfInterview.isNotEmpty()) {
                item { SectionTitle("Contacts liés") }
                items(contactsOfInterview) { contact ->
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
