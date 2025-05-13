package com.delhomme.jobbingtrack.ui.major.relances

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Relance
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.major.candidatures.SectionTitle
import com.delhomme.jobbingtrack.ui.components.DetailItemCard
import com.delhomme.jobbingtrack.utils.toFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RelanceDetailScreen(
    relance: Relance,
    navController: NavController
) {
    val candidature = FakeDataProvider.candidatures.find { it.id == relance.candidatureId }
    val contact = FakeDataProvider.contacts.find { it.id == relance.contactId }

    BackHandler {
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Détail Relance") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("${Routes.EDIT_RELANCE}/${relance.id}")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier")
                    }
                    IconButton(onClick = {
                        FakeDataProvider.removeRelance(relance.id)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Archiver")
                    }

                    IconButton(onClick = {
                        FakeDataProvider.deleteRelance(relance.id)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.DeleteForever, contentDescription = "Supprimer définitivement")
                    }

                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Type : ${relance.type?.name ?: "Non spécifié"}")
            Text("Statut de réponse : ${relance.responseStatus?.name ?: "Non spécifié"}")
            Text("Date : ${relance.date.toFormattedDate()}")
            relance.notes?.let { Text("Notes : $it") }
            candidature?.let {
                SectionTitle("Candidature liée")
                DetailItemCard(
                    title = it.title,
                    subtitle = it.applicationStatus.name,
                    onClick = { navController.navigate("${Routes.CANDIDATURE_DETAIL}/${it.id}") }
                )
            }

            contact?.let {
                SectionTitle("Contact lié")
                DetailItemCard(
                    title = "${it.firstName} ${it.lastName}",
                    subtitle = it.position ?: "Pas de poste",
                    onClick = { navController.navigate("${Routes.CONTACT_DETAIL}/${it.id}") }
                )
            }
        }
    }
}
