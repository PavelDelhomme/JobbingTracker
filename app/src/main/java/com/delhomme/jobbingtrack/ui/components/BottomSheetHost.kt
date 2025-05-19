package com.delhomme.jobbingtrack.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.logic.*
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntrepriseViewModel
import com.delhomme.jobbingtrack.ui.major.appels.AddOrEditAppelScreen
import com.delhomme.jobbingtrack.ui.major.candidatures.AddOrEditCandidatureScreen
import com.delhomme.jobbingtrack.ui.major.contacts.AddOrEditContactScreen
import com.delhomme.jobbingtrack.ui.major.entreprises.AddOrEditEntrepriseScreen
import com.delhomme.jobbingtrack.ui.major.entretiens.AddOrEditEntretienScreen
import com.delhomme.jobbingtrack.ui.major.relances.AddOrEditRelanceScreen

enum class BottomSheetContentType {
    ADD_CANDIDATURE,
    ADD_CONTACT,
    ADD_ENTREPRISE,
    ADD_RELANCE,
    ADD_ENTRETIEN,
    ADD_APPEL,
    NONE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetHost(
    navController: NavHostController,
    visibleContent: BottomSheetContentType,
    linkedCandidatureId: String?,
    onDismissRequest: () -> Unit
) {
    if (visibleContent == BottomSheetContentType.NONE) return

    // 1) On récupère les données via les ViewModels (pas FakeDataProvider)
    val candVm: CandidatureViewModel = viewModel()
    val entpVm: EntrepriseViewModel = viewModel()
    val candidatures    by candVm.candidatures.observeAsState(emptyList())
    val entreprises    by entpVm.entreprises.observeAsState(emptyList())

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
            BottomSheetContentType.ADD_CANDIDATURE -> {
                AddOrEditCandidatureScreen(
                    candidatureId    = null,
                    linkedEntrepriseId  = null,
                    onCancel         = onDismissRequest
                )
            }
            BottomSheetContentType.ADD_CONTACT -> {
                AddOrEditContactScreen(
                    contactId             = null,
                    linkedCandidatureId   = linkedCandidatureId,
                    linkedEntrepriseId    = null,
                    onCancel              = onDismissRequest
                )
            }
            BottomSheetContentType.ADD_ENTREPRISE -> {
                AddOrEditEntrepriseScreen(
                    entrepriseId   = null,
                    onCancel       = onDismissRequest
                )
            }
            BottomSheetContentType.ADD_RELANCE -> {
                AddOrEditRelanceScreen(
                    relanceId               = null,
                    linkedCandidatureId     = linkedCandidatureId,
                    linkedCompanyId         = entrepriseIdFromCandidature,
                    onCancel                = onDismissRequest
                )
            }
            BottomSheetContentType.ADD_ENTRETIEN -> {
                AddOrEditEntretienScreen(
                    entretienId             = null,
                    linkedCandidatureId     = linkedCandidatureId,
                    linkedCompanyId         = entrepriseIdFromCandidature,
                    onCancel                = onDismissRequest
                )
            }
            BottomSheetContentType.ADD_APPEL -> {
                AddOrEditAppelScreen(
                    appelId                 = null,
                    linkedCandidatureId     = linkedCandidatureId,
                    linkedCompanyId         = entrepriseIdFromCandidature,
                    onCancel                = onDismissRequest
                )
            }
            else -> Unit
        }
    }
}
