package com.delhomme.jobbingtrack.ui.major.entreprises

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
import com.delhomme.jobbingtrack.data.viewmodel.EntrepriseViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntretienViewModel
import com.delhomme.jobbingtrack.data.viewmodel.AppelViewModel
import com.delhomme.jobbingtrack.data.viewmodel.RelanceViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.major.candidatures.SectionTitle
import com.delhomme.jobbingtrack.ui.components.DetailItemCard
import com.delhomme.jobbingtrack.utils.toFormattedDate
import org.checkerframework.checker.units.qual.cd


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntrepriseDetailScreen(
    entrepriseId: String,
    navController: NavController,
    entrepriseVm: EntrepriseViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
    candidatureVm: CandidatureViewModel = viewModel(),
    entretienVm: EntretienViewModel = viewModel(),
    appelVm: AppelViewModel = viewModel(),
    relanceVm: RelanceViewModel = viewModel()
) {
    // 1) Charger l'entreprise
    val allEnts by entrepriseVm.entreprises.observeAsState(emptyList())
    val entreprise = allEnts.find { it.id == entrepriseId } ?: return

    // 2) Charger toutes les listes
    val contacts    by contactVm.contacts.observeAsState(emptyList())
    val candidatures by candidatureVm.candidatures.observeAsState(emptyList())
    val entretiens  by entretienVm.entretiens.observeAsState(emptyList())
    val appels      by appelVm.appels.observeAsState(emptyList())
    val relances    by relanceVm.relances.observeAsState(emptyList())

    // 3) Filtrer par companyId / entrepriseId
    val myContacts      = contacts.filter    { it.entrepriseId == entrepriseId }
    val myCandidatures  = candidatures.filter{ it.companyId     == entrepriseId }
    val myEntretiens    = entretiens.filter  { it.entretien.companyId == entrepriseId }
    val myAppels        = appels.filter      { it.companyId     == entrepriseId }
    val myRelances      = relances.filter    { it.companyId     == entrepriseId }

    BackHandler { navController.popBackStack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(entreprise.name) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("${Routes.EDIT_ENTREPRISE}/${entreprise.id}")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier")
                    }
                    IconButton(onClick = {
                        entrepriseVm.archive(entreprise.id)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Archive, contentDescription = "Archiver")
                    }

                    IconButton(onClick = {
                        entrepriseVm.delete(entreprise.id)
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
                Text(text = "Informations sur l'entreprise", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Type : ${entreprise.type ?: "Non spécifié"}")
                Text(text = "Téléphone : ${entreprise.phone ?: "Non spécifié"}")
                Text(text = "Email : ${entreprise.email ?: "Non spécifié"}")
                Text(text = "Email RH : ${entreprise.hrEmail ?: "Non spécifié"}")
                Text(text = "Adresse : ${entreprise.address ?: "Non spécifiée"}")
            }

            if (myContacts.isNotEmpty()) {
                item { SectionTitle("Contacts liés") }
                items(myContacts) { c ->
                    DetailItemCard(
                        title = "${c.firstName} ${c.lastName}",
                        subtitle = c.position ?: "Pas de poste",
                        onClick = { navController.navigate("${Routes.CONTACT_DETAIL}/${c.id}") }
                    )
                }
            }

            if (myCandidatures.isNotEmpty()) {
                item { SectionTitle("Candidatures liées") }
                items(myCandidatures) { cd ->
                    DetailItemCard(
                        title = cd.title,
                        subtitle = cd.applicationStatus.toString(),
                        onClick = { navController.navigate("${Routes.CANDIDATURE_DETAIL}/${cd.id}") }
                    )
                }
            }

            if (myEntretiens.isNotEmpty()) {
                item { SectionTitle("Entretiens liés") }
                items(myEntretiens) { e ->
                    DetailItemCard(
                        title = "${e.entretien.type ?: "Type inconnu"} - ${e.entretien.style ?: "Style inconnu"}",
                        subtitle = e.entretien.dateTime.toFormattedDate(),
                        onClick = { navController.navigate("${Routes.ENTRETIEN_DETAIL}/${e.entretien.id}") }
                    )
                }
            }

            if (myRelances.isNotEmpty()) {
                item { SectionTitle("Relances liées") }
                items(myRelances) { r ->
                    DetailItemCard(
                        title = r.type?.toString() ?: "Type inconnu",
                        subtitle = r.responseStatus?.toString() ?: "Statut inconnu",
                        onClick = { navController.navigate("${Routes.RELANCE_DETAIL}/${r.id}") }
                    )
                }
            }

            if (myAppels.isNotEmpty()) {
                item { SectionTitle("Appels liés") }
                items(appels) { a ->
                    DetailItemCard(
                        title = "${a.subject} - ${a.contactId}",
                        subtitle = a.dateTime.toFormattedDate(),
                        onClick = { navController.navigate("${Routes.APPEL_DETAIL}/${a.id}") }
                    )
                }
            }
        }
    }
}
