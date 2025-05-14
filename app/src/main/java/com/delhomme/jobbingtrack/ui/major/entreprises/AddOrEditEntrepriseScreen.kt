package com.delhomme.jobbingtrack.ui.major.entreprises

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.data.classes.Entreprise
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import com.delhomme.jobbingtrack.utils.toFieldMap
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.utils.toSafeFieldMap

@Composable
fun AddOrEditEntrepriseScreen(
    navController: NavController? = null,
    existingEntrepriseData: Entreprise? = null,
    onSave: (Map<String, String>) -> Unit,
    onCancel: (() -> Unit)? = null
) {
    val fields = listOf(
        FormField("name", "Nom de l'entreprise", FieldType.TEXT, isRequired = true),
        FormField("type", "Secteur d'activité", FieldType.TEXT),
        FormField("phone", "Téléphone", FieldType.PHONE),
        FormField("email", "Email de contact", FieldType.EMAIL),
        FormField("hrEmail", "Email RH", FieldType.EMAIL),
        FormField("address", "Adresse", FieldType.TEXT),
        FormField("notes", "Notes", FieldType.MULTILINE_TEXT)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ReusableForm(
            fields = fields,
            initialValues = existingEntrepriseData?.toFieldMap()?.toSafeFieldMap(),
            onSubmit = { onSave(it) },
            onCancel = onCancel
        )
    }
}
