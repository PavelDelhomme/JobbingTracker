package com.delhomme.jobbingtrack.ui.candidatures

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import androidx.navigation.NavController

@Composable
fun AddOrEditCandidatureScreen(
    navController: NavController? = null,
    existingCandidatureData: Map<String, String>? = null, // null pour ajout
    onSave: (Map<String, String>) -> Unit
) {
    val fields = listOf(
        FormField(name = "title", label = "Titre de l'offre", type = FieldType.TEXT, isRequired = true),
        FormField(name = "companyName", label = "Entreprise", type = FieldType.TEXT, isRequired = true),
        FormField(name = "applicationDate", label = "Date de candidature", type = FieldType.DATE, isRequired = true),
        FormField(name = "platform", label = "Plateforme de la candidature", type = FieldType.TEXT),
        FormField(name = "contractType", label = "Type de contrat", type = FieldType.TEXT),
        FormField(name = "location", label = "Lieu du poste", type = FieldType.TEXT),
        FormField(name = "isSpontaneous", label = "Candidature Spontanée", type = FieldType.BOOLEAN),
        FormField(name = "notes", label = "Notes de la candidature", type = FieldType.MULTILINE_TEXT)
    )

    // todo pré-remplissage des champs avec existingCandidatureData

    ReusableForm(
        fields = fields,
        onSubmit = { formData ->
            onSave(formData)
            navController?.popBackStack() // Retour automatique après sauvegarde
        }
    )
}