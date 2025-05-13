package com.delhomme.jobbingtrack.ui.major.contacts

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.major.candidatures.SectionTitle
import com.delhomme.jobbingtrack.ui.components.DetailItemCard
import com.delhomme.jobbingtrack.utils.toFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    contact: Contact,
    navController: NavController
) {
// Meilleur :
    val candidatures = FakeDataProvider.candidatures.filter { candidature ->
        FakeDataProvider.relances.any { relance ->
            relance.candidatureId == candidature.id && relance.contactId == contact.id
        } ||
                FakeDataProvider.appels.any { appel ->
                    appel.candidatureId == candidature.id && appel.contactId == contact.id
                } ||
                FakeDataProvider.entretiens.any { entretien ->
                    entretien.contacts.contains(contact.id)
                }
    }
    val relances = FakeDataProvider.relances.filter { it.contactId == contact.id }
    val appels = FakeDataProvider.appels.filter { it.contactId == contact.id }
    val entretiens = FakeDataProvider.entretiens.filter { it.contacts.contains(contact.id) }

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
                        navController.navigate("${Routes.EDIT_CONTACT}/${contact.id}")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier Contact")
                    }
                    IconButton(onClick = {
                        FakeDataProvider.removeContact(contact.id)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Archive, contentDescription = "Archiver")
                    }

                    IconButton(onClick = {
                        FakeDataProvider.deleteContact(contact.id)
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
                Text(text = "Téléphone : ${contact.phone ?: "Non spécifié"}")
                Text(text = "Email : ${contact.email ?: "Non spécifié"}")
                Text(text = "Poste : ${contact.position ?: "Non spécifié"}")
                Text(text = "Service : ${contact.department ?: "Non spécifié"}")
            }

            if (candidatures.isNotEmpty()) {
                item { SectionTitle("Candidatures liées") }
                items(candidatures) { candidature ->
                    DetailItemCard(
                        title = candidature.title,
                        subtitle = candidature.applicationStatus.name,
                        onClick = { navController.navigate("${Routes.CANDIDATURE_DETAIL}/${candidature.id}") }
                    )
                }
            }

            if (relances.isNotEmpty()) {
                item { SectionTitle("Relances liées") }
                items(relances) { relance ->
                    DetailItemCard(
                        title = relance.type?.name ?: "Type inconnu",
                        subtitle = relance.responseStatus?.name ?: "Statut inconnu",
                        onClick = { navController.navigate("${Routes.RELANCE_DETAIL}/${relance.id}") }
                    )
                }
            }

            if (appels.isNotEmpty()) {
                item { SectionTitle("Appels liés") }
                items(appels) { appel ->
                    DetailItemCard(
                        title = appel.subject,
                        subtitle = appel.dateTime.toFormattedDate(),
                        onClick = { navController.navigate("${Routes.APPEL_DETAIL}/${appel.id}") }
                    )
                }
            }

            if (entretiens.isNotEmpty()) {
                item { SectionTitle("Entretiens liés") }
                items(entretiens) { entretien ->
                    DetailItemCard(
                        title = "${entretien.type?.name ?: "Type inconnu"} - ${entretien.style?.name ?: "Style inconnu"}",
                        subtitle = entretien.dateTime.toFormattedDate(),
                        onClick = { navController.navigate("${Routes.ENTRETIEN_DETAIL}/${entretien.id}") }
                    )
                }
            }
        }
    }
}
