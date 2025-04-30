package com.delhomme.jobbingtrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.ui.appels.AddOrEditAppelScreen
import com.delhomme.jobbingtrack.ui.appels.AppelDetailScreen
import com.delhomme.jobbingtrack.ui.appels.AppelsScreen
import com.delhomme.jobbingtrack.ui.archive_bin.ArchiveScreen
import com.delhomme.jobbingtrack.ui.archive_bin.TrashScreen
import com.delhomme.jobbingtrack.ui.candidatures.AddOrEditCandidatureScreen
import com.delhomme.jobbingtrack.ui.candidatures.CandidatureDetailScreen
import com.delhomme.jobbingtrack.ui.candidatures.CandidaturesScreen
import com.delhomme.jobbingtrack.ui.candidatures.CandidaturesTabScreen
import com.delhomme.jobbingtrack.ui.contacts.AddOrEditContactScreen
import com.delhomme.jobbingtrack.ui.contacts.ContactDetailScreen
import com.delhomme.jobbingtrack.ui.contacts.ContactsScreen
import com.delhomme.jobbingtrack.ui.entreprises.AddOrEditEntrepriseScreen
import com.delhomme.jobbingtrack.ui.entreprises.EntrepriseDetailScreen
import com.delhomme.jobbingtrack.ui.entreprises.EntreprisesScreen
import com.delhomme.jobbingtrack.ui.entreprises.EntretienDetailScreen
import com.delhomme.jobbingtrack.ui.entretiens.AddOrEditEntretienScreen
import com.delhomme.jobbingtrack.ui.entretiens.EntretiensScreen
import com.delhomme.jobbingtrack.ui.login.LoginScreen
import com.delhomme.jobbingtrack.ui.main.CalendarScreen
import com.delhomme.jobbingtrack.ui.main.MainScreen
import com.delhomme.jobbingtrack.ui.relances.AddOrEditRelanceScreen
import com.delhomme.jobbingtrack.ui.relances.RelanceDetailScreen
import com.delhomme.jobbingtrack.ui.relances.RelancesScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.MAIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(Routes.MAIN) {
            MainScreen(navController = navController)
        }

        composable("${Routes.CANDIDATURE_DETAIL}/{candidatureId}",
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
                    contacts = FakeDataProvider.contacts, // à filtrer si besoin
                    onAddRelance = { navController.navigate("${Routes.ADD_RELANCE}?linkedCandidatureId=$it") },
                    onAddAppel = { navController.navigate("${Routes.ADD_APPEL}?linkedCandidatureId=$it") },
                    onAddEntretien = { navController.navigate("${Routes.ADD_ENTRETIEN}?linkedCandidatureId=$it") },
                    onAddContact = { navController.navigate("${Routes.ADD_CONTACT}?linkedCandidatureId=$it") },
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

        composable("${Routes.ENTRETIEN_DETAIL}/{entretienId}") { backStackEntry ->
            val entretienId = backStackEntry.arguments?.getString("entretienId")
            val entretien = FakeDataProvider.entretiens.find { it.id == entretienId }
            if (entretien != null) {
                EntretienDetailScreen(
                    entretien = entretien,
                    navController = navController,
                    onBackClick = { navController.popBackStack() }
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



        composable(Routes.ADD_CANDIDATURE) {
            AddOrEditCandidatureScreen(
                navController = navController,
                existingCandidatureData = null,
                onSave = {
                    println("Candidature ajoutée : $it")
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.ADD_CONTACT) {
            AddOrEditContactScreen(
                navController = navController,
                existingContactData = null,
                linkedCandidatureId = null,
                onSave = {
                    println("Contact ajouté : $it")
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.ADD_ENTREPRISE) {
            AddOrEditEntrepriseScreen(
                navController = navController,
                existingEntrepriseData = null,
                onSave = {
                    println("Entreprise ajoutée : $it")
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.ADD_RELANCE) {
            AddOrEditRelanceScreen(
                navController = navController,
                existingRelanceData = null,
                linkedCandidatureId = null,
                onSave = {
                    println("Relance ajoutée : $it")
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.ADD_APPEL) {
            AddOrEditAppelScreen(
                navController = navController,
                existingAppelData = null,
                linkedCandidatureId = null,
                onSave = {
                    println("Appel ajouté : $it")
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.ADD_ENTRETIEN) {
            AddOrEditEntretienScreen(
                navController = navController,
                existingEntretienData = null,
                linkedCandidatureId = null,
                onSave = {
                    println("Entretien ajouté : $it")
                    navController.popBackStack()
                }
            )
        }

        composable("${Routes.EDIT_CANDIDATURE}/{candidatureId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("candidatureId")
            val item = FakeDataProvider.candidatures.find { it.id == id }
            if (item != null) {
                AddOrEditCandidatureScreen(
                    navController = navController,
                    existingCandidatureData = item,
                    onSave = {
                        println("Candidature modifiée : $it")
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("${Routes.EDIT_CONTACT}/{contactId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("contactId")
            val item = FakeDataProvider.contacts.find { it.id == id }
            if (item != null) {
                AddOrEditContactScreen(
                    navController = navController,
                    existingContactData = item,
                    onSave = {
                        println("Contact modifié : $it")
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("${Routes.EDIT_ENTREPRISE}/{entrepriseId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("entrepriseId")
            val item = FakeDataProvider.entreprises.find { it.id == id }
            if (item != null) {
                AddOrEditEntrepriseScreen(
                    navController = navController,
                    existingEntrepriseData = item,
                    onSave = {
                        println("Entreprise modifiée : $it")
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("${Routes.EDIT_RELANCE}/{relanceId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("relanceId")
            val item = FakeDataProvider.relances.find { it.id == id }
            if (item != null) {
                AddOrEditRelanceScreen(
                    navController = navController,
                    existingRelanceData = item,
                    linkedCandidatureId = item.candidatureId,
                    onSave = {
                        println("Relance modifiée : $it")
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("${Routes.EDIT_APPEL}/{appelId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("appelId")
            val item = FakeDataProvider.appels.find { it.id == id }
            if (item != null) {
                AddOrEditAppelScreen(
                    navController = navController,
                    existingAppelData = item,
                    linkedCandidatureId = item.candidatureId,
                    onSave = {
                        println("Appel modifié : $it")
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("${Routes.EDIT_ENTRETIEN}/{entretienId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("entretienId")
            val item = FakeDataProvider.entretiens.find { it.id == id }
            if (item != null) {
                AddOrEditEntretienScreen(
                    navController = navController,
                    existingEntretienData = item,
                    linkedCandidatureId = item.candidatureId,
                    onSave = {
                        println("Entretien modifié : $it")
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(Routes.ARCHIVES) {
            ArchiveScreen(navController)
        }
        composable(Routes.TRASH) {
            TrashScreen(navController)
        }


    }
}
