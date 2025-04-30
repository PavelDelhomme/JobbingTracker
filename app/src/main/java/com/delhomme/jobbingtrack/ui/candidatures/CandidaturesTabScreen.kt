package com.delhomme.jobbingtrack.ui.candidatures

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.*
import com.delhomme.jobbingtrack.navigation.Routes
import com.delhomme.jobbingtrack.ui.appels.AppelsScreen
import com.delhomme.jobbingtrack.ui.contacts.ContactsScreen
import com.delhomme.jobbingtrack.ui.entreprises.EntreprisesScreen
import com.delhomme.jobbingtrack.ui.entretiens.EntretiensScreen
import com.delhomme.jobbingtrack.ui.relances.RelancesScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandidaturesTabScreen(
    navController: NavController,
    candidatures: List<Candidature>,
    entreprises: List<Entreprise>,
    relances: List<Relance>,
    appels: List<Appel>,
    contacts: List<Contact>,
    entretiens: List<Entretien>
) {
    var selectedTabIndex by rememberSaveable  { mutableStateOf(0) }
    val tabs = listOf("Candidatures", "Entreprises", "Relances", "Appels", "Contacts", "Entretiens")

    fun handleItemClick(item: Any) {
        when (item) {
            is Candidature -> {
                navController.navigate("${Routes.CANDIDATURE_DETAIL}/${item.id}")
            }
            is Entreprise -> {
                navController.navigate("${Routes.ENTREPRISE_DETAIL}/${item.id}")
            }
            is Relance -> {
                println("Clique sur relance liée à candidature ${item.candidatureId}")
                // navController.navigate("${Routes.RELANCE_DETAIL}/${item.id}") si tu as un écran
            }
            is Appel -> {
                println("Clique sur appel lié à ${item.subject}")
                // navController.navigate("${Routes.APPEL_DETAIL}/${item.id}") si tu as un écran
            }
            is Contact -> {
                navController.navigate("${Routes.CONTACT_DETAIL}/${item.id}")
            }
            is Entretien -> {
                println("Clique sur entretien de candidature ${item.candidatureId}")
                // navController.navigate("${Routes.ENTRETIEN_DETAIL}/${item.id}") si tu as un écran
            }
            else -> {
                println("Type inconnu cliqué: $item")
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 16.dp
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> CandidaturesScreen(
                candidatures = candidatures,
                onItemClick = { handleItemClick(it) },
                onAddClick = { /* handled by FAB */ }
            )
            1 -> EntreprisesScreen(
                entreprises = entreprises,
                onItemClick = { handleItemClick(it) },
                onAddClick = { /* handled by FAB */ }
            )
            2 -> RelancesScreen(
                relances = relances,
                onItemClick = { handleItemClick(it) }
            )
            3 -> AppelsScreen(
                appels = appels,
                onItemClick = { handleItemClick(it) },
                onAddClick = { /* handled by FAB */ }
            )
            4 -> ContactsScreen(
                contacts = contacts,
                onItemClick = { handleItemClick(it) },
                onAddClick = { /* handled by FAB */ }
            )
            5 -> EntretiensScreen(
                entretiens = entretiens,
                onItemClick = { handleItemClick(it) }
            )
        }
    }
}