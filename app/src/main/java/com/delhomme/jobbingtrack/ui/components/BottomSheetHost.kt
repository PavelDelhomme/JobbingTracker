package com.delhomme.jobbingtrack.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.logic.*
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
    visibleContent: BottomSheetContentType,
    linkedCandidatureId: String?,
    onDismissRequest: () -> Unit
) {
    if (visibleContent == BottomSheetContentType.NONE) return

    val entrepriseIdFromCandidature = linkedCandidatureId?.let { id ->
        FakeDataProvider.candidatures.find { it.id == id }?.companyId
    }

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
            BottomSheetContentType.ADD_CANDIDATURE -> AddOrEditCandidatureScreen(
                navController = null,
                existingCandidatureData = null,
                onSave = {
                    saveCandidatureFromForm(it)
                    onDismissRequest()
                },
                onCancel = onDismissRequest
            )

            BottomSheetContentType.ADD_CONTACT -> AddOrEditContactScreen(
                navController = null,
                existingContactData = null,
                linkedEntrepriseId = entrepriseIdFromCandidature,
                onSave = {
                    saveContactFromForm(it)
                    onDismissRequest()
                },
                onCancel = onDismissRequest
            )

            BottomSheetContentType.ADD_ENTREPRISE -> AddOrEditEntrepriseScreen(
                navController = null,
                existingEntrepriseData = null,
                onSave = {
                    saveEntrepriseFromForm(it)
                    onDismissRequest()
                },
                onCancel = onDismissRequest
            )

            BottomSheetContentType.ADD_RELANCE -> AddOrEditRelanceScreen(
                navController = null,
                existingRelanceData = null,
                linkedCandidatureId = linkedCandidatureId,
                linkedCompanyId = entrepriseIdFromCandidature,
                onSave = {
                    saveRelanceFromForm(it)
                    onDismissRequest()
                },
                onCancel = onDismissRequest
            )

            BottomSheetContentType.ADD_ENTRETIEN -> AddOrEditEntretienScreen(
                navController = null,
                existingEntretienData = null,
                linkedCandidatureId = linkedCandidatureId,
                linkedCompanyId = entrepriseIdFromCandidature,
                onSave = {
                    saveEntretienFromForm(it)
                    onDismissRequest()
                },
                onCancel = onDismissRequest
            )

            BottomSheetContentType.ADD_APPEL -> AddOrEditAppelScreen(
                navController = null,
                existingAppelData = null,
                linkedCandidatureId = linkedCandidatureId,
                linkedCompanyId = entrepriseIdFromCandidature,
                onSave = {
                    saveAppelFromForm(it)
                    onDismissRequest()
                },
                onCancel = onDismissRequest
            )

            else -> {}
        }
        /*
        // NE PAS FORCER DE scroll externe ici, laisse le contenu gérer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                //.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = when (visibleContent) {
                    BottomSheetContentType.ADD_CANDIDATURE -> "Nouvelle candidature"
                    BottomSheetContentType.ADD_CONTACT -> "Nouveau contact"
                    BottomSheetContentType.ADD_ENTREPRISE -> "Nouvelle entreprise"
                    BottomSheetContentType.ADD_RELANCE -> "Nouvelle relance"
                    BottomSheetContentType.ADD_ENTRETIEN -> "Nouvel entretien"
                    BottomSheetContentType.ADD_APPEL -> "Nouvel appel"
                    else -> ""
                },
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 4.dp)
            )

            // ✅ Le contenu gère son scroll lui-même (comme ReusableForm déjà scrollable)
            when (visibleContent) {
                BottomSheetContentType.ADD_CANDIDATURE -> {
                    AddOrEditCandidatureScreen(
                        navController = null,
                        existingCandidatureData = null,
                        onSave = {
                            saveCandidatureFromForm(it)
                            onDismissRequest()
                        },
                        onCancel = { onDismissRequest() }
                    )
                }

                BottomSheetContentType.ADD_CONTACT -> {
                    AddOrEditContactScreen(
                        navController = null,
                        existingContactData = null,
                        linkedEntrepriseId = entrepriseIdFromCandidature,
                        onSave = {
                            saveContactFromForm(it)
                            onDismissRequest()
                        },
                        onCancel = { onDismissRequest() }
                    )
                }

                BottomSheetContentType.ADD_ENTREPRISE -> {
                    AddOrEditEntrepriseScreen(
                        navController = null,
                        existingEntrepriseData = null,
                        onSave = {
                            saveEntrepriseFromForm(it)
                            onDismissRequest()
                        },
                        onCancel = { onDismissRequest() }
                    )
                }

                BottomSheetContentType.ADD_RELANCE -> {
                    AddOrEditRelanceScreen(
                        navController = null,
                        existingRelanceData = null,
                        linkedCandidatureId = linkedCandidatureId,
                        onSave = {
                            saveRelanceFromForm(it)
                            onDismissRequest()
                        },
                        onCancel = { onDismissRequest() }
                    )
                }

                BottomSheetContentType.ADD_ENTRETIEN -> {
                    AddOrEditEntretienScreen(
                        navController = null,
                        existingEntretienData = null,
                        linkedCompanyId = null,
                        linkedCandidatureId = linkedCandidatureId,
                        onSave = {
                            saveEntretienFromForm(it)
                            onDismissRequest()
                        },
                        onCancel = { onDismissRequest() }
                    )
                }

                BottomSheetContentType.ADD_APPEL -> {
                    AddOrEditAppelScreen(
                        navController = null,
                        existingAppelData = null,
                        linkedCandidatureId = linkedCandidatureId,
                        onSave = {
                            saveAppelFromForm(it)
                            onDismissRequest()
                        },
                        onCancel = { onDismissRequest() }
                    )
                }

                else -> {}
            }
        }*/
    }
}
