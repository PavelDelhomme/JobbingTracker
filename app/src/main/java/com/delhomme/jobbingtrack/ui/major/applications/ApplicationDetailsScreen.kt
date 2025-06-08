package com.delhomme.jobbingtrack.ui.major.applications

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.delhomme.jobbingtrack.data.viewmodel.Call.CallViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Application.ApplicationViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Contact.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Company.CompanyViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Interview.InterviewViewModel
import com.delhomme.jobbingtrack.data.viewmodel.FollowUp.FollowUpViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.components.items.DetailItemCard
import com.delhomme.jobbingtrack.utils.toFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationDetailsScreen(
    applicationId: String,
    navController: NavController,
    candidatureVm: ApplicationViewModel = viewModel(),
    relanceVm: FollowUpViewModel = viewModel(),
    appelVm: CallViewModel = viewModel(),
    entretienVm: InterviewViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
    entrepriseVm: CompanyViewModel = viewModel(),
    userId: String
) {
    // 1) Charger la candidature
    val allCands by candidatureVm.allForUser(userId = userId).observeAsState(emptyList())
    val candidature = allCands.find { it.id == applicationId }
        ?: return // ou u petit loader / message d'erreur

    // 2) Charger l’entreprise
    val allEnts by entrepriseVm.allForUser(userId = userId).observeAsState(emptyList())
    val entreprise = allEnts.find { it.id == candidature.companyId }

    // 2) Charger toutess les entités liées
    val relances by relanceVm.allForUser(userId = userId).observeAsState(emptyList())
    val appels by appelVm.allForUser(userId = userId).observeAsState(emptyList())
    val entretiensWithContacts by entretienVm.getAllWithContacts(userId).observeAsState(emptyList())
    val contacts by contactVm.allForUser(userId = userId).observeAsState(emptyList())

    // 3) Filtrer celles qui concernent notre candidature
    val myRelances   = relances.filter { it.applicationId == applicationId }
    val myAppels     = appels.filter { it.applicationId == applicationId }
    val myEntretiens = entretiensWithContacts.filter {
        it.entretien.applicationId == applicationId
    }
    // les contacts qu'on a associés via appels / relances / entretiens :
    val myContacts = contacts.filter { contact ->
        myRelances.any { it.contactId == contact.id } ||
                myAppels.any { it.contactId == contact.id } ||
                myEntretiens.any { it.contacts.any { ec -> ec.id == contact.id } }
    }


    BackHandler {
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = candidature.title) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("${Routes.APPLICATION_EDIT}/${candidature.id}")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier la candidature")
                    }
                    IconButton(onClick = {
                        candidatureVm.archive(listOf(candidature.id), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Archive, contentDescription = "Archiver")
                    }

                    IconButton(onClick = {
                        candidatureVm.delete(listOf(candidature.id), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.DeleteForever, contentDescription = "Supprimer définitivement")
                    }

                }
            )
        },
        floatingActionButton = {
            AddActionButtons(
                onAddRelance   = { navController.navigate("${Routes.FOLLOWUP_ADD}?linkedCandidatureId=${candidature.id}") },
                onAddAppel     = { navController.navigate("${Routes.CALL_ADD}?linkedCandidatureId=${candidature.id}") },
                onAddEntretien = { navController.navigate("${Routes.INTERVIEW_ADD}?linkedCandidatureId=${candidature.id}") },
                onAddContact   = { navController.navigate("${Routes.CONTACT_ADD}?linkedCandidatureId=${candidature.id}") }
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
                Text("Titre : ${candidature.title}")

                Text("Entreprise : ${entreprise?.name ?: "—"}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.clickable {
                        navController.navigate("${Routes.COMPANY_DETAIL}/${candidature.companyId}")
                    }
                )
                Text(
                    text = "Date: ${candidature.applicationDate.toFormattedDate()}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Statut: ${candidature.applicationStatus}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Type de contrat: ${candidature.applicationType}",
                    style = MaterialTheme.typography.bodyMedium
                )
                candidature.platform?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Plateforme: $it", style = MaterialTheme.typography.bodySmall)
                }
                candidature.contractType?.let {
                    Text(
                        text = "Type de contrat: $it",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                candidature.location?.let {
                    Text(
                        text = "Lieu du poste: $it",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                candidature.notes?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Notes: $it", style = MaterialTheme.typography.bodySmall)
                }
            }


            item {
                SectionTitle("Relances liées")
            }
            items(myRelances) { relance ->
                DetailItemCard(
                    title = "${relance.type ?: "Type inconnu"} (${relance.responseStatus ?: "Statut inconnu"})",
                    subtitle = relance.date.toString(),
                    onClick = { navController.navigate("${Routes.FOLLOWUP_DETAIL}/${relance.id}")}
                )
            }

            item {
                SectionTitle("Appels liés")
            }
            items(myAppels) { appel ->
                DetailItemCard(
                    title = appel.subject,
                    subtitle = appel.notes,
                    onClick = { navController.navigate("${Routes.DETAIL_CALL}/${appel.id}")}
                )
            }

            item {
                SectionTitle("Entretiens liés")
            }
            items(myEntretiens) { entretienWithContacts ->
                val entretien = entretienWithContacts.entretien
                val contacts = entretienWithContacts.contacts

                DetailItemCard(
                    title = "${entretien.type ?: "Type inconnu"} - ${entretien.style ?: "Style inconnu"}",
                    subtitle = contacts.joinToString(", ") { it.firstName + " " + it.lastName },
                    onClick = { navController.navigate("${Routes.ENTRETIEN_DETAIL}/${entretien.id}") }
                )
            }

            item {
                SectionTitle("Contacts liés")
            }
            items(myContacts) { contact ->
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
    onAddRelance: () -> Unit,
    onAddAppel: () -> Unit,
    onAddEntretien: () -> Unit,
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
                SmallFloatingActionButton(onClick = { expanded = false; onAddRelance() }) {
                    Icon(Icons.Default.Alarm, contentDescription = "Ajouter Relance")
                }
                SmallFloatingActionButton(onClick = { expanded = false; onAddAppel() }) {
                    Icon(Icons.Default.Phone, contentDescription = "Ajouter Appel")
                }
                SmallFloatingActionButton(onClick = { expanded = false; onAddEntretien() }) {
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
