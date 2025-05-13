package com.delhomme.jobbingtrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.logic.saveAppelFromForm
import com.delhomme.jobbingtrack.data.logic.saveCandidatureFromForm
import com.delhomme.jobbingtrack.data.logic.saveContactFromForm
import com.delhomme.jobbingtrack.data.logic.saveEntrepriseFromForm
import com.delhomme.jobbingtrack.data.logic.saveEntretienFromForm
import com.delhomme.jobbingtrack.data.logic.saveRelanceFromForm
import com.delhomme.jobbingtrack.ui.major.appels.AddOrEditAppelScreen
import com.delhomme.jobbingtrack.ui.major.appels.AppelDetailScreen
import com.delhomme.jobbingtrack.ui.major.archive_bin.ArchiveScreen
import com.delhomme.jobbingtrack.ui.major.archive_bin.TrashScreen
import com.delhomme.jobbingtrack.ui.major.candidatures.AddOrEditCandidatureScreen
import com.delhomme.jobbingtrack.ui.major.candidatures.CandidatureDetailScreen
import com.delhomme.jobbingtrack.ui.major.contacts.AddOrEditContactScreen
import com.delhomme.jobbingtrack.ui.major.contacts.ContactDetailScreen
import com.delhomme.jobbingtrack.ui.major.entreprises.AddOrEditEntrepriseScreen
import com.delhomme.jobbingtrack.ui.major.entreprises.EntrepriseDetailScreen
import com.delhomme.jobbingtrack.ui.major.entretiens.EntretienDetailScreen
import com.delhomme.jobbingtrack.ui.major.entretiens.AddOrEditEntretienScreen
import com.delhomme.jobbingtrack.ui.major.login.LoginScreen
import com.delhomme.jobbingtrack.ui.main.MainScreen
import com.delhomme.jobbingtrack.ui.major.relances.AddOrEditRelanceScreen
import com.delhomme.jobbingtrack.ui.major.relances.RelanceDetailScreen

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

        composable("${Routes.ADD_CONTACT}?linkedCandidatureId={linkedCandidatureId}&linkedEntrepriseId={linkedEntrepriseId}",
            arguments = listOf(
                navArgument("linkedCandidatureId") { nullable = true; type = NavType.StringType },
                navArgument("linkedEntrepriseId") { nullable = true; type = NavType.StringType }
            )
        ) { backStackEntry ->
            val candidatureId = backStackEntry.arguments?.getString("linkedCandidatureId")
            var entrepriseId = backStackEntry.arguments?.getString("linkedEntrepriseId")

            if (entrepriseId == null && candidatureId != null) {
                entrepriseId = FakeDataProvider.candidatures.find { it.id == candidatureId }?.companyId
            }

            AddOrEditContactScreen(
                navController = navController,
                existingContactData = null,
                linkedEntrepriseId = entrepriseId,
                onSave = {
                    saveContactFromForm(it)
                    navController.popBackStack()
                }
            )
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
                    saveCandidatureFromForm(it)
                    navController.popBackStack()
                }
            )
        }
        composable(
            "${Routes.ADD_CONTACT}?linkedEntrepriseId={linkedEntrepriseId}",
            arguments = listOf(navArgument("linkedEntrepriseId") { nullable = true; type = NavType.StringType })
        ) { backStackEntry ->
            val entrepriseId = backStackEntry.arguments?.getString("linkedEntrepriseId")

            AddOrEditContactScreen(
                navController = navController,
                existingContactData = null,
                linkedEntrepriseId = entrepriseId,
                onSave = {
                    saveContactFromForm(it)
                    navController.popBackStack()
                }
            )
        }


        composable("${Routes.EDIT_CONTACT}/{contactId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("contactId")
            val item = FakeDataProvider.contacts.find { it.id == id }
            if (item != null) {
                AddOrEditContactScreen(
                    navController = navController,
                    existingContactData = item,
                    linkedEntrepriseId = item.entrepriseId,
                    onSave = {
                        saveContactFromForm(it)
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(Routes.ADD_ENTREPRISE) {
            AddOrEditEntrepriseScreen(
                navController = navController,
                existingEntrepriseData = null,
                onSave = {
                    saveEntrepriseFromForm(it)
                    navController.popBackStack()
                }
            )
        }

        composable("${Routes.ADD_RELANCE}?linkedCandidatureId={linkedCandidatureId}&linkedCompanyId={linkedCompanyId}",
            arguments = listOf(
                navArgument("linkedCandidatureId") { nullable = true; type = NavType.StringType },
                navArgument("linkedCompanyId") { nullable = true; type = NavType.StringType }
            )
        ) { backStackEntry ->
            val candidatureId = backStackEntry.arguments?.getString("linkedCandidatureId")
            val companyId = backStackEntry.arguments?.getString("linkedCompanyId")

            AddOrEditRelanceScreen(
                navController = navController,
                existingRelanceData = null,
                linkedCandidatureId = candidatureId,
                linkedCompanyId = companyId,
                onSave = {
                    saveRelanceFromForm(it)
                    navController.popBackStack()
                }
            )
        }

        composable("${Routes.ADD_APPEL}?linkedCandidatureId={linkedCandidatureId}&linkedCompanyId={linkedCompanyId}",
            arguments = listOf(
                navArgument("linkedCandidatureId") { nullable = true; type = NavType.StringType },
                navArgument("linkedCompanyId") { nullable = true; type = NavType.StringType }
            )
        ) { backStackEntry ->
            val candidatureId = backStackEntry.arguments?.getString("linkedCandidatureId")
            val companyId = backStackEntry.arguments?.getString("linkedCompanyId")

            AddOrEditAppelScreen(
                navController = navController,
                existingAppelData = null,
                linkedCandidatureId = candidatureId,
                linkedCompanyId = companyId,
                onSave = {
                    saveAppelFromForm(it)
                    navController.popBackStack()
                }
            )
        }

        composable("${Routes.ADD_ENTRETIEN}?linkedCandidatureId={linkedCandidatureId}&linkedCompanyId={linkedCompanyId}",
            arguments = listOf(
                navArgument("linkedCandidatureId") { nullable = true; type = NavType.StringType },
                navArgument("linkedCompanyId") { nullable = true; type = NavType.StringType }
            )
        ) { backStackEntry ->
            val candidatureId = backStackEntry.arguments?.getString("linkedCandidatureId")
            val companyId = backStackEntry.arguments?.getString("linkedCompanyId")

            AddOrEditEntretienScreen(
                navController = navController,
                existingEntretienData = null,
                linkedCandidatureId = candidatureId,
                linkedCompanyId = companyId,
                onSave = {
                    saveEntretienFromForm(it)
                    navController.popBackStack()
                }
            )
        }


        composable("${Routes.EDIT_ENTREPRISE}/{entrepriseId}",
            arguments = listOf(navArgument("entrepriseId") { type = NavType.StringType })
        ) { backStackEntry ->
            val entrepriseId = backStackEntry.arguments?.getString("entrepriseId")
            val entreprise = FakeDataProvider.entreprises.find { it.id == entrepriseId }
            entreprise?.let {
                AddOrEditEntrepriseScreen(
                    navController = navController,
                    existingEntrepriseData = it,
                    onSave = { updated ->
                        saveEntrepriseFromForm(updated)
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("${Routes.EDIT_RELANCE}/{relanceId}",
            arguments = listOf(navArgument("relanceId") { type = NavType.StringType })
        ) { backStackEntry ->
            val relanceId = backStackEntry.arguments?.getString("relanceId")
            val relance = FakeDataProvider.relances.find { it.id == relanceId }
            relance?.let {
                AddOrEditRelanceScreen(
                    navController = navController,
                    existingRelanceData = it,
                    linkedCandidatureId = it.candidatureId,
                    linkedCompanyId = it.companyId,
                    onSave = { updated ->
                        saveRelanceFromForm(updated)
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("${Routes.EDIT_APPEL}/{appelId}",
            arguments = listOf(navArgument("appelId") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("appelId")
            val appel = FakeDataProvider.appels.find { it.id == id }
            appel?.let {
                AddOrEditAppelScreen(
                    navController = navController,
                    existingAppelData = it,
                    linkedCandidatureId = it.candidatureId,
                    linkedCompanyId = it.companyId,
                    onSave = { updated ->
                        saveAppelFromForm(updated)
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("${Routes.EDIT_ENTRETIEN}/{entretienId}",
            arguments = listOf(navArgument("entretienId") { type = NavType.StringType })
        ) { backStackEntry ->
            val entretienId = backStackEntry.arguments?.getString("entretienId")
            val entretien = FakeDataProvider.entretiens.find { it.id == entretienId }
            entretien?.let {
                AddOrEditEntretienScreen(
                    navController = navController,
                    existingEntretienData = it,
                    linkedCandidatureId = it.candidatureId,
                    linkedCompanyId = it.companyId,
                    onSave = { updated ->
                        saveEntretienFromForm(updated)
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
