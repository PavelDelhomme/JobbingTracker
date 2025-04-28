package com.delhomme.jobbingtrack.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.delhomme.jobbingtrack.ui.appels.AddOrEditAppelScreen
import com.delhomme.jobbingtrack.ui.candidatures.AddOrEditCandidatureScreen
import com.delhomme.jobbingtrack.ui.contacts.AddOrEditContactScreen
import com.delhomme.jobbingtrack.ui.entreprises.AddOrEditEntrepriseScreen
import com.delhomme.jobbingtrack.ui.entretiens.AddOrEditEntretienScreen
import com.delhomme.jobbingtrack.ui.relances.AddOrEditRelanceScreen

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
    visibleContent: BottomSheetContentType,
    linkedCandidatureId: String?,
    onDismissRequest: () -> Unit
) {
    if (visibleContent != BottomSheetContentType.NONE) {
        ModalBottomSheet(
            onDismissRequest = { onDismissRequest() }
        ) {
            when (visibleContent) {
                BottomSheetContentType.ADD_CANDIDATURE -> {
                    AddOrEditCandidatureScreen(
                        navController = null,
                        existingCandidatureData = null,
                        onSave = {
                            println("Ajout candidature effectué: $it")
                            onDismissRequest()
                        }
                    )
                }
                BottomSheetContentType.ADD_CONTACT -> {
                    AddOrEditContactScreen(
                        navController = null,
                        existingContactData = null,
                        onSave = {
                            println("Ajout contact effectué: $it")
                            onDismissRequest()
                        },
                        linkedCandidatureId = linkedCandidatureId
                    )
                }
                BottomSheetContentType.ADD_ENTREPRISE -> {
                    AddOrEditEntrepriseScreen(
                        navController = null,
                        existingEntrepriseData = null,
                        onSave = {
                            println("Ajout entreprise effectué: $it")
                            onDismissRequest()
                        }
                    )
                }
                BottomSheetContentType.ADD_RELANCE -> {
                    AddOrEditRelanceScreen(
                        navController = null,
                        existingRelanceData = null,
                        onSave = {
                            println("Ajout relance effectué: $it")
                            onDismissRequest()
                        },
                        linkedCandidatureId = linkedCandidatureId
                    )
                }
                BottomSheetContentType.ADD_ENTRETIEN -> {
                    AddOrEditEntretienScreen(
                        navController = null,
                        existingEntretienData = null,
                        onSave = {
                            println("Ajout relance effectué: $it")
                            onDismissRequest()
                        },
                        linkedCandidatureId = linkedCandidatureId
                    )
                }
                BottomSheetContentType.ADD_APPEL -> {
                    AddOrEditAppelScreen(
                        navController = null,
                        existingAppelData = null,
                        onSave = {
                            println("Ajout appel effectué: $it")
                            onDismissRequest()
                        },
                        linkedCandidatureId = linkedCandidatureId
                    )
                }
                else -> {}
            }
        }
    }
}
