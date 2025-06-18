package com.delhomme.jobbingtrack.archives.ui

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
import com.delhomme.jobbingtrack.datas.viewmodels.ApplicationStatusViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.ApplicationTypeViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.CallViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.CompanyViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.ContactViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.FollowUpStatusViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.FollowUpTypeViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.FollowUpViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.InterviewStyleViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.InterviewTypeViewModel
import com.delhomme.jobbingtrack.datas.viewmodels.InterviewViewModel
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
                  applicationsStatusVm: ApplicationStatusViewModel,
                  applicationsTypeVm: ApplicationTypeViewModel,
                  interviewsStyleVm: InterviewStyleViewModel,
                  interviewsTypeVm: InterviewTypeViewModel,
                  followUpTypeVm: FollowUpTypeViewModel,
                  followUpStatusVm: FollowUpStatusViewModel,
                  onClear: () -> Unit,
                  onRestore: () -> Unit,
                  onDelete: () -> Unit
) {
    val applicationsArchived = applicationViewModel.archivedForUser(userId).observeAsState(listOf()).value
    val companiesArchived = companyViewModel.archivedForUser(userId).observeAsState(listOf()).value
    val callsArchived = callViewModel.archivedForUser(userId).observeAsState(listOf()).value
    val contactsArchived = contactViewModel.archivedForUser(userId).observeAsState(listOf()).value
    val interviewsArchived = interviewViewModel.allForUser(userId).observeAsState(listOf()).value
        .filter { it.interview.base.isArchived && !it.interview.base.isDeleted }
    val followUpsArchived = followUpViewModel.archivedForUser(userId).observeAsState(listOf()).value

    val archived = applicationsArchived + companiesArchived + callsArchived + contactsArchived + interviewsArchived + followUpsArchived

    val applicationStatuses = applicationsStatusVm.all.observeAsState(emptyList()).value
    val applicationTypes = applicationsTypeVm.all.observeAsState(emptyList()).value
    val interviewStyles = interviewsStyleVm.all.observeAsState(emptyList()).value
    val interviewTypes = interviewsTypeVm.all.observeAsState(emptyList()).value
    val followUpTypes = followUpTypeVm.all.observeAsState(emptyList()).value
    val followUpStatuses = followUpStatusVm.all.observeAsState(emptyList()).value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Archives", style = MaterialTheme.typography.headlineMedium)
        Text("Candidatures archivées", style = MaterialTheme.typography.headlineSmall)
        applicationsArchived.forEach {
            val statusLabel = applicationStatuses.find { status -> status.id == it.applicationStatusId }?.label ?: "—"
            val typeLabel = applicationTypes.find { type -> type.id == it.applicationTypeId }?.label ?: "Type inconnu"
            Text(
                text = "${it.title} ($statusLabel) ($typeLabel)",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.APPLICATION_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
        Text("Entreprises archivées", style = MaterialTheme.typography.headlineSmall)
        companiesArchived.forEach {
            Text(
                text = it.name,
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.COMPANY_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
        Text("Appels archivées", style = MaterialTheme.typography.headlineSmall)
        callsArchived.forEach {
            Text(
                text = it.subject,
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
            val typeLabel = interviewTypes.find { t -> t.id == it.interview.typeId }?.label ?: "Type inconnu"
            val styleLabel = interviewStyles.find { s -> s.id == it.interview.styleId }?.label ?: "Style inconnu"
            Text(
                text = "$typeLabel $styleLabel ${it.interview.dateTime}${it.interview.applicationId} ${it.interview.companyId}",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.ENTRETIEN_DETAIL}/${it.interview.id}")
                }.padding(8.dp)
            )
        }
        Text("Relances archivées", style = MaterialTheme.typography.headlineSmall)
        followUpsArchived.forEach {
            val statusLabel = followUpStatuses.find { s -> s.id == it.statusId }?.label ?: "Statut inconnu"
            val typeLabel = followUpTypes.find { t -> t.id == it.typeId }?.label ?: "Type inconnu"
            Text(
                text = "${it.base.archivedAt} ${it.date} $statusLabel $typeLabel (${it.applicationId})",
                modifier = Modifier.clickable {
                    navController.navigate("${Routes.FOLLOWUP_DETAIL}/${it.id}")
                }.padding(8.dp)
            )
        }
    }
}
