package com.delhomme.jobbingtrack.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.delhomme.jobbingtrack.cvs.repositories.CvRepository
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
                viewModel = ProfileViewModel(
                    profileRepository = (JobbingTrackApp.instance.profileRepository),
                    cvRepository = (JobbingTrackApp.instance.cvRepository)
                )
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
        composable(Routes.ARCHIVES) {
            val applicationVm: ApplicationViewModel = hiltViewModel()
            val companyVm: CompanyViewModel = hiltViewModel()
            val callVm: CallViewModel = hiltViewModel()
            val contactVm: ContactViewModel = hiltViewModel()
            val interviewVm: InterviewViewModel = hiltViewModel()
            val followUpVm: FollowUpViewModel = hiltViewModel()
            ArchiveScreen(navController, userId = userId.toString(),
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

        // — CVs —
        composable(Routes.CVS) { CvsScreen(navController, userId = userId.toString(),
            cvVm = CvViewModel(CvRepository(JobbingTrackApp().cvDao)),
            cvs = emptyList(),
            onItemClick = {},
            onEdit = {},
            onArchive = {},
            onDelete = {},
            onAddClick = { navController.navigate(Routes.CV_ADD) }
        ) }
        composable(Routes.CV_ADD) {
            AddOrEditCvScreen(
                navController,
                cvVm = CvViewModel(JobbingTrackApp()),
                cvs = emptyList(),
                onItemClick = {},
                onEdit = {},
                onArchive = {},
                onDelete = {},
                userId = userId.toString(),
                onAddClick = { navController.navigate(Routes.CV_ADD) },
            )
        }
        composable("${Routes.CV_EDIT}/{cvId}",
            arguments = listOf(navArgument("cvId") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments!!.getString("cvId")!!
            AddOrEditCvScreen(
                userId = userId.toString(),
                cvId = id,
                cvVm = CvViewModel(JobbingTrackApp()),
                cvs = emptyList(),
                onItemClick = {},
                onEdit = {},
                onArchive = {},
                onDelete = {},
                onAddClick = { navController.navigate(Routes.CV_ADD) },
                navController = navController,
            )
        }
        composable("${Routes.CV_DETAIL}/{cvId}",
            arguments = listOf(navArgument("cvId") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments!!.getString("cvId")!!
            CvDetailScreen(
                cvId = id,
                userId = userId.toString(),
                navController = navController
            )
        }


        composable(Routes.EXPERIENCE_ADD) {
            AddOrEditExperienceScreen(
                userId = userId.toString(),
                experienceId = null,
                onCancel = { navController.popBackStack() })
        }
        composable("${Routes.EXPERIENCE_EDIT}/{experienceId}",
            arguments = listOf(navArgument("experienceId") { type = NavType.StringType })
        ) { bs ->
            val id = bs.arguments!!.getString("experienceId")!!
            AddOrEditExperienceScreen(
                userId = userId.toString(),
                experienceId = id,
                onCancel = { navController.popBackStack() }
            )
        }

        composable("${Routes.EXPERIENCE_DETAIL}/{experienceId}",
            arguments = listOf(navArgument("experienceId") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments!!.getString("experienceId")!!
            ExperienceDetailScreen(
                experienceId = id,
                userId = userId.toString(),
                navController = navController
            )
        }

        composable(Routes.SKILL_ADD) {
            AddOrEditSkillScreen(userId = userId.toString(), skillId = null, onCancel = { navController.popBackStack() })
        }
        composable("${Routes.SKILL_EDIT}/{skillId}",
            arguments = listOf(navArgument("skillId") { type = NavType.StringType })
        ) { bs ->
            val id = bs.arguments!!.getString("skillId")!!
            AddOrEditSkillScreen(userId = userId.toString(), skillId = id, onCancel = { navController.popBackStack() })
        }

        // — DÉTAILS SKILL —
        composable("${Routes.SKILL_DETAIL}/{skillId}",
            arguments = listOf(navArgument("skillId") { type = NavType.StringType })
        ) { bs ->
            val id = bs.arguments!!.getString("skillId")!!
            SkillDetailScreen(skillId = id, userId = userId.toString(), navController = navController)
        }

        composable(Routes.FORMATION_ADD) {
            AddOrEditFormationScreen(userId = userId.toString(), formationId = null, onCancel = { navController.popBackStack() })
        }
        composable("${Routes.FORMATION_EDIT}/{formationId}",
            arguments = listOf(navArgument("formationId") { type = NavType.StringType })
        ) { bs ->
            val id = bs.arguments!!.getString("formationId")!!
            AddOrEditFormationScreen(userId = userId.toString(), formationId = id, onCancel = { navController.popBackStack() })
        }


        composable(Routes.PROJECT_ADD) {
            AddOrEditProjectScreen(userId = userId.toString(), projectId = null, onCancel = { navController.popBackStack() })
        }
        composable("${Routes.PROJECT_EDIT}/{projectId}",
            arguments = listOf(navArgument("projectId") { type = NavType.StringType })
        ) { bs ->
            val id = bs.arguments!!.getString("projectId")!!
            AddOrEditProjectScreen(userId = userId.toString(), projectId = id, onCancel = { navController.popBackStack() })
        }
        // — DÉTAILS PROJECT —
        composable("${Routes.PROJECT_DETAIL}/{projectId}",
            arguments = listOf(navArgument("projectId") { type = NavType.StringType })
        ) { bs ->
            val id = bs.arguments!!.getString("projectId")!!
            ProjectDetailScreen(projectId = id, userId = userId.toString(), navController = navController)
        }

        composable(Routes.EDUCATION_ADD) {
            AddOrEditEducationScreen(userId = userId.toString(), educationId = null, onCancel = { navController.popBackStack() })
        }
        composable("${Routes.EDUCATION_EDIT}/{educationId}",
            arguments = listOf(navArgument("educationId") { type = NavType.StringType })
        ) { bs ->
            val id = bs.arguments!!.getString("educationId")!!
            AddOrEditEducationScreen(userId = userId.toString(), educationId = id, onCancel = { navController.popBackStack() })
        }
        // — DÉTAILS EDUCATION —
        composable("${Routes.EDUCATION_DETAIL}/{educationId}",
            arguments = listOf(navArgument("educationId") { type = NavType.StringType })
        ) { bs ->
            val id = bs.arguments!!.getString("educationId")!!
            EducationDetailScreen(educationId = id, userId = userId.toString(), navController = navController)
        }


        composable(Routes.LANGUAGE_ADD) {
            AddOrEditLanguageScreen(userId = userId.toString(), languageId = null, onCancel = { navController.popBackStack() })
        }
        composable("${Routes.LANGUAGE_EDIT}/{languageId}",
            arguments = listOf(navArgument("languageId") { type = NavType.StringType })
        ) { bs ->
            val id = bs.arguments!!.getString("languageId")!!
            AddOrEditLanguageScreen(userId = userId.toString(), languageId = id, onCancel = { navController.popBackStack() })
        }
        // — DÉTAILS LANGUAGE —
        composable("${Routes.LANGUAGE_DETAIL}/{languageId}",
            arguments = listOf(navArgument("languageId") { type = NavType.StringType })
        ) { bs ->
            val id = bs.arguments!!.getString("languageId")!!
            LanguageDetailScreen(languageId = id, userId = userId.toString(), navController = navController)
        }


        composable(Routes.COLLABORATOR_ADD) {
            AddOrEditCollaboratorScreen(userId = userId.toString(), collaboratorId = null, onCancel = { navController.popBackStack() })
        }
        composable("${Routes.COLLABORATOR_EDIT}/{collaboratorId}",
            arguments = listOf(navArgument("collaboratorId") { type = NavType.StringType })
        ) { bs ->
            val id = bs.arguments!!.getString("collaboratorId")!!
            AddOrEditCollaboratorScreen(userId = userId.toString(), collaboratorId = id, onCancel = { navController.popBackStack() })
        }
        // — DÉTAILS COLLABORATOR —
        composable("${Routes.COLLABORATOR_DETAIL}/{collaboratorId}",
            arguments = listOf(navArgument("collaboratorId") { type = NavType.StringType })
        ) { bs ->
            val id = bs.arguments!!.getString("collaboratorId")!!
            CollaboratorDetailScreen(collaboratorId = id, userId = userId.toString(), navController = navController)
        }
    }
}
