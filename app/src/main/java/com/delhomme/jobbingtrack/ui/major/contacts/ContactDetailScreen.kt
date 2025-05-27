package com.delhomme.jobbingtrack.ui.major.contacts

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
import com.delhomme.jobbingtrack.ui.major.candidatures.SectionTitle
import com.delhomme.jobbingtrack.ui.components.items.DetailItemCard
import com.delhomme.jobbingtrack.utils.toFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    contactId: String,
    navController: NavController,
    userId: String,
    contactVm: ContactViewModel = viewModel(),
    entrepriseVm: EntrepriseViewModel = viewModel(),
    candidatureVm: CandidatureViewModel = viewModel(),
    appelVm: AppelViewModel = viewModel(),
    entretienVm: EntretienViewModel = viewModel(),
    relanceVm: RelanceViewModel = viewModel()
) {
    // 1) Charger le contact
    val allContacts by contactVm.allForUser(userId = userId).observeAsState(emptyList())
    val contact    = allContacts.find { it.id == contactId } ?: return

    // 2) Charger l'entreprise associée (pour le nom)
    val allEnts     by entrepriseVm.allForUser(userId = userId).observeAsState(emptyList())
    val entreprise = allEnts.find { it.id == contact.companyId } ?: return

    // 3) Charger tous les oobjets et filtrere ceux qui concernent ce contact
    val allCands by candidatureVm.allForUser(userId = userId).observeAsState(emptyList())
    val allRelances by relanceVm.allForUser(userId = userId).observeAsState(emptyList())
    val allAppels by appelVm.allForUser(userId = userId).observeAsState(emptyList())
    val allEntretiens by entretienVm.allForUser(userId = userId).observeAsState(emptyList())

    val linkedCands = allCands.filter { c ->
        allAppels.any { it.contactId == contactId && it.candidatureId == c.id } ||
        allRelances.any { it.contactId == contactId && it.candidatureId == c.id } ||
        allEntretiens.any   { ewc ->
            ewc.contacts.any { it.id == contactId } &&
                    ewc.entretien.candidatureId == c.id
        }
    }
    val linkedAppels = allAppels.filter { it.contactId == contactId }
    val linkedEntretiens = allEntretiens.filter { ewc -> ewc.contacts.any { it.id == contactId } }
    val linkedRelances = allRelances.filter { it.contactId == contactId }


    BackHandler {
        navController.popBackStack()
    }

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
                        navController.navigate("${Routes.EDIT_CONTACT}/${contactId}")
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
                Text(text = "Informations sur le contact", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Entreprise : ${entreprise.name}", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                Text(text = "Téléphone : ${contact.phone ?: "Non spécifié"}")
                Text(text = "Email : ${contact.email ?: "Non spécifié"}")
                Text(text = "Poste : ${contact.position ?: "Non spécifié"}")
                Text(text = "Service : ${contact.department ?: "Non spécifié"}")
            }


            if (linkedCands.isNotEmpty()) {
                item { SectionTitle("Candidatures liées") }
                items(linkedCands) { c ->
                    DetailItemCard(
                        title    = c.title,
                        subtitle = c.applicationStatus.toString(),
                        onClick  = { navController.navigate("${Routes.CANDIDATURE_DETAIL}/${c.id}") }
                    )
                }
            }


            if (linkedAppels.isNotEmpty()) {
                item { SectionTitle("Appels liés") }
                items(linkedAppels) { a ->
                    DetailItemCard(
                        title    = a.subject,
                        subtitle = a.dateTime.toFormattedDate(),
                        onClick  = { navController.navigate("${Routes.APPEL_DETAIL}/${a.id}") }
                    )
                }
            }

            if (linkedEntretiens.isNotEmpty()) {
                item { SectionTitle("Entretiens liés") }
                items(linkedEntretiens) { e ->
                    DetailItemCard(
                        title = "${e.entretien.type } — ${e.entretien.style} pour ${e.entretien.companyId}",
                        subtitle = e.entretien.dateTime.toFormattedDate(),
                        onClick  = { navController.navigate("${Routes.ENTRETIEN_DETAIL}/${e.entretien.id}") }
                    )
                }
            }

            if (linkedRelances.isNotEmpty()) {
                item { SectionTitle("Relances liées") }
                items(linkedRelances) { r ->
                    DetailItemCard(
                        title    = r.type ?: "—",
                        subtitle = r.date.toFormattedDate(),
                        onClick  = { navController.navigate("${Routes.RELANCE_DETAIL}/${r.id}") }
                    )
                }
            }
        }
    }
}
