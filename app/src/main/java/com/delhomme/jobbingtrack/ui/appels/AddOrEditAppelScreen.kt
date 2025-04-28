package com.delhomme.jobbingtrack.ui.appels

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Appel
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.utils.toFieldMap

@Composable
fun AddOrEditAppelScreen(
    navController: NavController? = null,
    existingAppelData: Appel? = null,
    onSave: (Map<String, String>) -> Unit,
    linkedCandidatureId: String? = null // Ajout par défaut null
) {
    val fields = listOf(
        FormField(
            name = "dateTime",
            label = "Date et heure de l'appel",
            type = FieldType.DATE,
            isRequired = true
        ),
        FormField(
            name = "subject",
            label = "Objet de l'appel",
            type = FieldType.TEXT,
            isRequired = true
        ),
        FormField(
            name = "companyId",
            label = "ID de l'entreprise",
            type = FieldType.TEXT,
            isRequired = true
        ),
        FormField(
            name = "contactId",
            label = "ID du contact (optionnel)",
            type = FieldType.TEXT
        ),
        FormField(
            name = "candidatureId", // Correction ici : ce n'est pas "applicationId" dans tes modèles mais bien "candidatureId"
            label = "ID de la candidature (optionnel)",
            type = FieldType.TEXT,
            initialValue = linkedCandidatureId,
            readOnly = linkedCandidatureId != null
        ),
        FormField(
            name = "relanceId",
            label = "ID de la relance (optionnel)",
            type = FieldType.TEXT
        ),
        FormField(
            name = "notes",
            label = "Notes sur l'appel",
            type = FieldType.MULTILINE_TEXT
        )
    )

    ReusableForm(
        fields = fields,
        initialValues = existingAppelData?.toFieldMap(),
        onSubmit = { formData ->
            onSave(formData)
            navController?.popBackStack()
        }
    )
}
