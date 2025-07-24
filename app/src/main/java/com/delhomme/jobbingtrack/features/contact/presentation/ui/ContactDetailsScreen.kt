package com.delhomme.jobbingtrack.features.contact.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.delhomme.jobbingtrack.core.utils.toFormattedDate
import com.delhomme.jobbingtrack.features.application.presentation.viewmodels.ApplicationStatusViewModel
import com.delhomme.jobbingtrack.features.application.presentation.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.features.call.presentation.viewmodel.CallViewModel
import com.delhomme.jobbingtrack.features.company.presentation.viewmodel.CompanyViewModel
import com.delhomme.jobbingtrack.features.contact.presentation.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.features.contact.presentation.viewmodel.DepartmentTypeViewModel
import com.delhomme.jobbingtrack.features.contact.presentation.viewmodel.PositionTypeViewModel
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpWithContacts
import com.delhomme.jobbingtrack.features.followup.presentation.viewmodel.FollowUpStatusViewModel
import com.delhomme.jobbingtrack.features.followup.presentation.viewmodel.FollowUpTypeViewModel
import com.delhomme.jobbingtrack.features.followup.presentation.viewmodel.FollowUpViewModel
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.features.interview.presentation.viewmodel.InterviewStyleViewModel
import com.delhomme.jobbingtrack.features.interview.presentation.viewmodel.InterviewTypeViewModel
import com.delhomme.jobbingtrack.features.interview.presentation.viewmodel.InterviewViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.shared.DetailItemCard
import com.delhomme.jobbingtrack.ui.shared.SectionTitle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    contactId: String,
    navController: NavController,
    userId: String,
    contactVm: ContactViewModel = viewModel(),
    companyVm: CompanyViewModel = viewModel(),
    applicationVm: ApplicationViewModel = viewModel(),
    callVm: CallViewModel = viewModel(),
    interviewVm: InterviewViewModel = viewModel(),
    followUpVm: FollowUpViewModel = viewModel(),
    positionTypeVm: PositionTypeViewModel = viewModel(),
    departmentTypeVm: DepartmentTypeViewModel = viewModel(),
    applicationStatusVm: ApplicationStatusViewModel = viewModel(),
    interviewTypeVm: InterviewTypeViewModel = viewModel(),
    interviewStyleVm: InterviewStyleViewModel = viewModel(),
    followUpTypeVm: FollowUpTypeViewModel = viewModel(),
    followUpStatusVm: FollowUpStatusViewModel = viewModel()
) {// 1) Charger le contact
    val contacts by contactVm.allForUser(userId).observeAsState(emptyList())
    val contact = contacts.find { it.id == contactId } ?: return

    // 2) Charger les données de base
    val companies by companyVm.allForUser(userId).observeAsState(emptyList())
    val applications by applicationVm.allForUser(userId).observeAsState(emptyList())
    val calls by callVm.allForUser(userId).observeAsState(emptyList())

    // 3) Charger les données avec relations Room
    //val interviewsWithContacts by interviewVm.getAllActiveWithContacts(userId).observeAsState(emptyList())
    //val followUpsWithContacts by followUpVm.getAllActiveWithContacts(userId).observeAsState(emptyList())

    val interviewsWithContacts: List<InterviewWithContacts> by interviewVm.getAllActiveWithContacts(userId).observeAsState(emptyList())
    val followUpsWithContacts: List<FollowUpWithContacts> by followUpVm.getAllActiveWithContacts(userId).observeAsState(emptyList())
    // 4) Charger les listes pour résolution des IDs
    val positions by positionTypeVm.all.observeAsState(emptyList())
    val departments by departmentTypeVm.all.observeAsState(emptyList())
    val appStatuses by applicationStatusVm.all.observeAsState(emptyList())
    val interviewTypes by interviewTypeVm.all.observeAsState(emptyList())
    val interviewStyles by interviewStyleVm.all.observeAsState(emptyList())
    val followUpTypes by followUpTypeVm.all.observeAsState(emptyList())
    val followUpStatuses by followUpStatusVm.all.observeAsState(emptyList())

    // 5) Résolution des labels pour le contact
    val positionLabel = positions.find { it.id == contact.positionId }?.label ?: "Non spécifié"
    val departmentLabel = departments.find { it.id == contact.departmentId }?.name ?: "Non spécifié"
    val company = companies.find { it.id == contact.companyId }

    // 6) Filtrer les entités liées au contact
    val linkedCalls = calls.filter { it.contactId == contactId }

    val linkedInterviews = interviewsWithContacts.filter { interviewWithContacts ->
        interviewWithContacts.contacts.any { it.id == contactId }
    }

    val linkedFollowUps = followUpsWithContacts.filter { followUpWithContacts ->
        followUpWithContacts.contacts.any { it.id == contactId }
    }

    // Candidatures liées via les appels, entretiens ou relances
    val linkedApplications = applications.filter { app ->
        linkedCalls.any { it.applicationId == app.id } ||
                linkedInterviews.any { it.interview.applicationId == app.id } ||
                linkedFollowUps.any { it.followUp.applicationId == app.id }
    }

    BackHandler { navController.popBackStack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${contact.firstName} ${contact.lastName}") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("${Routes.CONTACT_EDIT}/${contactId}")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier Contact")
                    }
                    IconButton(onClick = {
                        contactVm.archive(listOf(contactId), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Archive, contentDescription = "Archiver")
                    }

                    IconButton(onClick = {
                        contactVm.delete(listOf(contactId), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.DeleteForever, contentDescription = "Supprimer définitivement")
                    }

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
                Text("Informations sur le contact", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Entreprise : ${company?.name ?: "—"}", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                Text("Téléphone : ${contact.phone ?: "Non spécifié"}")
                Text("Email : ${contact.email ?: "Non spécifié"}")
                Text("Poste : $positionLabel")
                Text("Service : $departmentLabel")
            }


            if (linkedApplications.isNotEmpty()) {
                item { SectionTitle("Candidatures liées") }
                items(linkedApplications) { app ->
                    val status = appStatuses.find { it.id == app.statusRefId }?.label ?: "—"
                    DetailItemCard(
                        title = app.title,
                        subtitle = status,
                        onClick = { navController.navigate("${Routes.APPLICATION_DETAIL}/${app.id}") }
                    )
                }
            }

            if (linkedCalls.isNotEmpty()) {
                item { SectionTitle("Appels liés") }
                items(linkedCalls) { call ->
                    DetailItemCard(
                        title = call.subject,
                        subtitle = call.timestamp.toFormattedDate(),
                        onClick = { navController.navigate("${Routes.DETAIL_CALL}/${call.id}") }
                    )
                }
            }

            // Entretiens liés
            if (linkedInterviews.isNotEmpty()) {
                item { SectionTitle("Entretiens liés") }
                items(linkedInterviews) { interviewWithContacts ->
                    val interview = interviewWithContacts.interview
                    val typeLabel = interviewTypes.find { it.id == interview.typeId }?.label ?: "Type inconnu"
                    val styleLabel = interviewStyles.find { it.id == interview.styleId }?.label ?: "Style inconnu"
                    DetailItemCard(
                        title = "$typeLabel — $styleLabel",
                        subtitle = interview.dateTime.toFormattedDate(),
                        onClick = { navController.navigate("${Routes.ENTRETIEN_DETAIL}/${interview.id}") }
                    )
                }
            }

            // Relances liées
            if (linkedFollowUps.isNotEmpty()) {
                item { SectionTitle("Relances liées") }
                items(linkedFollowUps) { followUpWithContacts ->
                    val followUp = followUpWithContacts.followUp
                    val typeLabel = followUpTypes.find { it.id == followUp.typeId }?.label ?: "Type inconnu"
                    val statusLabel = followUpStatuses.find { it.id == followUp.statusId }?.label ?: "Statut inconnu"
                    DetailItemCard(
                        title = typeLabel,
                        subtitle = statusLabel,
                        onClick = { navController.navigate("${Routes.FOLLOWUP_DETAIL}/${followUp.id}") }
                    )
                }
            }
        }
    }
}
