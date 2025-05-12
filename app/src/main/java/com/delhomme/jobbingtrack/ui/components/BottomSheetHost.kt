package com.delhomme.jobbingtrack.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.logic.*
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
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = false, // Permet le swipe vers le bas au lieu d'être fixé
            confirmValueChange = { true } // Pour ajouter des contrôles de validation
        )

        ModalBottomSheet(
            onDismissRequest = { onDismissRequest() },
            sheetState = sheetState,
            modifier = Modifier.fillMaxWidth() // Largeur seulement
        ) {
            // NE PAS FORCER DE scroll externe ici, laisse le contenu gérer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
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
                    modifier = Modifier.padding(top = 8.dp)
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
                            linkedCandidatureId = linkedCandidatureId,
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
            }
        }
    }
}
