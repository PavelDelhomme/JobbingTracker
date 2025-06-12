package com.delhomme.jobbingtrack.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.delhomme.jobbingtrack.applications.ui.ApplicationsScreen
import com.delhomme.jobbingtrack.applications.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.calls.ui.CallsScreen
import com.delhomme.jobbingtrack.calls.viewmodels.CallViewModel
import com.delhomme.jobbingtrack.companies.ui.CompaniesScreen
import com.delhomme.jobbingtrack.companies.viewmodels.CompanyViewModel
import com.delhomme.jobbingtrack.contacts.ui.ContactsScreen
import com.delhomme.jobbingtrack.followsup.viewmodels.FollowUpViewModel
import com.delhomme.jobbingtrack.interviews.viewmodels.InterviewViewModel
import com.delhomme.jobbingtrack.contacts.viewmodels.ContactViewModel
import com.delhomme.jobbingtrack.followsup.ui.FollowUpsScreen
import com.delhomme.jobbingtrack.interviews.ui.InterviewsScreen
import com.delhomme.jobbingtrack.navigation.Routes

@Composable
fun ApplicationsTabsContent(
    navController: NavHostController,
    selectedTabIndex: Int,
    onTabChange: (Int) -> Unit,
    userId: String
) {
    val applicationsVm: ApplicationViewModel = viewModel()
    val companiesVm: CompanyViewModel = viewModel()
    val followUpsVm: FollowUpViewModel     = viewModel()
    val callsVm: CallViewModel         = viewModel()
    val contactsVm: ContactViewModel     = viewModel()
    val interviewsVm: InterviewViewModel = viewModel()
    val interviewsWithContactsVm: InterviewViewModel = viewModel()

    val applications       by applicationsVm.activeForUser(userId = userId).observeAsState(emptyList())
    val companies        by companiesVm.activeForUser(userId = userId).observeAsState(emptyList())
    val followUps        by followUpsVm.activeForUser(userId = userId).observeAsState(emptyList())
    val calls      by callsVm.activeForUser(userId = userId).observeAsState(emptyList())
    val contacts    by contactsVm.activeForUser(userId = userId).observeAsState(emptyList())
    val interviews  by interviewsVm.activeForUser(userId = userId).observeAsState(emptyList())
    val interviewsWithContacts by interviewsWithContactsVm.activeWithContactsForUser(userId = userId).observeAsState(emptyList())

    BackHandler { onTabChange(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(selectedTabIndex = selectedTabIndex, edgePadding = 16.dp) {
            listOf("Candidatures","Entreprises","Relances","Appels","Contacts","Entretiens")
                .forEachIndexed { i, t ->
                    Tab(i==selectedTabIndex, onClick={onTabChange(i)}){ Text(t) }
                }
            }
        Box(modifier = Modifier.weight(1f)) {
            when (selectedTabIndex) {
                0 -> ApplicationsScreen(
                    navController = navController,
                    applicationVm = applicationsVm,
                    companies = companies,
                    onItemClick = {
                        navController.navigate("${Routes.APPLICATION_DETAIL}/${it.id}")
                    },
                    onEdit = {
                        navController.navigate("${Routes.APPLICATION_EDIT}/${it.id}")
                    },
                    onArchive = {
                        applicationsVm.archive(listOf(it.id), userId = userId)
                    },
                    onDelete = {
                        applicationsVm.delete(listOf(it.id), userId = userId)
                    },
                    onAddClick = {
                        /* handled by FAB */
                    },
                    userId = userId
                )
                1 -> CompaniesScreen (
                    companies = companies,
                    companiesVm = companiesVm,
                    onItemClick = {
                        navController.navigate("${Routes.COMPANY_DETAIL}/${it.id}")
                    },
                    onEdit = {
                        navController.navigate("${Routes.COMPANY_EDIT}/${it.id}")
                    },
                    onArchive = {
                        companiesVm.archive(listOf(it.id), userId = userId)
                    },
                    onDelete = {
                        companiesVm.delete(listOf(it.id), userId = userId)
                    },
                    onAddClick = { /* handled by FAB */ },
                )
                2 -> FollowUpsScreen    (
                    followUps = followUps,
                    followUpVm = followUpsVm,
                    onItemClick = {
                        navController.navigate("${Routes.FOLLOWUP_DETAIL}/${it.id}")
                    },
                    onEdit = {
                        navController.navigate("${Routes.FOLLOWUP_EDIT}/${it.id}")
                    },
                    onArchive = {
                        followUpsVm.archive(listOf(it.id), userId = userId)
                    },
                    onDelete = {
                        followUpsVm.delete(listOf(it.id), userId = userId)
                    },
                    onAddClick = { /* handled by FAB */ }
                )
                3 -> CallsScreen      (
                    calls = calls,
                    callsVm = callsVm,
                    onItemClick = {
                        navController.navigate("${Routes.DETAIL_CALL}/${it.id}")
                    },
                    onEdit = {
                        navController.navigate("${Routes.CALL_EDIT}/${it.id}")
                    },
                    onArchive = {
                        callsVm.archive(listOf(it.id), userId = userId)
                    },
                    onDelete = {
                        callsVm.delete(listOf(it.id), userId = userId)
                    },
                    onAddClick = { /* handled by FAB */ }
                )
                4 -> ContactsScreen    (
                    contacts = contacts,
                    contactVm = contactsVm,
                    onItemClick = {
                        navController.navigate("${Routes.CONTACT_DETAIL}/${it.id}")
                    },
                    onEdit = {
                        navController.navigate("${Routes.CONTACT_EDIT}/${it.id}")
                    },
                    onArchive = {
                        contactsVm.archive(listOf(it.id), userId = userId)
                    },
                    onDelete = {
                        contactsVm.delete(listOf(it.id), userId = userId)
                    },
                    onAddClick = { /* handled by FAB */ }
                )
                5 -> InterviewsScreen(
                    interviews = interviewsWithContacts,
                    companies = companies,
                    onItemClick = {
                        navController.navigate("${Routes.ENTRETIEN_DETAIL}/${it.interview.id}")
                    },
                    onEdit = {
                        navController.navigate("${Routes.INTERVIEW_EDIT}/${it.interview.id}")
                    },
                    onArchive = {
                        interviewsWithContactsVm.archive(listOf(it.interview.id), userId = userId)
                    },
                    onDelete = {
                        interviewsWithContactsVm.delete(listOf(it.interview.id), userId = userId)
                    }
                )
            }
        }
    }
}
