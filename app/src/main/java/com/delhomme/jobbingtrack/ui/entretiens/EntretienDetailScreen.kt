package com.delhomme.jobbingtrack.ui.entreprises

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Entretien
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider.contacts
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.candidatures.SectionTitle
import com.delhomme.jobbingtrack.ui.components.DetailItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntretienDetailScreen(
    entretien: Entretien,
    onBackClick: () -> Unit,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(entretien.candidatureId) },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
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
        val candidature = FakeDataProvider.candidatures.find { it.id == entretien.candidatureId }

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
                Text(text = "Type : ${entretien.type ?: "Non spécifié"}")
                Text(text = "Style  : ${entretien.style ?: "Non spécifié"}")
                Text(text = "Date et heure  : ${entretien.dateTime ?: "Non spécifié"}")
                Text(text = "Contacts  : ${entretien.contacts ?: "Non spécifié"}")
                Text(text = "Adresse : ${entretien.location ?: "Non spécifiée"}")
                Text(text = "Tests requis  : ${entretien.testsNeeded ?: "Non spécifiée"}")
                Text(text = "Deadline des test : ${entretien.testsDeadline ?: "Non spécifiée"}")
                Text(text = "Notes d'interview : ${entretien.interviewNotes ?: "Non spécifiée"}")
                Text(text = "Durée : ${entretien.durationMinutes ?: "Non spécifiée"}")
                Text(text = "Notes post entretien : ${entretien.postInterviewNotes ?: "Non spécifiée"}")
                Text(text = "Notes pré entretien : ${entretien.preInterviewNotes ?: "Non spécifiée"}")
                Text(text = "Date de retour : ${entretien.returnDate ?: "Non spécifiée"}")
            }

            item {
                candidature?.let { candidature ->
                    SectionTitle("Candidature liée")
                    DetailItemCard(
                        title = candidature.title,
                        subtitle = candidature.applicationStatus.name,
                        onClick = { navController.navigate("${Routes.CANDIDATURE_DETAIL}/${candidature.id}") }
                    )
                }
            }


            if (contacts.isNotEmpty()) {
                item { SectionTitle("Contacts liés") }
                items(contacts) { contact ->
                    DetailItemCard(
                        title = "${contact.firstName} ${contact.lastName}",
                        subtitle = contact.position ?: "Pas de fonction",
                        onClick = { navController.navigate("${Routes.CONTACT_DETAIL}/${contact.id}") }
                    )
                }
            }
        }
    }
}
