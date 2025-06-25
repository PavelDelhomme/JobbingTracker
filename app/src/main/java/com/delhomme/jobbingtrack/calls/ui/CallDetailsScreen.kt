package com.delhomme.jobbingtrack.calls.ui


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
import com.delhomme.jobbingtrack.applications.ui.SectionTitle
import com.delhomme.jobbingtrack.applications.vms.ApplicationStatusViewModel
import com.delhomme.jobbingtrack.applications.vms.ApplicationViewModel
import com.delhomme.jobbingtrack.calls.vms.CallViewModel
import com.delhomme.jobbingtrack.commons.ui.items.DetailItemCard
import com.delhomme.jobbingtrack.companies.vms.CompanyViewModel
import com.delhomme.jobbingtrack.contacts.vms.ContactViewModel
import com.delhomme.jobbingtrack.contacts.vms.PositionTypeViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.utils.toFormattedDate
import kotlin.collections.find

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallDetailsScreen(
    callId: String,
    navController: NavController,
    callVm: CallViewModel = viewModel(),
    applicationVm: ApplicationViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
    companyVm: CompanyViewModel = viewModel(),
    applicationStatusVm: ApplicationStatusViewModel = viewModel(),
    positionTypeVm: PositionTypeViewModel = viewModel(),
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
    val positionTypes = positionTypeVm.all.observeAsState(emptyList()).value
    val applicationStatuses = applicationStatusVm.all.observeAsState(emptyList()).value
    val contact    = contacts.find    { it.id == call.contactId }
    val company = companies.find { it.id == call.companyId }

    val statusLabel = applicationStatuses.find { it.id == application?.applicationStatusId }?.label ?: "—"
    val positionLabel = positionTypes.find { it.id == contact?.positionId }?.label ?: "—"

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
                    title = it.title,
                    subtitle = statusLabel,
                    onClick = { navController.navigate("${Routes.APPLICATION_DETAIL}/${it.id}") }
                )
            }
            contact?.let {
                SectionTitle("Contact lié")
                DetailItemCard(
                    title = "${it.firstName} ${it.lastName}",
                    subtitle = positionLabel,
                    onClick = { navController.navigate("${Routes.CONTACT_DETAIL}/${it.id}") }
                )
            }
        }
    }
}