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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Candidature
import com.delhomme.jobbingtrack.data.classes.Relance
import com.delhomme.jobbingtrack.data.classes.Appel
import com.delhomme.jobbingtrack.data.classes.Entretien
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.components.DetailItemCard
import com.delhomme.jobbingtrack.utils.toFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandidatureDetailScreen(
    candidature: Candidature,
    relances: List<Relance>,
    appels: List<Appel>,
    entretiens: List<Entretien>,
    contacts: List<Contact>,
    onAddRelance: (String) -> Unit,
    onAddAppel: (String) -> Unit,
    onAddEntretien: (String) -> Unit,
    onAddContact: (String) -> Unit,
    navController: NavController
) {

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
                        FakeDataProvider.removeCandidature(candidature.id)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Archive, contentDescription = "Archiver")
                    }

                    IconButton(onClick = {
                        FakeDataProvider.deleteCandidature(candidature.id)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.DeleteForever, contentDescription = "Supprimer définitivement")
                    }

                }
            )
        },
        floatingActionButton = {
            AddActionButtons(
                onAddRelance = { onAddRelance(candidature.id) },
                onAddAppel = { onAddAppel(candidature.id) },
                onAddEntretien = { onAddEntretien(candidature.id) },
                onAddContact = { onAddContact(candidature.id) }
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
                Text(
                    text = "Entreprise: ${candidature.companyName}",
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
                    text = "Statut: ${candidature.applicationStatus.name}",
                    style = MaterialTheme.typography.bodyMedium
                )
                candidature.notes?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Notes: $it", style = MaterialTheme.typography.bodySmall)
                }
            }

            item {
                SectionTitle("Relances liées")
            }
            items(relances) { relance ->
                DetailItemCard(
                    title = "${relance.type?.name ?: "Type inconnu"} (${relance.responseStatus?.name ?: "Statut inconnu"})",
                    subtitle = relance.date.toString(),
                    onClick = { navController.navigate("${Routes.RELANCE_DETAIL}/${relance.id}")}
                )
            }

            item {
                SectionTitle("Appels liés")
            }
            items(appels) { appel ->
                DetailItemCard(
                    title = appel.subject,
                    subtitle = appel.notes,
                    onClick = { navController.navigate("${Routes.APPEL_DETAIL}/${appel.id}")}
                )
            }

            item {
                SectionTitle("Entretiens liés")
            }
            items(entretiens) { entretien ->
                DetailItemCard(
                    title = "${entretien.type?.name ?: "Type inconnu"} - ${entretien.style?.name ?: "Style inconnu"}",
                    subtitle = "${entretien.contacts}",
                    onClick = { navController.navigate("${Routes.ENTRETIEN_DETAIL}/${entretien.id}")}
                )
            }

            item {
                SectionTitle("Contacts liés")
            }
            items(contacts) { contact ->
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
