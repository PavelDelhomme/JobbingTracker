package com.delhomme.jobbingtrack.ui.major.appels

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
    linkedCandidatureId: String? = null,
    linkedCompanyId: String? = null,
    onSave: (Map<String, String>) -> Unit,
    onCancel: (() -> Unit)? = null,
) {
    val fields = listOf(
        FormField(name = "dateTime", label = "Date et heure de l'appel", type = FieldType.DATE, isRequired = true),
        FormField(name = "subject", label = "Objet de l'appel", type = FieldType.SUGGESTION_TEXT, options = listOf("Appel de suivi", "Prise de contact", "Demande d'informations"), isRequired = true),
        FormField(
            name = "companyId",
            label = "Entreprise",
            type = FieldType.SELECTION,
            initialValue = linkedCompanyId
        ),
        FormField(
            name = "contactId",
            label = "Contact (optionnel)",
            type = FieldType.SELECTION
        ),
        FormField(
            name = "candidatureId",
            label = "Candidature (optionnel)",
            type = FieldType.SELECTION,
            initialValue = linkedCandidatureId
        ),
        FormField(
            name = "relanceId",
            label = "Relance liée (optionnel)",
            type = FieldType.SELECTION
        ),
        FormField(name = "notes", label = "Notes sur l'appel", type = FieldType.MULTILINE_TEXT)
    )

    ReusableForm(
        fields = fields,
        initialValues = existingAppelData?.toFieldMap(),
        onSubmit = { formData ->
            onSave(formData)
            navController?.popBackStack()
        },
        onCancel = onCancel
    )
}
