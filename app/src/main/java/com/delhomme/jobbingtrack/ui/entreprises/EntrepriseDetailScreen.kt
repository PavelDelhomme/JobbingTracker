package com.delhomme.jobbingtrack.ui.entreprises

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Entreprise
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.candidatures.SectionTitle
import com.delhomme.jobbingtrack.ui.components.DetailItemCard
import com.delhomme.jobbingtrack.utils.toFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntrepriseDetailScreen(
    entreprise: Entreprise,
    navController: NavController
) {
    val contacts = FakeDataProvider.contacts.filter { it.entrepriseId == entreprise.id }
    val candidatures = FakeDataProvider.candidatures.filter { it.companyId == entreprise.id }
    val entretiens = FakeDataProvider.entretiens.filter { it.companyId == entreprise.id }
    val appels = FakeDataProvider.appels.filter { it.companyId == entreprise.id }
    val relances = FakeDataProvider.relances.filter { it.companyId == entreprise.id }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(entreprise.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("edit_page_route")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier")
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
                Text(text = "Informations sur l'entreprise", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Type : ${entreprise.type ?: "Non spécifié"}")
                Text(text = "Téléphone : ${entreprise.phone ?: "Non spécifié"}")
                Text(text = "Email : ${entreprise.email ?: "Non spécifié"}")
                Text(text = "Email RH : ${entreprise.hrEmail ?: "Non spécifié"}")
                Text(text = "Adresse : ${entreprise.address ?: "Non spécifiée"}")
            }

            if (contacts.isNotEmpty()) {
                item { SectionTitle("Contacts liés") }
                items(contacts) { contact ->
                    DetailItemCard(
                        title = "${contact.firstName} ${contact.lastName}",
                        subtitle = contact.position ?: "Pas de poste",
                        onClick = { navController.navigate("${Routes.CONTACT_DETAIL}/${contact.id}") }
                    )
                }
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

            if (entretiens.isNotEmpty()) {
                item { SectionTitle("Entretiens liés") }
                items(entretiens) { entretien ->
                    DetailItemCard(
                        title = "${entretien.type.name} - ${entretien.style.name}",
                        subtitle = entretien.dateTime.toFormattedDate(),
                        onClick = { navController.navigate("${Routes.ENTRETIEN_DETAIL}/${entretien.id}") }
                    )
                }
            }

            if (relances.isNotEmpty()) {
                item { SectionTitle("Relances liées") }
                items(relances) { relance ->
                    DetailItemCard(
                        title = relance.type.name,
                        subtitle = relance.responseStatus.name,
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
        }
    }
}
