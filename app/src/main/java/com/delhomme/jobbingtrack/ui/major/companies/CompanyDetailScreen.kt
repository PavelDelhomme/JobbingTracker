package com.delhomme.jobbingtrack.ui.major.companies

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.viewmodel.CompanyViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ApplicationViewModel
import com.delhomme.jobbingtrack.data.viewmodel.InterviewViewModel
import com.delhomme.jobbingtrack.data.viewmodel.CallViewModel
import com.delhomme.jobbingtrack.data.viewmodel.FollowUpViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.major.applications.SectionTitle
import com.delhomme.jobbingtrack.ui.components.items.DetailItemCard
import com.delhomme.jobbingtrack.utils.toFormattedDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyDetailScreen(
    companyId: String,
    navController: NavController,
    userId: String,
    entrepriseVm: CompanyViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
    candidatureVm: ApplicationViewModel = viewModel(),
    entretienVm: InterviewViewModel = viewModel(),
    appelVm: CallViewModel = viewModel(),
    relanceVm: FollowUpViewModel = viewModel()
) {
    // 1) Charger l’entreprise en question
    val allEntreprises by entrepriseVm.allForUser(userId).observeAsState(emptyList())
    val entreprise = allEntreprises.find { it.id == companyId } ?: return

    // 2) Charger tous les objets liés à l’entreprise
    val allContacts     by contactVm.allForUser(userId).observeAsState(emptyList())
    val allCandidatures by candidatureVm.allForUser(userId).observeAsState(emptyList())
    val allEntretiens   by entretienVm.allForUser(userId).observeAsState(emptyList())
    val allAppels       by appelVm.allForUser(userId).observeAsState(emptyList())
    val allRelances     by relanceVm.allForUser(userId).observeAsState(emptyList())

    // 3) Filtrer les objets liés à cette entreprise
    val linkedContacts     = allContacts.filter     { it.companyId == companyId }
    val linkedCandidatures = allCandidatures.filter { it.companyId == companyId }
    val linkedEntretiens   = allEntretiens.filter   { it.entretien.companyId == companyId }
    val linkedAppels       = allAppels.filter       { it.companyId == companyId }
    val linkedRelances     = allRelances.filter     { it.companyId == companyId }
    BackHandler { navController.popBackStack() }

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
                        navController.navigate("${Routes.COMPANY_EDIT}/${entreprise.id}")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier")
                    }
                    IconButton(onClick = {
                        entrepriseVm.archive(listOf(entreprise.id), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Archive, contentDescription = "Archiver")
                    }
                    IconButton(onClick = {
                        entrepriseVm.delete(listOf(entreprise.id), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.DeleteForever, contentDescription = "Supprimer")
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
            // Bloc info entreprise
            item {
                Text("Informations sur l'entreprise", style = MaterialTheme.typography.headlineSmall)
                Spacer(Modifier.height(8.dp))
                Text("Type : ${entreprise.type ?: "Non spécifié"}")
                Text("Téléphone : ${entreprise.phone ?: "Non spécifié"}")
                Text("Email : ${entreprise.email ?: "Non spécifié"}")
                Text("Email RH : ${entreprise.hrEmail ?: "Non spécifié"}")
                Text("Adresse : ${entreprise.address ?: "Non spécifiée"}")
            }


            // Contacts
            if (linkedContacts.isNotEmpty()) {
                item { SectionTitle("Contacts liés") }
                items(linkedContacts) { c ->
                    DetailItemCard(
                        title = "${c.firstName} ${c.lastName}",
                        subtitle = c.position ?: "Aucun poste défini",
                        onClick = { navController.navigate("${Routes.CONTACT_DETAIL}/${c.id}") }
                    )
                }
            }

            // Candidatures
            if (linkedCandidatures.isNotEmpty()) {
                item { SectionTitle("Candidatures liées") }
                items(linkedCandidatures) { c ->
                    DetailItemCard(
                        title = c.title,
                        subtitle = c.applicationStatus.toString(),
                        onClick = { navController.navigate("${Routes.APPLICATION_DETAIL}/${c.id}") }
                    )
                }
            }

            // Entretiens
            if (linkedEntretiens.isNotEmpty()) {
                item { SectionTitle("Entretiens liés") }
                items(linkedEntretiens) { ewc ->
                    val e = ewc.entretien
                    DetailItemCard(
                        title = "${e.type ?: "Type inconnu"} - ${e.style ?: "Style inconnu"}",
                        subtitle = e.dateTime.toFormattedDate(),
                        onClick = { navController.navigate("${Routes.ENTRETIEN_DETAIL}/${e.id}") }
                    )
                }
            }

            // Relances
            if (linkedRelances.isNotEmpty()) {
                item { SectionTitle("Relances liées") }
                items(linkedRelances) { r ->
                    DetailItemCard(
                        title = r.type ?: "Type inconnu",
                        subtitle = r.responseStatus ?: "Statut inconnu",
                        onClick = { navController.navigate("${Routes.FOLLOWUP_DETAIL}/${r.id}") }
                    )
                }
            }

            // Appels
            if (linkedAppels.isNotEmpty()) {
                item { SectionTitle("Appels liés") }
                items(linkedAppels) { a ->
                    DetailItemCard(
                        title = a.subject,
                        subtitle = a.dateTime.toFormattedDate(),
                        onClick = { navController.navigate("${Routes.DETAIL_CALL}/${a.id}") }
                    )
                }
            }
        }
    }
}