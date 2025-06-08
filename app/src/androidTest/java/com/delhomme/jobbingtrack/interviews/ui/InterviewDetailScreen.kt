package com.delhomme.jobbingtrack.interviews.ui


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
import com.delhomme.jobbingtrack.data.viewmodel.Application.ApplicationViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Contact.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Interview.InterviewViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.components.items.DetailItemCard
import com.delhomme.jobbingtrack.ui.major.applications.SectionTitle
import com.delhomme.jobbingtrack.utils.toFormattedDateTime

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

    // 3) On charge la candidature liée
    val allApplications      by applicationViewModel.activeForUser(userId = userId).observeAsState(emptyList())
    val applicationOpt = allApplications.firstOrNull { it.id == interview.applicationId }

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
                Text(text = "Type : ${interview.type ?: "Non spécifié"}")
                Text(text = "Style  : ${interview.style ?: "Non spécifié"}")
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

            applicationOpt?.let { application ->
                item {
                    SectionTitle("Candidature liée")
                    DetailItemCard(
                        title = application.title,
                        subtitle = application.applicationStatus,
                        onClick = {
                            navController.navigate("${Routes.APPLICATION_DETAIL}/${application.id}")
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
