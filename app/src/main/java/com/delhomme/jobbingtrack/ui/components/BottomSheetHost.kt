package com.delhomme.jobbingtrack.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            onDismissRequest = { onDismissRequest() },
            dragHandle = {},
            modifier = Modifier.fillMaxHeight(0.9f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
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
                    style = MaterialTheme.typography.headlineSmall
                )

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
                            linkedCandidatureId = linkedCandidatureId,
                            onSave = {
                                println("Ajout contact effectué: $it")
                                onDismissRequest()
                            }
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
                            linkedCandidatureId = linkedCandidatureId,
                            onSave = {
                                println("Ajout relance effectué: $it")
                                onDismissRequest()
                            }
                        )
                    }
                    BottomSheetContentType.ADD_ENTRETIEN -> {
                        AddOrEditEntretienScreen(
                            navController = null,
                            existingEntretienData = null,
                            linkedCandidatureId = linkedCandidatureId,
                            onSave = {
                                println("Ajout entretien effectué: $it")
                                onDismissRequest()
                            }
                        )
                    }
                    BottomSheetContentType.ADD_APPEL -> {
                        AddOrEditAppelScreen(
                            navController = null,
                            existingAppelData = null,
                            linkedCandidatureId = linkedCandidatureId,
                            onSave = {
                                println("Ajout appel effectué: $it")
                                onDismissRequest()
                            }
                        )
                    }
                    else -> {}
                }
            }
        }
    }
}