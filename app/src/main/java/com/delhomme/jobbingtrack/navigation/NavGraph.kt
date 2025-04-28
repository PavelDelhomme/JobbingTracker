package com.delhomme.jobbingtrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.ui.appels.AddOrEditAppelScreen
import com.delhomme.jobbingtrack.ui.appels.AppelDetailScreen
import com.delhomme.jobbingtrack.ui.appels.AppelsScreen
import com.delhomme.jobbingtrack.ui.candidatures.AddOrEditCandidatureScreen
import com.delhomme.jobbingtrack.ui.candidatures.CandidatureDetailScreen
import com.delhomme.jobbingtrack.ui.candidatures.CandidaturesScreen
import com.delhomme.jobbingtrack.ui.components.BottomSheetContentType
import com.delhomme.jobbingtrack.ui.contacts.AddOrEditContactScreen
import com.delhomme.jobbingtrack.ui.contacts.ContactDetailScreen
import com.delhomme.jobbingtrack.ui.contacts.ContactsScreen
import com.delhomme.jobbingtrack.ui.entreprises.AddOrEditEntrepriseScreen
import com.delhomme.jobbingtrack.ui.entreprises.EntrepriseDetailScreen
import com.delhomme.jobbingtrack.ui.entreprises.EntreprisesScreen
import com.delhomme.jobbingtrack.ui.entreprises.EntretienDetailScreen
import com.delhomme.jobbingtrack.ui.entretiens.AddOrEditEntretienScreen
import com.delhomme.jobbingtrack.ui.entretiens.EntretiensScreen
import com.delhomme.jobbingtrack.ui.main.DashboardScreen
import com.delhomme.jobbingtrack.ui.login.LoginScreen
import com.delhomme.jobbingtrack.ui.main.MainScreen
import com.delhomme.jobbingtrack.ui.relances.AddOrEditRelanceScreen
import com.delhomme.jobbingtrack.ui.relances.RelanceDetailScreen
import com.delhomme.jobbingtrack.ui.relances.RelancesScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }
        composable(Routes.HOME) {
            MainScreen(navController)
        }
        composable(Routes.CANDIDATURES) {
            CandidaturesScreen(
                candidatures = FakeDataProvider.candidatures,
                onItemClick = { candidature ->
                    navController.navigate("${Routes.CANDIDATURE_DETAIL}/${candidature.id}")
                },
                onAddClick = {
                    // Action pour ajouter une candidature ouvrir le bottom enfin formulaire d'ajout d'une candidature
                }
            )
        }
        composable(Routes.CONTACTS) {
            ContactsScreen(
                contacts = FakeDataProvider.contacts,
                onItemClick = { contact ->
                    navController.navigate("${Routes.CONTACT_DETAIL}/${contact.id}")
                },
                onAddClick = {

                }
            )
        }
        composable(Routes.ENTREPRISES) {
            EntreprisesScreen(
                entreprises = FakeDataProvider.entreprises,
                onItemClick = { entreprise ->
                    navController.navigate("${Routes.ENTREPRISE_DETAIL}/${entreprise.id}")
                },
                onAddClick = {

                }
            )
        }
        composable(Routes.APPELS) {
            AppelsScreen(
                appels = FakeDataProvider.appels,
                onItemClick = { appel ->
                    println("Clique sur appel : ${appel.subject}")
                },
                onAddClick = {

                }
            )
        }
        composable(Routes.RELANCES) {
            RelancesScreen(
                relances = FakeDataProvider.relances,
                onItemClick = { relance ->
                    println("Clique sur relance pour candidature ${relance.candidatureId}")
                },
            )
        }
        composable(Routes.ENTRETIENS) {
            EntretiensScreen(
                entretiens = FakeDataProvider.entretiens,
                onItemClick = { entretien ->
                    println("Clique sur entretien de candidature ${entretien.candidatureId}")
                },
            )
        }
        composable(Routes.ADD_CANDIDATURE) {
            AddOrEditCandidatureScreen(
                navController = navController,
                existingCandidatureData = null,
                onSave = { formData ->
                    println("Nouvelle candidature sauvegardée : $formData")
                }
            )
        }
        composable(Routes.ADD_CONTACT) {
            AddOrEditContactScreen(
                navController = navController,
                existingContactData = null,
                onSave = { formData ->
                    println("Nouveau contact sauvegardé : $formData")
                }
            )
        }
        composable(Routes.ADD_ENTREPRISE) {
            AddOrEditEntrepriseScreen(
                navController = navController,
                existingEntrepriseData = null,
                onSave = { formData ->
                    println("Nouvelle entreprise sauvegardée : $formData")
                }
            )
        }
        composable(Routes.ADD_RELANCE) {
            AddOrEditRelanceScreen(
                navController = navController,
                existingRelanceData = null,
                onSave = { formData ->
                    println("Nouvelle relance sauvegardée : $formData")
                }
            )
        }
        composable(Routes.ADD_APPEL) {
            AddOrEditAppelScreen(
                navController = navController,
                linkedCandidatureId = null, // (ou une valeur contextuelle si besoin)
                onSave = { formData ->
                    println("Nouvel appel sauvegardé : $formData")
                }
            )
        }
        composable(Routes.ADD_ENTRETIEN) {
            AddOrEditEntretienScreen(navController = navController) { formData ->
                println("Nouvel entretien sauvegardé : $formData")
            }
        }
        composable(
            route = "${Routes.CANDIDATURE_DETAIL}/{candidatureId}",
            arguments = listOf(navArgument("candidatureId") { type = NavType.StringType })
        ) { backStackEntry ->
            val candidatureId = backStackEntry.arguments?.getString("candidatureId") ?: return@composable
            val candidature = FakeDataProvider.candidatures.find { it.id == candidatureId }
            if (candidature != null) {
                CandidatureDetailScreen(
                    candidature = candidature,
                    relances = FakeDataProvider.relances.filter { it.candidatureId == candidatureId },
                    appels = FakeDataProvider.appels.filter { it.candidatureId == candidatureId },
                    entretiens = FakeDataProvider.entretiens.filter { it.candidatureId == candidatureId },
                    contacts = FakeDataProvider.contacts, // À filtrer selon ta logique de contact lié
                    onAddRelance = { candidatureId ->
                        navController.navigate("${Routes.ADD_RELANCE}?linkedCandidatureId=$candidatureId")
                    },
                    onAddAppel = { candidatureId ->
                        navController.navigate("${Routes.ADD_APPEL}?linkedCandidatureId=$candidatureId")
                    },
                    onAddEntretien = { candidatureId ->
                        navController.navigate("${Routes.ADD_ENTRETIEN}?linkedCandidatureId=$candidatureId")
                    },
                    onAddContact = { candidatureId ->
                        navController.navigate("${Routes.ADD_CONTACT}?linkedCandidatureId=$candidatureId")
                    },
                    navController = navController
                )
            }
        }
        composable("${Routes.ENTREPRISE_DETAIL}/{entrepriseId}") { backStackEntry ->
            val entrepriseId = backStackEntry.arguments?.getString("entrepriseId")
            val entreprise = FakeDataProvider.entreprises.find { it.id == entrepriseId }
            if (entreprise != null) {
                EntrepriseDetailScreen(
                    entreprise = entreprise,
                    navController = navController
                )
            }
        }
        composable("${Routes.CONTACT_DETAIL}/{contactId}") { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")
            val contact = FakeDataProvider.contacts.find { it.id == contactId }
            if (contact != null) {
                ContactDetailScreen(
                    contact = contact,
                    navController = navController
                )
            }
        }

        composable("${Routes.ENTRETIEN_DETAIL}/{entretienId}") { backStackEntry ->
            val entretienId = backStackEntry.arguments?.getString("entretienId")
            val entretien = FakeDataProvider.entretiens.find { it.id == entretienId }
            if (entretien != null) {
                EntretienDetailScreen(
                    entretien = entretien,
                    onBackClick = { navController.popBackStack() },
                    navController = navController
                )
            }
        }
        composable("${Routes.RELANCE_DETAIL}/{relanceId}") { backStackEntry ->
            val relanceId = backStackEntry.arguments?.getString("relanceId")
            val relance = FakeDataProvider.relances.find { it.id == relanceId }
            if (relance != null) {
                RelanceDetailScreen(
                    relance = relance,
                    navController = navController
                )
            }
        }

        composable("${Routes.APPEL_DETAIL}/{appelId}") { backStackEntry ->
            val appelId = backStackEntry.arguments?.getString("appelId")
            val appel = FakeDataProvider.appels.find { it.id == appelId }
            if (appel != null) {
                AppelDetailScreen(
                    appel = appel,
                    navController = navController
                )
            }
        }
    }
}
