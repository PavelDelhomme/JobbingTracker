package com.delhomme.jobbingtrack.ui.major.appels

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        FormField("dateTime", "Date et heure de l'appel", FieldType.DATE, isRequired = true),
        FormField("subject", "Objet de l'appel", FieldType.SUGGESTION_TEXT, options = listOf("Appel de suivi", "Prise de contact", "Demande d'informations"), isRequired = true),
        FormField("companyId", "Entreprise", FieldType.SELECTION, initialValue = linkedCompanyId),
        FormField("contactId", "Contact (optionnel)", FieldType.SELECTION),
        FormField("candidatureId", "Candidature (optionnel)", FieldType.SELECTION, initialValue = linkedCandidatureId),
        FormField("relanceId", "Relance liée (optionnel)", FieldType.SELECTION),
        FormField("notes", "Notes sur l'appel", FieldType.MULTILINE_TEXT)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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
}
