package com.delhomme.jobbingtrack.ui.major.calls

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import com.delhomme.jobbingtrack.data.viewmodel.Call.CallViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Application.ApplicationViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Contact.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Company.CompanyViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.major.applications.SectionTitle
import com.delhomme.jobbingtrack.ui.components.items.DetailItemCard
import com.delhomme.jobbingtrack.utils.toFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallDetailsScreen(
    callId: String,
    navController: NavController,
    callVm: CallViewModel = viewModel(),
    applicationVm: ApplicationViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
    companyVm: CompanyViewModel = viewModel(),
    userId: String
) {

    // 1) Charger l’appel
    val calls by callVm.allForUser(userId = userId).observeAsState(emptyList())
    val call  = calls.find { it.id == callId } ?: return

    // 2) Charger la candidature et le contact associés
    val applications       by applicationVm.allForUser(userId = userId).observeAsState(emptyList())
    val contacts    by contactVm.allForUser(userId = userId).observeAsState(emptyList())
    val companies by companyVm.allForUser(userId = userId).observeAsState(emptyList())

    val application      = applications.find       { it.id == call.applicationId }
    val contact    = contacts.find    { it.id == call.contactId }
    val company = companies.find { it.id == call.companyId }

    BackHandler {
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Détail Appel") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("${Routes.CALL_EDIT}/$callId")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier")
                    }
                    IconButton(onClick = {
                        callVm.archive(listOf(callId), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Archive, contentDescription = "Archiver")
                    }
                    IconButton(onClick = {
                        callVm.delete(listOf(callId), userId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.DeleteForever,
                            contentDescription = "Supprimer")
                    }
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
            Text("Sujet : ${call.subject}")
            Text("Date  : ${call.dateTime.toFormattedDate()}")
            Text("Entreprise : ${company?.name ?: "—"}")
            call.notes?.let { Text("Notes  : $it") }

            application?.let {
                SectionTitle("Candidature liée")
                DetailItemCard(
                    title    = it.title,
                    subtitle = it.applicationStatus.toString(),
                    onClick  = { navController.navigate("${Routes.APPLICATION_DETAIL}/${it.id}") }
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