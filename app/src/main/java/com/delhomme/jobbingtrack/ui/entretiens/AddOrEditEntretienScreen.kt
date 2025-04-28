package com.delhomme.jobbingtrack.ui.entretiens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.ui.components.ReusableForm

@Composable
fun AddOrEditEntretienScreen(
    navController: NavController? = null,
    existingEntretienData: Map<String, String>? = null,
    linkedCandidatureId: String? = null,
    linkedCompanyId: String? = null,
    onSave: (Map<String, String>) -> Unit
) {
    val fields = listOf(
        FormField(
            name = "dateTime",
            label = "Date et heure de l'entretien",
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
            name = "location",
            label = "Lieu de l'entretien",
            type = FieldType.TEXT
        ),
        FormField(
            name = "contacts",
            label = "Contacts liés (IDs séparés par virgule)",
            type = FieldType.TEXT
        ),
        FormField(
            name = "style",
            label = "Style d'entretien (ON_SITE ou REMOTE)",
            type = FieldType.TEXT,
            isRequired = true
        ),
        FormField(
            name = "type",
            label = "Type d'entretien (RH ou TECHNICAL)",
            type = FieldType.TEXT,
            isRequired = true
        ),
        FormField(
            name = "preInterviewNotes",
            label = "Notes avant entretien",
            type = FieldType.MULTILINE_TEXT
        ),
        FormField(
            name = "interviewNotes",
            label = "Notes pendant entretien",
            type = FieldType.MULTILINE_TEXT
        ),
        FormField(
            name = "postInterviewNotes",
            label = "Notes après entretien",
            type = FieldType.MULTILINE_TEXT
        ),
        FormField(
            name = "returnDate",
            label = "Date de retour attendu",
            type = FieldType.DATE
        ),
        FormField(
            name = "testsNeeded",
            label = "Tests requis ?",
            type = FieldType.BOOLEAN
        ),
        FormField(
            name = "testsDeadline",
            label = "Date limite pour les tests",
            type = FieldType.DATE
        )
    )

    ReusableForm(
        fields = fields,
        initialValues = existingEntretienData,
        onSubmit = { formData ->
            onSave(formData)
            navController?.popBackStack()
        }
    )
}
