package com.delhomme.jobbingtrack.feature.application.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.applications.vms.ApplicationPlatformViewModel
import com.delhomme.jobbingtrack.applications.vms.ApplicationStatusViewModel
import com.delhomme.jobbingtrack.applications.vms.ApplicationTypeViewModel
import com.delhomme.jobbingtrack.applications.vms.ContractTypeViewModel
import com.delhomme.jobbingtrack.followsup.vms.FollowUpStatusViewModel
import com.delhomme.jobbingtrack.followsup.vms.FollowUpTypeViewModel
import com.delhomme.jobbingtrack.followsup.vms.FollowUpViewModel
import com.delhomme.jobbingtrack.interviews.vms.InterviewStyleViewModel
import com.delhomme.jobbingtrack.interviews.vms.InterviewTypeViewModel
import com.delhomme.jobbingtrack.interviews.vms.InterviewViewModel
import com.delhomme.jobbingtrack.navigation.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationDetailsScreen(
    applicationId: String,
    navController: NavController,
    applicationVm: ApplicationViewModel = viewModel(),
    followUpVm: FollowUpViewModel = viewModel(),
    callVm: CallViewModel = viewModel(),
    interviewVm: InterviewViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
    companyVm: CompanyViewModel = viewModel(),
    applicationStatusVm: ApplicationStatusViewModel = viewModel(),
    applicationTypeVm: ApplicationTypeViewModel = viewModel(),
    applicationPlatformVm: ApplicationPlatformViewModel = viewModel(),
    contractTypeVm: ContractTypeViewModel = viewModel(),
    interviewTypeVm: InterviewTypeViewModel = viewModel(),
    interviewStyleVm: InterviewStyleViewModel = viewModel(),
    followUpTypeVm: FollowUpTypeViewModel = viewModel(),
    followUpStatusVm: FollowUpStatusViewModel = viewModel(),
    userId: String
) {
    // 1) Charger la candidature
    val allApplications by applicationVm.allForUser(userId = userId).observeAsState(emptyList())
    val application = allApplications.find { it.id == applicationId }
        ?: return // ou u petit loader / message d'erreur

    // 2) Charger l’entreprise
    val allCompanies by companyVm.allForUser(userId = userId).observeAsState(emptyList())
    val company = allCompanies.find { it.id == application.companyId }

    // 2) Charger toutess les entités liées
    val followups by followUpVm.allForUser(userId = userId).observeAsState(emptyList())
    val calls by callVm.allForUser(userId = userId).observeAsState(emptyList())
    val interviewsWithContacts by interviewVm.getAllWithContacts(userId).observeAsState(emptyList())
    val contacts by contactVm.allForUser(userId = userId).observeAsState(emptyList())

    // 4) Charger les types/labels pour affichage
    val allStatus by applicationStatusVm.all.observeAsState(emptyList())
    val allTypes by applicationTypeVm.all.observeAsState(emptyList())
    val allPlatforms by applicationPlatformVm.all.observeAsState(emptyList())
    val allContracts by contractTypeVm.all.observeAsState(emptyList())
    val allInterviewTypes by interviewTypeVm.all.observeAsState(emptyList())
    val allInterviewStyles by interviewStyleVm.all.observeAsState(emptyList())
    val allFollowUpTypes by followUpTypeVm.all.observeAsState(emptyList())
    val allFollowUpStatus by followUpStatusVm.all.observeAsState(emptyList())


    // 5) Filtrer entités liées à cette application
    val myFollowUps = followups.filter { it.applicationId == applicationId }
    val myCalls = calls.filter { it.applicationId == applicationId }
    val myInterviews = interviewsWithContacts.filter { it.interview.applicationId == applicationId }

    // 6) Contacts liés via interviews/calls (et optionnellement via POJO FollowUpWithContacts)
    val myContacts = contacts.filter { contact ->
        myCalls.any { it.contactId == contact.id } ||
                myInterviews.any { it.contacts.any { ec -> ec.id == contact.id } }
        // Pour les relances, il faudrait charger les FollowUpWithContacts si besoin
    }

    val statusLabel = allStatus.find { it.id == application.statusRefId }?.label ?: "—"
    val typeLabel = allTypes.find { it.id == application.typeRefId }?.label ?: "—"
    val platformLabel = allPlatforms.find { it.id == application.platformRefId }?.label ?: "—"
    val contractLabel = allContracts.find { it.id == application.contractTypeRefId }?.label ?: "—"
    BackHandler {
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = application.title) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("${Routes.APPLICATION_EDIT}/${application.id}")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier la candidature")
                    }
                    IconButton(onClick = {
                        applicationVm.archive(listOf(application.id), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Archive, contentDescription = "Archiver")
                    }

                    IconButton(onClick = {
                        applicationVm.delete(listOf(application.id), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.DeleteForever, contentDescription = "Supprimer définitivement")
                    }

                }
            )
        },
        floatingActionButton = {
            AddActionButtons(
                onAddFollowUp   = { navController.navigate("${Routes.FOLLOWUP_ADD}?linkedApplicationId=${application.id}") },
                onAddCall     = { navController.navigate("${Routes.CALL_ADD}?linkedApplicationId=${application.id}") },
                onAddInterview = { navController.navigate("${Routes.INTERVIEW_ADD}?linkedApplicationId=${application.id}") },
                onAddContact   = { navController.navigate("${Routes.CONTACT_ADD}?linkedApplicationId=${application.id}") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("Titre : ${application.title}")

                Text("Entreprise : ${company?.name ?: "—"}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.clickable {
                        navController.navigate("${Routes.COMPANY_DETAIL}/${application.companyId}")
                    }
                )
                Text(
                    text = "Date: ${application.applicationDate.toFormattedDate()}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Statut: $statusLabel",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Type de candidature: $typeLabel",
                    style = MaterialTheme.typography.bodyMedium
                )
                if (platformLabel != "—") {
                    Text(text = "Plateforme: $platformLabel", style = MaterialTheme.typography.bodySmall)
                }
                if (contractLabel != "—") {
                    Text(text = "Type de contrat: $contractLabel", style = MaterialTheme.typography.bodySmall)
                }
                application.location?.let {
                    Text(
                        text = "Lieu du poste: $it",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                application.notes?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Notes: $it", style = MaterialTheme.typography.bodySmall)
                }
            }


            item { SectionTitle("Relances liées") }
            items(myFollowUps, key = { it.id }) { followup ->
                val typeLabel = allFollowUpTypes.find { t -> t.id == followup.typeId }?.label ?: "Type inconnu"
                val statusLabel = allFollowUpStatus.find { s -> s.id == followup.responseId }?.label ?: "Statut inconnu"
                DetailItemCard(
                    title = "$typeLabel ($statusLabel)",
                    subtitle = followup.date.toFormattedDate(),
                    onClick = { navController.navigate("${Routes.FOLLOWUP_DETAIL}/${followup.id}") }
                )
            }

            item { SectionTitle("Appels liés") }
            items(myCalls, key = { it.id }) { call ->
                DetailItemCard(
                    title = call.subject,
                    subtitle = call.notes,
                    onClick = { navController.navigate("${Routes.DETAIL_CALL}/${call.id}") }
                )
            }

            item { SectionTitle("Entretiens liés") }
            items(myInterviews, key = { it.interview.id }) { interviewWithContacts ->
                val interview = interviewWithContacts.interview
                val contacts = interviewWithContacts.contacts
                val interviewTypeLabel = allInterviewTypes.find { t -> t.id == interview.typeId }?.label ?: "Type inconnu"
                val interviewStyleLabel = allInterviewStyles.find { s -> s.id == interview.styleId }?.label ?: "Style inconnu"
                DetailItemCard(
                    title = "$interviewTypeLabel - $interviewStyleLabel",
                    subtitle = contacts.joinToString(", ") { it.firstName + " " + it.lastName },
                    onClick = { navController.navigate("${Routes.ENTRETIEN_DETAIL}/${interview.id}") }
                )
            }

            item { SectionTitle("Contacts liés") }
            items(myContacts, key = { it.id }) { contact ->
                DetailItemCard(
                    title = "${contact.firstName} ${contact.lastName}",
                    subtitle = "${contact.phone}",
                    onClick = { navController.navigate("${Routes.CONTACT_DETAIL}/${contact.id}") }
                )
            }

        }
    }
}
