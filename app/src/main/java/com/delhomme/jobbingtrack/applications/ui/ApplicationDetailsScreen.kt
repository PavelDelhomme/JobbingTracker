package com.delhomme.jobbingtrack.applications.ui


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.applications.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.calls.viewmodels.CallViewModel
import com.delhomme.jobbingtrack.commons.ui.items.DetailItemCard
import com.delhomme.jobbingtrack.companies.viewmodels.CompanyViewModel
import com.delhomme.jobbingtrack.contacts.viewmodels.ContactViewModel
import com.delhomme.jobbingtrack.followsup.viewmodels.FollowUpViewModel
import com.delhomme.jobbingtrack.interviews.viewmodels.InterviewViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.utils.toFormattedDate
import kotlin.collections.find
import androidx.compose.foundation.lazy.items

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

    // 3) Filtrer celles qui concernent notre candidature
    val myFollowUps   = followups.filter { it.applicationId == applicationId }
    val myCalls     = calls.filter { it.applicationId == applicationId }
    val myInterviews = interviewsWithContacts.filter {
        it.interview.applicationId == applicationId
    }
    // les contacts qu'on a associés via appels / relances / entretiens :
    val myContacts = contacts.filter { contact ->
        myFollowUps.any { it.contactsIds.any { id -> id == contact.id } } ||
                myCalls.any { it.contactId == contact.id } ||
                myInterviews.any { it.contacts.any { ec -> ec.id == contact.id } }
    }


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
                    text = "Statut: ${application.applicationStatus}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Type de contrat: ${application.applicationType}",
                    style = MaterialTheme.typography.bodyMedium
                )
                application.platform?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Plateforme: $it", style = MaterialTheme.typography.bodySmall)
                }
                application.contractType?.let {
                    Text(
                        text = "Type de contrat: $it",
                        style = MaterialTheme.typography.bodySmall
                    )
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


            item {
                SectionTitle("Relances liées")
            }
            items(myFollowUps, key = { it.id }) { followup ->
                DetailItemCard(
                    title = "${followup.type ?: "Type inconnu"} (${followup.responseStatus ?: "Statut inconnu"})",
                    subtitle = followup.date.toString(),
                    onClick = { navController.navigate("${Routes.FOLLOWUP_DETAIL}/${followup.id}")}
                )
            }

            item {
                SectionTitle("Appels liés")
            }
            items(myCalls, key = { it.id }) { call ->
                DetailItemCard(
                    title = call.subject,
                    subtitle = call.notes,
                    onClick = { navController.navigate("${Routes.DETAIL_CALL}/${call.id}")}
                )
            }

            item {
                SectionTitle("Entretiens liés")
            }
            items(myInterviews, key = { it.interview.id }) { interviewWithContacts ->
                val interview = interviewWithContacts.interview
                val contacts = interviewWithContacts.contacts

                DetailItemCard(
                    title = "${interview.type ?: "Type inconnu"} - ${interview.style ?: "Style inconnu"}",
                    subtitle = contacts.joinToString(", ") { it.firstName + " " + it.lastName },
                    onClick = { navController.navigate("${Routes.ENTRETIEN_DETAIL}/${interview.id}") }
                )
            }

            item {
                SectionTitle("Contacts liés")
            }
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

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}
@Composable
fun AddActionButtons(
    onAddFollowUp: () -> Unit,
    onAddCall: () -> Unit,
    onAddInterview: () -> Unit,
    onAddContact: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            if (expanded) {
                SmallFloatingActionButton(onClick = { expanded = false; onAddFollowUp() }) {
                    Icon(Icons.Default.Alarm, contentDescription = "Ajouter Relance")
                }
                SmallFloatingActionButton(onClick = { expanded = false; onAddCall() }) {
                    Icon(Icons.Default.Phone, contentDescription = "Ajouter Appel")
                }
                SmallFloatingActionButton(onClick = { expanded = false; onAddInterview() }) {
                    Icon(Icons.Default.Chat, contentDescription = "Ajouter Entretien")
                }
                SmallFloatingActionButton(onClick = { expanded = false; onAddContact() }) {
                    Icon(Icons.Default.Person, contentDescription = "Ajouter Contact")
                }
            }

            FloatingActionButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter")
            }
        }
    }
}
