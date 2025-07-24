package com.delhomme.jobbingtrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.delhomme.jobbingtrack.features.application.presentation.screens.AddOrEditApplicationScreen
import com.delhomme.jobbingtrack.features.application.presentation.screens.ApplicationDetailsScreen
import com.delhomme.jobbingtrack.features.application.presentation.viewmodels.ApplicationStatusViewModel
import com.delhomme.jobbingtrack.features.application.presentation.viewmodels.ApplicationTypeViewModel
import com.delhomme.jobbingtrack.features.application.presentation.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.features.archive.presentation.ui.ArchiveScreen
import com.delhomme.jobbingtrack.features.authentication.presentation.screens.LoginScreen
import com.delhomme.jobbingtrack.features.authentication.presentation.screens.RegisterScreen
import com.delhomme.jobbingtrack.features.call.presentation.ui.AddOrEditCallScreen
import com.delhomme.jobbingtrack.features.call.presentation.ui.CallDetailsScreen
import com.delhomme.jobbingtrack.features.call.presentation.viewmodel.CallViewModel
import com.delhomme.jobbingtrack.features.company.presentation.ui.AddOrEditCompanyScreen
import com.delhomme.jobbingtrack.features.company.presentation.ui.CompanyDetailScreen
import com.delhomme.jobbingtrack.features.company.presentation.viewmodel.CompanyViewModel
import com.delhomme.jobbingtrack.features.contact.presentation.ui.AddOrEditContactScreen
import com.delhomme.jobbingtrack.features.contact.presentation.ui.ContactDetailScreen
import com.delhomme.jobbingtrack.features.contact.presentation.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.features.followup.presentation.ui.AddOrEditFollowUpScreen
import com.delhomme.jobbingtrack.features.followup.presentation.ui.FollowUpDetailScreen
import com.delhomme.jobbingtrack.features.followup.presentation.viewmodel.FollowUpStatusViewModel
import com.delhomme.jobbingtrack.features.followup.presentation.viewmodel.FollowUpTypeViewModel
import com.delhomme.jobbingtrack.features.followup.presentation.viewmodel.FollowUpViewModel
import com.delhomme.jobbingtrack.features.interview.presentation.ui.AddOrEditInterviewScreen
import com.delhomme.jobbingtrack.features.interview.presentation.ui.InterviewDetailScreen
import com.delhomme.jobbingtrack.features.interview.presentation.viewmodel.InterviewStyleViewModel
import com.delhomme.jobbingtrack.features.interview.presentation.viewmodel.InterviewTypeViewModel
import com.delhomme.jobbingtrack.features.interview.presentation.viewmodel.InterviewViewModel
import com.delhomme.jobbingtrack.features.trash.presentation.ui.TrashScreen


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
            route = "${Routes.APPLICATION_EDIT}/{application_id}",
            arguments = listOf(navArgument("application_id") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("application_id")!!
            AddOrEditApplicationScreen(
                userId = userId.toString(),
                applicationId = id,
                linkedCompanyId = null,
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.APPLICATION_DETAIL}/{application_id}",
            arguments = listOf(navArgument("application_id") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("application_id")!!
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
                contactId = null,
                userId = userId.toString(),
                linkedApplicationId = bs.arguments?.getString("linkedApplicationId"),
                linkedCompanyId = bs.arguments?.getString("linkedCompanyId"),
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            route = "${Routes.CONTACT_EDIT}/{contact_id}",
            arguments = listOf(navArgument("contact_id"){ type=NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments!!.getString("contact_id")!!
            AddOrEditContactScreen(
                contactId = id,
                userId = userId.toString(),
                linkedApplicationId = null,
                linkedCompanyId = null,
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.CONTACT_DETAIL}/{contact_id}",
            arguments = listOf(navArgument("contact_id") {
                type = NavType.StringType
            })
        ) { bs ->
            val id = bs.arguments!!.getString("contact_id")!!
            ContactDetailScreen(
                contactId = id,
                userId = userId.toString(),
                navController = navController
            )
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
            "${Routes.COMPANY_EDIT}/{company_id}",
            arguments = listOf(navArgument("company_id") {
                type = NavType.StringType
            })
        ) { bs ->
            val id = bs.arguments!!.getString("company_id")!!
            AddOrEditCompanyScreen(
                companyId = id,
                userId = userId,
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.COMPANY_DETAIL}/{company_id}",
            arguments = listOf(navArgument("company_id") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("company_id")!!
            CompanyDetailScreen(
                companyId = id,
                userId = userId.toString(),
                navController = navController
            )
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
            route = "${Routes.FOLLOWUP_EDIT}/{follow_up_id}"
                    + "?linkedApplicationId={linkedApplicationId}"
                    + "&linkedCompanyId={linkedCompanyId}",
            arguments = listOf(
                navArgument("follow_up_id"){ type=NavType.StringType },
                navArgument("linkedApplicationId"){ type=NavType.StringType; nullable=true; defaultValue=null },
                navArgument("linkedCompanyId"){ type=NavType.StringType; nullable=true; defaultValue=null }
            )
        ) { bs ->
            AddOrEditFollowUpScreen(
                followUpId           = bs.arguments!!.getString("follow_up_id"),
                userId = userId.toString(),
                linkedApplicationId = bs.arguments?.getString("linkedApplicationId"),
                linkedCompanyId     = bs.arguments?.getString("linkedCompanyId"),
                onCancel = { navController.popBackStack() }
            )
        }
        composable(
            "${Routes.FOLLOWUP_DETAIL}/{follow_up_id}",
            arguments = listOf(navArgument("follow_up_id") {
                type = NavType.StringType
            })
        ) { backStack ->
            val id = backStack.arguments!!.getString("follow_up_id")!!
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
        composable(Routes.ARCHIVES) {
            val applicationVm: ApplicationViewModel = hiltViewModel()
            val companyVm: CompanyViewModel = hiltViewModel()
            val callVm: CallViewModel = hiltViewModel()
            val contactVm: ContactViewModel = hiltViewModel()
            val interviewVm: InterviewViewModel = hiltViewModel()
            val followUpVm: FollowUpViewModel = hiltViewModel()
            val applicationStatusVm: ApplicationStatusViewModel = hiltViewModel()
            val applicationsTypeVm: ApplicationTypeViewModel = hiltViewModel()
            val interviewsStyleVm: InterviewStyleViewModel = hiltViewModel()
            val interviewsTypeVm: InterviewTypeViewModel = hiltViewModel()
            val followUpTypeVm: FollowUpTypeViewModel = hiltViewModel()
            val followUpStatusVm: FollowUpStatusViewModel = hiltViewModel()

            ArchiveScreen(navController, userId = userId.toString(),
                applicationViewModel = applicationVm,
                companyViewModel = companyVm,
                callViewModel = callVm,
                contactViewModel = contactVm,
                interviewViewModel = interviewVm,
                followUpViewModel = followUpVm,
                onDelete = { navController.navigate(Routes.TRASH) },
                onRestore = { navController.navigate(Routes.MAIN) },
                applicationsStatusVm = applicationStatusVm,
                applicationsTypeVm = applicationsTypeVm,
                interviewsStyleVm = interviewsStyleVm,
                interviewsTypeVm = interviewsTypeVm,
                followUpTypeVm = followUpTypeVm,
                followUpStatusVm = followUpStatusVm,
                onClear = { navController.navigate(Routes.ARCHIVES) }
            )
        }
        composable(Routes.TRASH) {
            val applicationVm: ApplicationViewModel = hiltViewModel()
            val companyVm: CompanyViewModel = hiltViewModel()
            val callVm: CallViewModel = hiltViewModel()
            val contactVm: ContactViewModel = hiltViewModel()
            val interviewVm: InterviewViewModel = hiltViewModel()
            val followUpVm: FollowUpViewModel = hiltViewModel()
            TrashScreen(navController, userId = userId.toString(),
                applicationViewModel = applicationVm,
                companyViewModel = companyVm,
                callViewModel = callVm,
                contactViewModel = contactVm,
                interviewViewModel = interviewVm,
                followUpViewModel = followUpVm,
                onDelete = { navController.navigate(Routes.TRASH) },
                onRestore = { navController.navigate(Routes.MAIN) }
            )
        }
    }
}
