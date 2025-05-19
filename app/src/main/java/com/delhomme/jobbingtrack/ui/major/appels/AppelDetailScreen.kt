package com.delhomme.jobbingtrack.ui.major.appels

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Appel
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.viewmodel.AppelViewModel
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntrepriseViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.major.candidatures.SectionTitle
import com.delhomme.jobbingtrack.ui.components.DetailItemCard
import com.delhomme.jobbingtrack.utils.toFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppelDetailScreen(
    appelId: String,
    navController: NavController,
    appelVm: AppelViewModel = viewModel(),
    candidatureVm: CandidatureViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
    entrepriseVm: EntrepriseViewModel = viewModel(),
) {

    // 1) Charger l’appel
    val allAppels by appelVm.appels.observeAsState(emptyList())
    val appel    = allAppels.find { it.id == appelId } ?: return

    // 2) Charger la candidature et le contact associés
    val allCands by candidatureVm.candidatures.observeAsState(emptyList())
    val allContacts by contactVm.contacts.observeAsState(emptyList())
    val candi   = allCands.find { it.id == appel.candidatureId }
    val contact = allContacts.find { it.id == appel.contactId }


    // 3) Charger l’entreprise pour le nom
    val allEnts by entrepriseVm.entreprises.observeAsState(emptyList())
    val entreprise = allEnts.find { it.id == appel.companyId }

    BackHandler {
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Détail Appel") },
                navigationIcon = {
                    IconButton { navController.popBackStack() }
                    { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Retour") }
                },
                actions = {
                    IconButton {
                        navController.navigate("${Routes.EDIT_APPEL}/$appelId")
                    } { Icon(Icons.Default.Edit, "Modifier") }
                    IconButton {
                        appelVm.archive(appelId)
                        navController.popBackStack()
                    } { Icon(Icons.Default.Archive, "Archiver") }
                    IconButton {
                        appelVm.delete(appelId)
                        navController.popBackStack()
                    } { Icon(Icons.Default.DeleteForever, "Supprimer") }
                }
            )
        }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Sujet : ${appel.subject}")
            Text("Date  : ${appel.dateTime.toFormattedDate()}")
            Text("Entreprise : ${entreprise?.name ?: "—"}")
            appel.notes?.let { Text("Notes  : $it") }

            candi?.let {
                SectionTitle("Candidature liée")
                DetailItemCard(
                    title    = it.title,
                    subtitle = it.applicationStatus.name,
                    onClick  = { navController.navigate("${Routes.CANDIDATURE_DETAIL}/${it.id}") }
                )
            }
            contact?.let {
                SectionTitle("Contact lié")
                DetailItemCard(
                    title    = "${it.firstName} ${it.lastName}",
                    subtitle = it.position ?: "—",
                    onClick  = { navController.navigate("${Routes.CONTACT_DETAIL}/${it.id}") }
                )
            }
        }
    }
}