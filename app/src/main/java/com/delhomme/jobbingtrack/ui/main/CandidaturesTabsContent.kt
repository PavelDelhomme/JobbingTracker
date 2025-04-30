package com.delhomme.jobbingtrack.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.appels.AppelsScreen
import com.delhomme.jobbingtrack.ui.candidatures.CandidaturesScreen
import com.delhomme.jobbingtrack.ui.contacts.ContactsScreen
import com.delhomme.jobbingtrack.ui.entreprises.EntreprisesScreen
import com.delhomme.jobbingtrack.ui.entretiens.EntretiensScreen
import com.delhomme.jobbingtrack.ui.relances.RelancesScreen

@Composable
fun CandidaturesTabsContent(
    navController: NavHostController,
    selectedTabIndex: Int,
    onTabChange: (Int) -> Unit
) {
    navController.popBackStack()
    val tabs = listOf("Candidatures", "Entreprises", "Relances", "Appels", "Contacts", "Entretiens")

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 16.dp
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabChange(index) },
                    text = { Text(title) }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> CandidaturesScreen(
                candidatures = FakeDataProvider.candidatures,
                onItemClick = {
                    navController.navigate("${Routes.CANDIDATURE_DETAIL}/${it.id}")
                },
                onAddClick = { /* handled by FAB */ }
            )
            1 -> EntreprisesScreen(
                entreprises = FakeDataProvider.entreprises,
                onItemClick = {
                    navController.navigate("${Routes.ENTREPRISE_DETAIL}/${it.id}")
                },
                onAddClick = { /* handled by FAB */ }
            )
            2 -> RelancesScreen(
                relances = FakeDataProvider.relances,
                onItemClick = {
                    navController.navigate("${Routes.RELANCE_DETAIL}/${it.id}")
                }
            )
            3 -> AppelsScreen(
                appels = FakeDataProvider.appels,
                onItemClick = {
                    navController.navigate("${Routes.APPEL_DETAIL}/${it.id}")
                },
                onAddClick = { /* handled by FAB */ }
            )
            4 -> ContactsScreen(
                contacts = FakeDataProvider.contacts,
                onItemClick = {
                    navController.navigate("${Routes.CONTACT_DETAIL}/${it.id}")
                },
                onAddClick = { /* handled by FAB */ }
            )
            5 -> EntretiensScreen(
                entretiens = FakeDataProvider.entretiens,
                onItemClick = {
                    navController.navigate("${Routes.ENTRETIEN_DETAIL}/${it.id}")
                }
            )
        }
    }
}
