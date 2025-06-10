package com.delhomme.jobbingtrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.delhomme.jobbingtrack.JobbingTrackApp
import com.delhomme.jobbingtrack.api.authentication.ui.*
import com.delhomme.jobbingtrack.applications.ui.*
import com.delhomme.jobbingtrack.applications.viewmodels.*
import com.delhomme.jobbingtrack.companies.ui.*
import com.delhomme.jobbingtrack.companies.viewmodels.*
import com.delhomme.jobbingtrack.calls.ui.*
import com.delhomme.jobbingtrack.calls.viewmodels.*
import com.delhomme.jobbingtrack.contacts.ui.*
import com.delhomme.jobbingtrack.contacts.viewmodels.*
import com.delhomme.jobbingtrack.followsup.ui.*
import com.delhomme.jobbingtrack.followsup.viewmodels.*
import com.delhomme.jobbingtrack.interviews.ui.*
import com.delhomme.jobbingtrack.interviews.viewmodels.*
import com.delhomme.jobbingtrack.archives.ui.*
import com.delhomme.jobbingtrack.archives.viewmodels.*
import com.delhomme.jobbingtrack.trash.ui.*
import com.delhomme.jobbingtrack.trash.viewmodels.*
import com.delhomme.jobbingtrack.cvs.ui.*
import com.delhomme.jobbingtrack.cvs.viewmodels.*
import com.delhomme.jobbingtrack.profiles.ui.*
import com.delhomme.jobbingtrack.profiles.viewmodels.*
import com.delhomme.jobbingtrack.ui.main.MainScreen

@Composable
fun NavGraph(navController: NavHostController, isLoggedIn: Boolean, userId: String?) {
    NavHost(
        navController = navController,
        startDestination = when {
            userId == null -> Routes.LOGIN
            isLoggedIn -> Routes.MAIN
            else -> Routes.LOGIN
        }
    ) {
        // — LOGIN / REGISTER / MAIN —
        composable(Routes.LOGIN)    { LoginScreen(navController) }
        composable(Routes.REGISTER) { RegisterScreen(navController) }
        composable(Routes.MAIN)     { MainScreen(navController, userId ?: "") }

        composable(Routes.PROFILE) {
            ProfileScreen(
                profileId = userId,
                viewModel = ProfileViewModel(JobbingTrackApp())
            )
        }
        // — CANDIDATURES —
        composable(
            route = Routes.APPLICATION_ADD
                    + "?linkedCompanyId={linkedCompanyId}",
            arguments = listOf(
                navArgument("linkedCompanyId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStack ->
            val linkedCompId = backStack.arguments?.getString("linkedCompanyId")
            AddOrEditApplicationScreen(
                userId = userId.toString(),
                applicationId = null,
                linkedCompanyId  = linkedCompId,
                onCancel            = { navController.popBackStack() }
            )
        }
        composable(
            route = "${Routes.APPLICATION_EDIT}/{applicationId}",
            arguments = listOf(navArgument("applicationId") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("applicationId")!!
            AddOrEditApplicationScreen(
                userId = userId.toString(),
                applicationId = id,
                linkedCompanyId = null,
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.APPLICATION_DETAIL}/{applicationId}",
            arguments = listOf(navArgument("applicationId") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("applicationId")!!
            ApplicationDetailsScreen(userId = userId.toString(), applicationId = id, navController = navController)
        }

        // — CONTACTS —
        composable(
            route = Routes.CONTACT_ADD
                    + "?linkedApplicationId={linkedApplicationId}"
                    + "&linkedCompanyId={linkedCompanyId}",
            arguments = listOf(
                navArgument("linkedApplicationId"){ type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedCompanyId"){ type=NavType.StringType; nullable=true; defaultValue=null }
            )
        ) { bs ->
            AddOrEditContactScreen(
                contactId            = null,
                userId               = userId.toString(),
                linkedApplicationId  = bs.arguments?.getString("linkedApplicationId"),
                linkedCompanyId      = bs.arguments?.getString("linkedCompanyId"),
                onCancel             = { navController.popBackStack() }
            )
        }
        composable(
            route = "${Routes.CONTACT_EDIT}/{contactId}",
            arguments = listOf(navArgument("contactId"){ type=NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments!!.getString("contactId")!!
            AddOrEditContactScreen(
                contactId            = id,
                userId               = userId.toString(),
                linkedApplicationId  = null,
                linkedCompanyId   = null,
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
            ContactDetailScreen(contactId = id, userId = userId.toString(), navController = navController)
        }

        // — ENTREPRISES —
        composable(Routes.COMPANY_ADD) {
            AddOrEditCompanyScreen(
                companyId = null,
                userId = userId,
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.COMPANY_EDIT}/{companyId}",
            arguments = listOf(navArgument("companyId") {
                type = NavType.StringType
            })
        ) { bs ->
            val id = bs.arguments!!.getString("companyId")!!
            AddOrEditCompanyScreen(
                companyId = id,
                userId = userId,
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.COMPANY_DETAIL}/{companyId}",
            arguments = listOf(navArgument("companyId") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("companyId")!!
            CompanyDetailScreen(companyId = id, userId = userId.toString(), navController = navController)
        }


        // — RELANCES —
        composable(
            route = Routes.FOLLOWUP_ADD
                    + "?linkedApplicationId={linkedApplicationId}"
                    + "&linkedCompanyId={linkedCompanyId}",
            arguments = listOf(
                navArgument("linkedApplicationId") { type = NavType.StringType; nullable = true; defaultValue = null },
                navArgument("linkedCompanyId")       { type = NavType.StringType; nullable = true; defaultValue = null }
            )
        ) { bs ->
            AddOrEditFollowUpScreen(
                followUpId = null,
                userId = userId.toString(),
                linkedApplicationId = bs.arguments?.getString("linkedApplicationId"),
                linkedCompanyId     = bs.arguments?.getString("linkedCompanyId"),
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            route = "${Routes.FOLLOWUP_EDIT}/{followUpId}"
                    + "?linkedApplicationId={linkedApplicationId}"
                    + "&linkedCompanyId={linkedCompanyId}",
            arguments = listOf(
                navArgument("followUpId"){ type=NavType.StringType },
                navArgument("linkedApplicationId"){ type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedCompanyId"){ type=NavType.StringType; nullable=true; defaultValue=null }
            )
        ) { bs ->
            AddOrEditFollowUpScreen(
                followUpId           = bs.arguments!!.getString("followUpId"),
                userId = userId.toString(),
                linkedApplicationId = bs.arguments?.getString("linkedApplicationId"),
                linkedCompanyId     = bs.arguments?.getString("linkedCompanyId"),
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.FOLLOWUP_DETAIL}/{followUpId}",
            arguments = listOf(navArgument("followUpId") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("followUpId")!!
            FollowUpDetailScreen(followUpId = id, userId = userId.toString(), navController = navController)
        }


        // — APPELS —
        composable(
            route = Routes.CALL_ADD
                    + "?linkedApplicationId={linkedApplicationId}"
                    + "&linkedCompanyId={linkedCompanyId}"
                    + "&linkedContactId={linkedContactId}"
                    + "&linkedFollowUpId={linkedFollowUpId}",
            arguments = listOf(
                navArgument("linkedApplicationId"){ type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedCompanyId")     { type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedContactId")     { type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedFollowUpId")     { type=NavType.StringType; nullable=true; defaultValue=null }
            )
        ) { bs ->
            AddOrEditCallScreen(
                callId             = null,
                userId              = userId.toString(),
                linkedApplicationId = bs.arguments?.getString("linkedApplicationId"),
                linkedCompanyId     = bs.arguments?.getString("linkedCompanyId"),
                linkedContactId     = bs.arguments?.getString("linkedContactId"),
                linkedFollowUpId     = bs.arguments?.getString("linkedFollowUpId"),
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.CALL_EDIT}/{callId}"
                    + "?linkedApplicationId={linkedApplicationId}"
                    + "&linkedCompanyId={linkedCompanyId}"
                    + "&linkedContactId={linkedContactId}"
                    + "&linkedFollowUpId={linkedFollowUpId}",
            arguments = listOf(
                navArgument("callId"){ type=NavType.StringType },
                navArgument("linkedApplicationId"){ type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedCompanyId")     { type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedContactId")     { type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedFollowUpId")     { type=NavType.StringType; nullable=true; defaultValue=null }
            )
        ) { bs ->
            AddOrEditCallScreen(
                callId             = bs.arguments!!.getString("callId"),
                userId              = userId.toString(),
                linkedApplicationId = bs.arguments?.getString("linkedApplicationId"),
                linkedCompanyId     = bs.arguments?.getString("linkedCompanyId"),
                linkedContactId     = bs.arguments?.getString("linkedContactId"),
                linkedFollowUpId     = bs.arguments?.getString("linkedFollowUpId"),
                onCancel            = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.DETAIL_CALL}/{callId}",
            arguments = listOf(navArgument("callId") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("callId")!!
            CallDetailsScreen(callId = id, navController = navController, userId = userId.toString())
        }

        // — ENTRETIENS —
        composable(
            route = Routes.INTERVIEW_ADD
                    + "?linkedApplicationId={linkedApplicationId}"
                    + "&linkedCompanyId={linkedCompanyId}",
            arguments = listOf(
                navArgument("linkedApplicationId"){
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
            val linkedAppId = backStack.arguments?.getString("linkedApplicationId")
            val linkedCompId = backStack.arguments?.getString("linkedCompanyId")
            AddOrEditInterviewScreen(
                interviewId = null,
                linkedApplicationId = linkedAppId,
                userId = userId.toString(),
                linkedCompanyId = linkedCompId,
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            route = "${Routes.INTERVIEW_EDIT}/{entretienId}"
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

            AddOrEditInterviewScreen(
                interviewId = id,
                userId = userId.toString(),
                linkedApplicationId = linkedC,
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
            InterviewDetailScreen(
                interviewId = id,
                userId = userId.toString(),
                navController = navController,
                onBackClick = { navController.popBackStack() }
            )
        }

        // — ARCHIVES / TRASH —
        composable(Routes.ARCHIVES) { ArchiveScreen(navController, userId = userId.toString(),
            applicationViewModel = ApplicationViewModel(JobbingTrackApp()),
            companyViewModel = CompanyViewModel(JobbingTrackApp()),
            callViewModel = CallViewModel(JobbingTrackApp()),
            contactViewModel = ContactViewModel(JobbingTrackApp()),
            interviewViewModel = InterviewViewModel(JobbingTrackApp()),
            followUpViewModel = FollowUpViewModel(JobbingTrackApp()),
            onDelete = { navController.navigate(Routes.TRASH) },
            onRestore = { navController.navigate(Routes.MAIN) }
        ) }
        composable(Routes.TRASH) { TrashScreen(navController, userId = userId.toString(),
            applicationViewModel = ApplicationViewModel(JobbingTrackApp()),
            companyViewModel = CompanyViewModel(JobbingTrackApp()),
            callViewModel = CallViewModel(JobbingTrackApp()),
            contactViewModel = ContactViewModel(JobbingTrackApp()),
            interviewViewModel = InterviewViewModel(JobbingTrackApp()),
            followUpViewModel = FollowUpViewModel(JobbingTrackApp()),
            onDelete = { navController.navigate(Routes.TRASH) },
            onRestore = { navController.navigate(Routes.MAIN) }
        ) }

        // — CV —
        composable(Routes.CVS) { CvsScreen(navController, userId = userId.toString(),
            cvViewModel = CvViewModel(JobbingTrackApp()),
        ) }
        composable(
            "${Routes.CV_UPLOAD}",
            arguments = listOf(navArgument("cvId") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("cvId")!!
            CVUploadScreen(cvId = id, navController = navController)
        }
    }
}
