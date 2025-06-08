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
import com.delhomme.jobbingtrack.data.viewmodel.Call.CallViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Application.ApplicationViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Contact.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Company.CompanyViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Interview.InterviewViewModel
import com.delhomme.jobbingtrack.data.viewmodel.FollowUp.FollowUpViewModel
import com.delhomme.jobbingtrack.navigation.Routes

@Composable
fun ArchiveScreen(navController: NavController,
                  userId: String,
                  applicationViewModel: ApplicationViewModel,
                  companyViewModel: CompanyViewModel,
                  callViewModel: CallViewModel,
                  contactViewModel: ContactViewModel,
                  interviewViewModel: InterviewViewModel,
                  followUpViewModel: FollowUpViewModel,
                  onRestore: () -> Unit,
                  onDelete: () -> Unit
) {
    val applicationsArchived = applicationViewModel.archivedForUser(userId).observeAsState(listOf()).value
    val companiesArchived = companyViewModel.archivedForUser(userId).observeAsState(listOf()).value
    val callsArchived = callViewModel.archivedForUser(userId).observeAsState(listOf()).value
    val contactsArchived = contactViewModel.archivedForUser(userId).observeAsState(listOf()).value
    val interviewsArchived = interviewViewModel.activeForUser(userId).observeAsState(listOf()).value
        .filter { it.isArchived && !it.isDeleted }
    val followUpsArchived = followUpViewModel.archivedForUser(userId).observeAsState(listOf()).value

    val archived = applicationsArchived + companiesArchived + callsArchived + contactsArchived + interviewsArchived + followUpsArchived

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Archives", style = MaterialTheme.typography.headlineMedium)
        Text("Candidatures archivées", style = MaterialTheme.typography.headlineSmall)
        applicationsArchived.forEach {
            Text(
                text = "${it.title} (${it.applicationStatus})",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.APPLICATION_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
        Text("Entreprises archivées", style = MaterialTheme.typography.headlineSmall)
        companiesArchived.forEach {
            Text(
                text = "${it.name}",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.COMPANY_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
        Text("Appels archivées", style = MaterialTheme.typography.headlineSmall)
        callsArchived.forEach {
            Text(
                text = "${it.subject}",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.DETAIL_CALL}/${it.id}")
                }.padding(8.dp)
            )
        }
        Text("Contacts archivées", style = MaterialTheme.typography.headlineSmall)
        contactsArchived.forEach {
            Text(
                text = "${it.firstName} ${it.lastName}",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.CONTACT_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
        Text("Entretiens archivées", style = MaterialTheme.typography.headlineSmall)
        interviewsArchived.forEach {
            Text(
                text = "${it.type} ${it.style} ${it.dateTime}${it.applicationId} ${it.companyId}",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.ENTRETIEN_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
        Text("Relances archivées", style = MaterialTheme.typography.headlineSmall)
        followUpsArchived.forEach {
            Text(
                text = "${it.archivedAt} ${it.date} ${it.responseStatus} (${it.applicationId})",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.FOLLOWUP_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
    }
}
