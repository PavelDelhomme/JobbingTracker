package com.delhomme.jobbingtrack.commons.ui.forms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.delhomme.jobbingtrack.applications.ui.AddOrEditApplicationScreen
import com.delhomme.jobbingtrack.applications.viewmodels.ApplicationViewModel
import com.delhomme.jobbingtrack.calls.ui.AddOrEditCallScreen
import com.delhomme.jobbingtrack.commons.entities.BottomSheetContentType
import com.delhomme.jobbingtrack.companies.ui.AddOrEditCompanyScreen
import com.delhomme.jobbingtrack.companies.viewmodels.CompanyViewModel
import com.delhomme.jobbingtrack.contacts.ui.AddOrEditContactScreen
import com.delhomme.jobbingtrack.followsup.ui.AddOrEditFollowUpScreen
import com.delhomme.jobbingtrack.interviews.ui.AddOrEditInterviewScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetHost(
    navController: NavHostController,
    visibleContent: BottomSheetContentType,
    userId: String,
    linkedCandidatureId: String?,
    onDismissRequest: () -> Unit,
) {
    if (visibleContent == BottomSheetContentType.NONE) return

    // 1) On récupère les données via les ViewModels (pas FakeDataProvider)
    val candVm: ApplicationViewModel = viewModel()
    val entpVm: CompanyViewModel = viewModel()


    // 2) On colle les StateFlow ici
    //    -> StateFlow<List<Candidature>>
    val candidatures by candVm.activeForUser(userId).observeAsState(emptyList())
    //    -> StateFlow<List<Entreprise>>
    val entreprises by entpVm.activeForUser(userId).observeAsState(emptyList())

    // 2) ON déduit l'entreprise depuis la candidature liée
    val entrepriseIdFromCandidature = linkedCandidatureId
        ?.let { id -> candidatures.find { it.id == id }?.companyId }


    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false, // Permet le swipe vers le bas au lieu d'être fixé
        confirmValueChange = { true } // Pour ajouter des contrôles de validation
    )

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = sheetState,
        modifier = Modifier.fillMaxWidth().imePadding()
    ) {

        when (visibleContent) {
            BottomSheetContentType.ADD_APPLICATION -> {
                AddOrEditApplicationScreen(
                    applicationId    = null,
                    linkedCompanyId  = null,
                    onCancel         = onDismissRequest,
                    userId           = userId
                )
            }
            BottomSheetContentType.ADD_CONTACT -> {
                AddOrEditContactScreen(
                    contactId             = null,
                    linkedApplicationId   = linkedCandidatureId,
                    linkedCompanyId    = null,
                    onCancel              = onDismissRequest,
                    userId                = userId
                )
            }
            BottomSheetContentType.ADD_COMPANY -> {
                AddOrEditCompanyScreen(
                    companyId   = null,
                    onCancel       = onDismissRequest,
                    userId         = userId
                )
            }
            BottomSheetContentType.ADD_RELANCE -> {
                AddOrEditFollowUpScreen(
                    followUpId               = null,
                    linkedApplicationId     = linkedCandidatureId,
                    linkedCompanyId         = entrepriseIdFromCandidature,
                    onCancel                = onDismissRequest,
                    userId                  = userId
                )
            }
            BottomSheetContentType.ADD_INTERVIEW -> {
                AddOrEditInterviewScreen(
                    interviewId             = null,
                    linkedApplicationId     = linkedCandidatureId,
                    linkedCompanyId         = entrepriseIdFromCandidature,
                    onCancel                = onDismissRequest,
                    userId                  = userId
                )
            }
            BottomSheetContentType.ADD_CALL -> {
                AddOrEditCallScreen(
                    callId                 = null,
                    linkedApplicationId     = linkedCandidatureId,
                    linkedCompanyId         = entrepriseIdFromCandidature,
                    onCancel                = onDismissRequest,
                    userId                  = userId
                )
            }
            else -> Unit
        }
    }
}
