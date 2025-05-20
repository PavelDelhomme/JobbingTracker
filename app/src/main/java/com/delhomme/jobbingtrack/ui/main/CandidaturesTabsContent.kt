package com.delhomme.jobbingtrack.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
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
    onTabChange: (Int) -> Unit
) {
    val candidatureVm: CandidatureViewModel = viewModel()
    val entreprisesVm: EntrepriseViewModel = viewModel()
    val relanceVm: RelanceViewModel     = viewModel()
    val appelVm: AppelViewModel         = viewModel()
    val contactVm: ContactViewModel     = viewModel()
    val entretienVm: EntretienViewModel = viewModel()

    val cands       by candidatureVm.candidatures.observeAsState(emptyList())
    val ents        by entreprisesVm.entreprises .observeAsState(emptyList())
    val rels        by relanceVm.relances     .observeAsState(emptyList())
    val appels      by appelVm.appels         .observeAsState(emptyList())
    val contacts    by contactVm.contacts     .observeAsState(emptyList())
    val entretiens  by entretienVm.entretiens .observeAsState(emptyList())

    BackHandler { onTabChange(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 16.dp
        ) {
            listOf("Candidatures","Entreprises","Relances","Appels","Contacts","Entretiens")
                .forEachIndexed { i, t ->
                    Tab(i==selectedTabIndex, onClick={onTabChange(i)}){ Text(t) }
                }
            }
    }
    when (selectedTabIndex) {
        0 -> CandidaturesScreen(candidatures = cands, onItemClick = { navController.navigate("${Routes.CANDIDATURE_DETAIL}/${it.id}") }, onAddClick = { /* handled by FAB */ })
        1 -> EntreprisesScreen (entreprises = ents, onItemClick = { navController.navigate("${Routes.ENTREPRISE_DETAIL}/${it.id}") }, onAddClick = { /* handled by FAB */ })
        2 -> RelancesScreen    (relances = rels, onItemClick = { navController.navigate("${Routes.RELANCE_DETAIL}/${it.id}") })
        3 -> AppelsScreen      (appels = appels, onItemClick = { navController.navigate("${Routes.APPEL_DETAIL}/${it.id}") }, onAddClick = { /* handled by FAB */ })
        4 -> ContactsScreen    (contacts = contacts, onItemClick = { navController.navigate("${Routes.CONTACT_DETAIL}/${it.id}") }, onAddClick = { /* handled by FAB */ })
        5 -> EntretiensScreen  (entretiens = entretiens, onItemClick = { navController.navigate("${Routes.ENTRETIEN_DETAIL}/${it.id}") })
    }
}
