package com.delhomme.jobbingtrack.ui.major.candidatures

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
import com.delhomme.jobbingtrack.data.viewmodel.AppelViewModel
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntrepriseViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntretienViewModel
import com.delhomme.jobbingtrack.data.viewmodel.RelanceViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.components.DetailItemCard
import com.delhomme.jobbingtrack.utils.toFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandidatureDetailScreen(
    candidatureId: String,
    navController: NavController,
    candidatureVm: CandidatureViewModel = viewModel(),
    relanceVm: RelanceViewModel = viewModel(),
    appelVm: AppelViewModel = viewModel(),
    entretienVm: EntretienViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
    entrepriseVm: EntrepriseViewModel = viewModel()
) {
    // 1) Charger la candidature
    val allCands by candidatureVm.candidatures.observeAsState(emptyList())
    val candidature = allCands.find { it.id == candidatureId }
        ?: return // ou u petit loader / message d'erreur

    // 2) Charger l’entreprise
    val allEnts     by entrepriseVm.entreprises.observeAsState(emptyList())
    val entreprise  = allEnts.find { it.id == candidature.companyId }

    // 2) Charger toutess les entités liées
    val relances    by relanceVm.relances.observeAsState(emptyList())
    val appels      by appelVm.appels.observeAsState(emptyList())
    val entretiens  by entretienVm.entretiens.observeAsState(emptyList())
    val contacts    by contactVm.contacts.observeAsState(emptyList())

    // 3) Filtrer celles qui concernent notre candidature
    val myRelances   = relances.filter { it.candidatureId == candidatureId }
    val myAppels     = appels.filter { it.candidatureId == candidatureId }
    val myEntretiens = entretiens.filter { it.entretien.candidatureId == candidatureId }
    // les contacts qu'on a associés via appels / relances / entretiens :
    val myContacts = contacts.filter { c ->
        myRelances.any { it.contactId == c.id } ||
        myAppels.any { it.contactId == c.id } ||
        myEntretiens.any { it.contacts.contains(c) }
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
                        navController.navigate("${Routes.EDIT_CANDIDATURE}/${candidature.id}")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier la candidature")
                    }
                    IconButton(onClick = {
                        candidatureVm.archive(candidature.id)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Archive, contentDescription = "Archiver")
                    }

                    IconButton(onClick = {
                        candidatureVm.delete(candidature.id)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.DeleteForever, contentDescription = "Supprimer définitivement")
                    }

                }
            )
        },
        floatingActionButton = {
            AddActionButtons(
                onAddRelance   = { navController.navigate("${Routes.ADD_RELANCE}?linkedCandidatureId=${candidature.id}") },
                onAddAppel     = { navController.navigate("${Routes.ADD_APPEL}?linkedCandidatureId=${candidature.id}") },
                onAddEntretien = { navController.navigate("${Routes.ADD_ENTRETIEN}?linkedCandidatureId=${candidature.id}") },
                onAddContact   = { navController.navigate("${Routes.ADD_CONTACT}?linkedCandidatureId=${candidature.id}") }
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
                        navController.navigate("${Routes.ENTREPRISE_DETAIL}/${candidature.companyId}")
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
                    onClick = { navController.navigate("${Routes.RELANCE_DETAIL}/${relance.id}")}
                )
            }

            item {
                SectionTitle("Appels liés")
            }
            items(myAppels) { appel ->
                DetailItemCard(
                    title = appel.subject,
                    subtitle = appel.notes,
                    onClick = { navController.navigate("${Routes.APPEL_DETAIL}/${appel.id}")}
                )
            }

            item {
                SectionTitle("Entretiens liés")
            }
            items(myEntretiens) { entretien ->
                DetailItemCard(
                    title = "${entretien.entretien.type ?: "Type inconnu"} - ${entretien.entretien.style ?: "Style inconnu"}",
                    subtitle = "${entretien.contacts}",
                    onClick = { navController.navigate("${Routes.ENTRETIEN_DETAIL}/${entretien.entretien.id}")}
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
