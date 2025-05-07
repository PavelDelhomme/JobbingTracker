package com.delhomme.jobbingtrack.ui.contacts

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.data.classes.toFormMap
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.forms.FormSuggestions
import com.delhomme.jobbingtrack.utils.toFieldMap

@Composable
fun AddOrEditContactScreen(
    navController: NavController? = null,
    existingContactData: Contact? = null,
    onSave: (Map<String, String>) -> Unit,
    linkedCandidatureId: String? = null,
    onCancel: (() -> Unit)? = null,
) {
    val knownContactTypes = listOf("Manager", "Recruteur", "CTO", "Chargé RH", "CEO")

    val fields = listOf(
        FormField(name = "firstName", label = "Prénom", type = FieldType.TEXT, isRequired = true),
        FormField(name = "lastName", label = "Nom", type = FieldType.TEXT, isRequired = true),
        FormField(name = "phone", label = "Téléphone", type = FieldType.PHONE),
        FormField(name = "email", label = "Email", type = FieldType.EMAIL),
        FormField(
            name = "position",
            label = "Poste",
            type = FieldType.DROPDOWN,
            options = FormSuggestions.contactPositions,
            onOptionRenamed = { old, new ->
                val index = FormSuggestions.contactPositions.indexOf(old)
                if (index != -1) FormSuggestions.contactPositions[index] = new
            }
        ),
        FormField(name = "department", label = "Service", type = FieldType.TEXT),
        FormField(name = "companyName", label = "Entreprise", type = FieldType.SUGGESTION_TEXT, isRequired = true, options = FakeDataProvider.entreprises.map { it.name }),
        FormField(name = "notes", label = "Notes", type = FieldType.MULTILINE_TEXT)
    )

    ReusableForm(
        fields = fields,
        initialValues = existingContactData?.toFormMap(),
        onSubmit = { formData ->
            onSave(formData)
            navController?.popBackStack()
        },
        onCancel = onCancel
    )
}
