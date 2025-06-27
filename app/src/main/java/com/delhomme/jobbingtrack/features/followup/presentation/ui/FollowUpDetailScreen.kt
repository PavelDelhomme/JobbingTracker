package com.delhomme.jobbingtrack.features.followup.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.core.utils.toFormattedDate
import com.delhomme.jobbingtrack.features.application.presentation.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.features.contact.presentation.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.features.followup.presentation.viewmodel.FollowUpViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.shared.DetailItemCard
import com.delhomme.jobbingtrack.ui.shared.SectionTitle
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FollowUpDetailScreen(
    followUpId: String,
    navController: NavController,
    userId: String,
    followUpVm: FollowUpViewModel = viewModel(),
    applicationVm: ApplicationViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel()
) {
    // 1) VM + chargement
    val allFollowUps by followUpVm.allForUser(userId).observeAsState(emptyList())
    val allApplications by applicationVm.activeForUser(userId).observeAsState(emptyList())
    val allContacts by contactVm.activeForUser(userId).observeAsState(emptyList())

    val followUp = allFollowUps.firstOrNull { it.id == followUpId } ?: return
    val application = allApplications.firstOrNull { it.id == followUp.applicationId }

    // Récupérer les contacts liés (many-to-many)
    val contactIds = runBlocking { followUpVm.getContactIdsForFollowUp(followUp.id) }
    val linkedContacts = allContacts.filter { contactIds.contains(it.id) }

    // Charger les labels des types et statuts
    val allTypes by followUpVm.getAllFollowUpTypes().observeAsState(emptyList())
    val allStatus by followUpVm.getAllFollowUpResponses().observeAsState(emptyList())
    val typeLabel = allTypes.find { it.id == followUp.typeId }?.label ?: "Non spécifié"
    val statusLabel = allStatus.find { it.id == followUp.statusId }?.label ?: "Non spécifié"


    BackHandler { navController.popBackStack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Détail Relance") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("${Routes.FOLLOWUP_EDIT}/${followUp.id}")
                    }) {
                        Icon(Icons.Default.Edit, "Modifier")
                    }
                    IconButton(onClick = {
                        followUpVm.archive(listOf(followUp.id), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.DeleteForever, "Archiver")
                    }
                    IconButton(onClick = {
                        followUpVm.delete(listOf(followUp.id.toString()), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.DeleteForever, "Supprimer définitivement")
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
            Text("Type : $typeLabel")
            Text("Statut : $statusLabel")
            Text("Date : ${followUp.date.toFormattedDate()}")
            followUp.notes?.let { Text("Notes              : $it") }

            application?.let {
                SectionTitle("Candidature liée")
                DetailItemCard(
                    title = it.title,
                    subtitle = it.applicationStatusId.toString(),
                    onClick = { navController.navigate("${Routes.APPLICATION_DETAIL}/${it.id}") }
                )
            }

            if (linkedContacts.isNotEmpty()) {
                SectionTitle("Contacts liés")
                linkedContacts.forEach { contact ->
                    DetailItemCard(
                        title = "${contact.firstName} ${contact.lastName}",
                        subtitle = contact.positionId ?: "Pas de poste",
                        onClick = { navController.navigate("${Routes.CONTACT_DETAIL}/${contact.id}") }
                    )
                }
            }
        }
    }
}