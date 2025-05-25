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
import com.delhomme.jobbingtrack.data.viewmodel.AppelViewModel
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntrepriseViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntretienViewModel
import com.delhomme.jobbingtrack.data.viewmodel.RelanceViewModel
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.major.appels.AppelsScreen
import com.delhomme.jobbingtrack.ui.major.candidatures.CandidaturesScreen
import com.delhomme.jobbingtrack.ui.major.contacts.ContactsScreen
import com.delhomme.jobbingtrack.ui.major.entreprises.EntreprisesScreen
import com.delhomme.jobbingtrack.ui.major.entretiens.EntretiensScreen
import com.delhomme.jobbingtrack.ui.major.relances.RelancesScreen

@Composable
fun CandidaturesTabsContent(
    navController: NavHostController,
    selectedTabIndex: Int,
    onTabChange: (Int) -> Unit,
    userId: String
) {
    val candidatureVm: CandidatureViewModel = viewModel()
    val entreprisesVm: EntrepriseViewModel = viewModel()
    val relancesVm: RelanceViewModel     = viewModel()
    val appelsVm: AppelViewModel         = viewModel()
    val contactsVm: ContactViewModel     = viewModel()
    //val entretiensVm: EntretienViewModel = viewModel()
    val entretiensWithContactsVm: EntretienViewModel = viewModel()

    val cands       by candidatureVm.activeForUser(userId = userId).observeAsState(emptyList())
    val ents        by entreprisesVm.activeForUser(userId = userId).observeAsState(emptyList())
    val rels        by relancesVm.activeForUser(userId = userId).observeAsState(emptyList())
    val appels      by appelsVm.activeForUser(userId = userId).observeAsState(emptyList())
    val contacts    by contactsVm.activeForUser(userId = userId).observeAsState(emptyList())
    val entretiens  by entretiensVm.activeForUser(userId = userId).observeAsState(emptyList())

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
                0 -> CandidaturesScreen(
                    navController = navController,
                    candidatures = cands,
                    entreprises = ents,
                    onItemClick = {
                        navController.navigate("${Routes.CANDIDATURE_DETAIL}/${it.id}")
                    },
                    onEdit = {
                        navController.navigate("${Routes.EDIT_CANDIDATURE}/${it.id}")
                    },
                    onArchive = {
                        candidatureVm.archive(it.id, userId = userId)
                    },
                    onDelete = {
                        candidatureVm.delete(it.id, userId = userId)
                    },
                    onAddClick = {
                        /* handled by FAB */
                    }
                )
                1 -> EntreprisesScreen (
                    entreprises = ents,
                    entreprisesVm = entreprisesVm,
                    onItemClick = {
                        navController.navigate("${Routes.ENTREPRISE_DETAIL}/${it.id}")
                    },
                    onEdit = {
                        navController.navigate("${Routes.EDIT_ENTREPRISE}/${it.id}")
                    },
                    onArchive = {
                        entreprisesVm.archive(it.id, userId = userId)
                    },
                    onDelete = {
                        entreprisesVm.delete(it.id, userId = userId)
                    },
                    onAddClick = { /* handled by FAB */ },
                )
                2 -> RelancesScreen    (
                    relances = rels,
                    relancesVm = relancesVm,
                    onItemClick = {
                        navController.navigate("${Routes.RELANCE_DETAIL}/${it.id}")
                    },
                    onEdit = {
                        navController.navigate("${Routes.EDIT_RELANCE}/${it.id}")
                    },
                    onArchive = {
                        relancesVm.archive(it.id, userId = userId)
                    },
                    onDelete = {
                        relancesVm.delete(it.id, userId = userId)
                    },
                    onAddClick = { /* handled by FAB */ }
                )
                3 -> AppelsScreen      (
                    appels = appels,
                    appelsVm = appelsVm,
                    onItemClick = {
                        navController.navigate("${Routes.APPEL_DETAIL}/${it.id}")
                    },
                    onEdit = {
                        navController.navigate("${Routes.EDIT_APPEL}/${it.id}")
                    },
                    onArchive = {
                        appelsVm.archive(it.id, userId = userId)
                    },
                    onDelete = {
                        appelsVm.delete(it.id, userId = userId)
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
                        navController.navigate("${Routes.EDIT_CONTACT}/${it.id}")
                    },
                    onArchive = {
                        contactsVm.archive(it.id, userId = userId)
                    },
                    onDelete = {
                        contactsVm.delete(it.id, userId = userId)
                    },
                    onAddClick = { /* handled by FAB */ }
                )
                5 -> EntretiensScreen  (
                    entretiens = entretiens,
                    entretiensVm = entretiensVm,
                    onItemClick = {
                        navController.navigate("${Routes.ENTRETIEN_DETAIL}/${it.entretien.id}")
                    },
                    onEdit = {
                        navController.navigate("${Routes.EDIT_CONTACT}/${it.id}")
                    },
                    onArchive = {
                        contactsVm.archive(it.id, userId = userId)
                    },
                    onDelete = {
                        contactsVm.delete(it.id, userId = userId)
                    },
                )
            }
        }
    }
}
