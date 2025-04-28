package com.delhomme.jobbingtrack.ui.entreprises

import androidx.compose.runtime.Composable
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField

@Composable
fun AddOrEditEntrepriseScreen(
    navController: NavController? = null,
    existingEntrepriseData: Map<String, String>? = null,
    onSave: (Map<String, String>) -> Unit
) {
    val fields = listOf(
        FormField(name = "name", label = "Nom de l'entreprise", type = FieldType.TEXT, isRequired = true),
        FormField(name = "type", label = "Secteur d'activité", type = FieldType.TEXT),
        FormField(name = "phone", label = "Téléphone", type = FieldType.PHONE),
        FormField(name = "email", label = "Email de contact", type = FieldType.EMAIL),
        FormField(name = "hrEmail", label = "Email du RH", type = FieldType.EMAIL),
        FormField(name = "address", label = "Adresse", type = FieldType.TEXT),
        FormField(name = "notes", label = "Notes sur l'entreprise", type = FieldType.MULTILINE_TEXT)
    )

    ReusableForm(
        fields = fields,
        onSubmit = { formData ->
            onSave(formData)
            navController?.popBackStack()
        }
    )
}
