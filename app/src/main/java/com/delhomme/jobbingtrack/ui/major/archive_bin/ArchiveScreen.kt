package com.delhomme.jobbingtrack.ui.major.archive_bin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import com.delhomme.jobbingtrack.data.viewmodel.AppelViewModel
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntrepriseViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntretienViewModel
import com.delhomme.jobbingtrack.data.viewmodel.RelanceViewModel
import com.delhomme.jobbingtrack.navigation.Routes

@Composable
fun ArchiveScreen(navController: NavController,
                  userId: String,
                  candidatureViewModel: CandidatureViewModel,
                  entrepriseViewModel: EntrepriseViewModel,
                  appelViewModel: AppelViewModel,
                  contactViewModel: ContactViewModel,
                  entretienViewModel: EntretienViewModel,
                  relanceViewModel: RelanceViewModel,
                  onRestore: () -> Unit,
                  onDelete: () -> Unit
) {
    val candidatureArchived = candidatureViewModel.archivedForUser(userId).observeAsState(listOf()).value
    val entrepriseArchived = entrepriseViewModel.archivedForUser(userId).observeAsState(listOf()).value
    val appelArchived = appelViewModel.archivedForUser(userId).observeAsState(listOf()).value
    val contactArchived = contactViewModel.archivedForUser(userId).observeAsState(listOf()).value
    val entretienArchived = entretienViewModel.activeForUser(userId).observeAsState(listOf()).value
        .filter { it.isArchived && !it.isDeleted }
    val relanceArchived = relanceViewModel.archivedForUser(userId).observeAsState(listOf()).value

    val archived = candidatureArchived + entrepriseArchived + appelArchived + contactArchived + entretienArchived + relanceArchived

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Archives", style = MaterialTheme.typography.headlineMedium)
        Text("Candidatures archivées", style = MaterialTheme.typography.headlineSmall)
        candidatureArchived.forEach {
            Text(
                text = "${it.title} (${it.applicationStatus})",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.CANDIDATURE_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
        Text("Entreprises archivées", style = MaterialTheme.typography.headlineSmall)
        entrepriseArchived.forEach {
            Text(
                text = "${it.name}",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.ENTREPRISE_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
        Text("Appels archivées", style = MaterialTheme.typography.headlineSmall)
        appelArchived.forEach {
            Text(
                text = "${it.subject}",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.APPEL_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
        Text("Contacts archivées", style = MaterialTheme.typography.headlineSmall)
        contactArchived.forEach {
            Text(
                text = "${it.firstName} ${it.lastName}",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.CONTACT_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
        Text("Entretiens archivées", style = MaterialTheme.typography.headlineSmall)
        entretienArchived.forEach {
            Text(
                text = "${it.type} ${it.style} ${it.dateTime}${it.candidatureId} ${it.companyId}",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.ENTRETIEN_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
        Text("Relances archivées", style = MaterialTheme.typography.headlineSmall)
        relanceArchived.forEach {
            Text(
                text = "${it.archivedAt} ${it.date} ${it.responseStatus} (${it.candidatureId})",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.RELANCE_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
    }
}
