package com.delhomme.jobbingtrack.ui.contacts

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.utils.toFieldMap

@Composable
fun AddOrEditContactScreen(
    navController: NavController? = null,
    existingContactData: Contact? = null,
    onSave: (Map<String, String>) -> Unit,
    linkedCandidatureId: String? = null
) {
    val fields = listOf(
        FormField(name = "firstName", label = "Prénom", type = FieldType.TEXT, isRequired = true),
        FormField(name = "lastName", label = "Nom", type = FieldType.TEXT, isRequired = true),
        FormField(name = "phone", label = "Téléphone", type = FieldType.PHONE),
        FormField(name = "email", label = "Email", type = FieldType.EMAIL),
        FormField(name = "position", label = "Poste dans l'entreprise", type = FieldType.TEXT),
        FormField(name = "department", label = "Service", type = FieldType.TEXT),
        FormField(
            name = "companyName",
            label = "Entreprise",
            type = FieldType.TEXT,
            isRequired = true
            // éventuellement on pourrait ici aussi préremplir companyName avec la candidature liée
        ),
        FormField(name = "notes", label = "Notes", type = FieldType.MULTILINE_TEXT)
    )

    ReusableForm(
        fields = fields,
        initialValues = existingContactData?.toFieldMap(),
        onSubmit = { formData ->
            onSave(formData)
            navController?.popBackStack()
        }
    )
}
