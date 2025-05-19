package com.delhomme.jobbingtrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
import com.delhomme.jobbingtrack.ui.major.login.RegisterScreen
import com.delhomme.jobbingtrack.ui.major.relances.AddOrEditRelanceScreen
import com.delhomme.jobbingtrack.ui.major.relances.RelanceDetailScreen

@Composable
fun NavGraph(navController: NavHostController, isLoggedIn: Boolean) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Routes.MAIN else Routes.LOGIN
    ) {
        // — LOGIN / REGISTER / MAIN —
        composable(Routes.LOGIN)    { LoginScreen(navController) }
        composable(Routes.REGISTER) { RegisterScreen(navController) }
        composable(Routes.MAIN)     { MainScreen(navController) }

        // — CANDIDATURES —
        composable(
            route = Routes.ADD_CANDIDATURE
                    + "?linkedEntrepriseId={linkedEntrepriseId}",
            arguments = listOf(
                navArgument("linkedEntrepriseId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStack ->
            val linkedEntId = backStack.arguments?.getString("linkedEntrepriseId")
            AddOrEditCandidatureScreen(
                candidatureId = null,
                linkedEntrepriseId  = linkedEntId,
                onCancel            = { navController.popBackStack() }
            )
        }
        composable(
            route = "${Routes.EDIT_CANDIDATURE}/{candidatureId}",
            arguments = listOf(navArgument("candidatureId") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("candidatureId")!!
            AddOrEditCandidatureScreen(
                candidatureId = id,
                linkedEntrepriseId = null,
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.CANDIDATURE_DETAIL}/{candidatureId}",
            arguments = listOf(navArgument("candidatureId") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("candidatureId")!!
            CandidatureDetailScreen(candidatureId = id, navController = navController)
        }

        // — CONTACTS —
        composable(
            route = Routes.ADD_CONTACT
                    + "?linkedCandidatureId={linkedCandidatureId}"
                    + "&linkedEntrepriseId={linkedEntrepriseId}",
            arguments = listOf(
                navArgument("linkedCandidatureId"){ type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedEntrepriseId"){ type=NavType.StringType; nullable=true; defaultValue=null }
            )
        ) { bs ->
            AddOrEditContactScreen(
                contactId            = null,
                linkedCandidatureId  = bs.arguments?.getString("linkedCandidatureId"),
                linkedEntrepriseId   = bs.arguments?.getString("linkedEntrepriseId"),
                onCancel             = { navController.popBackStack() }
            )
        }
        composable(
            route = "${Routes.EDIT_CONTACT}/{contactId}",
            arguments = listOf(navArgument("contactId"){ type=NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments!!.getString("contactId")!!
            AddOrEditContactScreen(
                contactId            = id,
                linkedCandidatureId  = null,
                linkedEntrepriseId   = null,
                onCancel             = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.CONTACT_DETAIL}/{contactId}",
            arguments = listOf(navArgument("contactId") {
                type = NavType.StringType
            })
        ) { bs ->
            val id = bs.arguments!!.getString("contactId")!!
            ContactDetailScreen(contactId = id, navController = navController)
        }

        // — ENTREPRISES —
        composable(Routes.ADD_ENTREPRISE) {
            AddOrEditEntrepriseScreen(
                entrepriseId = null,
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.EDIT_ENTREPRISE}/{entrepriseId}",
            arguments = listOf(navArgument("entrepriseId") {
                type = NavType.StringType
            })
        ) { bs ->
            val id = bs.arguments!!.getString("entrepriseId")!!
            AddOrEditEntrepriseScreen(
                entrepriseId = id,
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.ENTREPRISE_DETAIL}/{entrepriseId}",
            arguments = listOf(navArgument("entrepriseId") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("entrepriseId")!!
            EntrepriseDetailScreen(entrepriseId = id, navController = navController)
        }


        // — RELANCES —
        composable(
            route = Routes.ADD_RELANCE
                    + "?linkedCandidatureId={linkedCandidatureId}"
                    + "&linkedCompanyId={linkedCompanyId}",
            arguments = listOf(
                navArgument("linkedCandidatureId") { type = NavType.StringType; nullable = true; defaultValue = null },
                navArgument("linkedCompanyId")       { type = NavType.StringType; nullable = true; defaultValue = null }
            )
        ) { bs ->
            AddOrEditRelanceScreen(
                relanceId = null,
                linkedCandidatureId = bs.arguments?.getString("linkedCandidatureId"),
                linkedCompanyId     = bs.arguments?.getString("linkedCompanyId"),
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            route = "${Routes.EDIT_RELANCE}/{relanceId}"
                    + "?linkedCandidatureId={linkedCandidatureId}"
                    + "&linkedCompanyId={linkedCompanyId}",
            arguments = listOf(
                navArgument("relanceId"){ type=NavType.StringType },
                navArgument("linkedCandidatureId"){ type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedCompanyId"){ type=NavType.StringType; nullable=true; defaultValue=null }
            )
        ) { bs ->
            AddOrEditRelanceScreen(
                relanceId           = bs.arguments!!.getString("relanceId"),
                linkedCandidatureId = bs.arguments?.getString("linkedCandidatureId"),
                linkedCompanyId     = bs.arguments?.getString("linkedCompanyId"),
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.RELANCE_DETAIL}/{relanceId}",
            arguments = listOf(navArgument("relanceId") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("relanceId")!!
            RelanceDetailScreen(relanceId = id, navController = navController)
        }


        // — APPELS —
        composable(
            route = Routes.ADD_APPEL
                    + "?linkedCandidatureId={linkedCandidatureId}"
                    + "&linkedCompanyId={linkedCompanyId}"
                    + "&linkedContactId={linkedContactId}"
                    + "&linkedRelanceId={linkedRelanceId}",
            arguments = listOf(
                navArgument("linkedCandidatureId"){ type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedCompanyId")     { type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedContactId")     { type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedRelanceId")     { type=NavType.StringType; nullable=true; defaultValue=null }
            )
        ) { bs ->
            AddOrEditAppelScreen(
                appelId             = null,
                linkedCandidatureId = bs.arguments?.getString("linkedCandidatureId"),
                linkedCompanyId     = bs.arguments?.getString("linkedCompanyId"),
                linkedContactId     = bs.arguments?.getString("linkedContactId"),
                linkedRelanceId     = bs.arguments?.getString("linkedRelanceId"),
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.EDIT_APPEL}/{appelId}"
                    + "?linkedCandidatureId={linkedCandidatureId}"
                    + "&linkedCompanyId={linkedCompanyId}"
                    + "&linkedContactId={linkedContactId}"
                    + "&linkedRelanceId={linkedRelanceId}",
            arguments = listOf(
                navArgument("appelId"){ type=NavType.StringType },
                navArgument("linkedCandidatureId"){ type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedCompanyId")     { type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedContactId")     { type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedRelanceId")     { type=NavType.StringType; nullable=true; defaultValue=null }
            )
        ) { bs ->
            AddOrEditAppelScreen(
                appelId             = bs.arguments!!.getString("appelId"),
                linkedCandidatureId = bs.arguments?.getString("linkedCandidatureId"),
                linkedCompanyId     = bs.arguments?.getString("linkedCompanyId"),
                linkedContactId     = bs.arguments?.getString("linkedContactId"),
                linkedRelanceId     = bs.arguments?.getString("linkedRelanceId"),
                onCancel            = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.APPEL_DETAIL}/{appelId}",
            arguments = listOf(navArgument("appelId") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("appelId")!!
            AppelDetailScreen(appelId = id, navController = navController)
        }

        // — ENTRETIENS —
        composable(
            route = Routes.ADD_ENTRETIEN
                    + "?linkedCandidatureId={linkedCandidatureId}"
                    + "&linkedCompanyId={linkedCompanyId}",
            arguments = listOf(
                navArgument("linkedCandidatureId"){
                    type=NavType.StringType
                    nullable=true
                    defaultValue=null
                },
                navArgument("linkedCompanyId")      {
                    type=NavType.StringType
                    nullable=true
                    defaultValue=null
                }
            )
        ) { backStack ->
            val linkedCand = backStack.arguments?.getString("linkedCandidatureId")
            val linkedComp = backStack.arguments?.getString("linkedCompanyId")
            AddOrEditEntretienScreen(
                entretienId = null,
                linkedCandidatureId = linkedCand,
                linkedCompanyId = linkedComp,
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            route = "${Routes.EDIT_ENTRETIEN}/{entretienId}"
                  + "?linkedCandidatureId={linkedCandidatureId"
                  + "&linkedCompanyId={linkedCompanyId}",
            arguments = listOf(
                navArgument("entretienId") {
                    type = NavType.StringType
                },
                navArgument("linkedCandidatureId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("linkedCompanyId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStack ->
            val id = backStack.arguments!!.getString("entretienId")!!
            val linkedC = backStack.arguments?.getString("linkedCandidatureId")
            val linkedP = backStack.arguments?.getString("linkedCompanyId")

            AddOrEditEntretienScreen(
                entretienId = id,
                linkedCandidatureId = linkedC,
                linkedCompanyId = linkedP,
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.ENTRETIEN_DETAIL}/{entretienId}",
            arguments = listOf(navArgument("entretienId") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("entretienId")!!
            EntretienDetailScreen(
                entretienId = id,
                navController = navController,
                onBackClick = { navController.popBackStack() }
            )
        }

        // — ARCHIVES / TRASH —
        composable(Routes.ARCHIVES) { ArchiveScreen(navController) }
        composable(Routes.TRASH) { TrashScreen(navController) }
    }
}
