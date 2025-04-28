package com.delhomme.jobbingtrack.ui.relances

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Relance
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.utils.toFieldMap

@Composable
fun AddOrEditRelanceScreen(
    navController: NavController? = null,
    existingRelanceData: Relance? = null,
    linkedCandidatureId: String? = null,
    linkedCompanyId: String? = null,
    onSave: (Map<String, String>) -> Unit
) {
    val fields = listOf(
        FormField(
            name = "date",
            label = "Date de la relance",
            type = FieldType.DATE,
            isRequired = true
        ),
        FormField(
            name = "candidatureId",
            label = "ID de la candidature",
            type = FieldType.TEXT,
            isRequired = true,
            initialValue = linkedCandidatureId,
            readOnly = linkedCandidatureId != null
        ),
        FormField(
            name = "companyId",
            label = "ID de l'entreprise",
            type = FieldType.TEXT,
            isRequired = true,
            initialValue = linkedCompanyId,
            readOnly = linkedCompanyId != null
        ),
        FormField(
            name = "contactId",
            label = "ID du contact (optionnel)",
            type = FieldType.TEXT
        ),
        FormField(
            name = "type",
            label = "Type de relance (Appel, Email, Sur place)",
            type = FieldType.TEXT,
            isRequired = true
        ),
        FormField(
            name = "responseStatus",
            label = "Statut de la relance (En attente, Positif, Négatif, Aucun retour)",
            type = FieldType.TEXT
        ),
        FormField(
            name = "notes",
            label = "Notes de la relance",
            type = FieldType.MULTILINE_TEXT
        )
    )

    ReusableForm(
        fields = fields,
        initialValues = existingRelanceData?.toFieldMap(),
        onSubmit = { formData ->
            onSave(formData)
            navController?.popBackStack()
        }
    )
}