package com.delhomme.jobbingtrack.ui.major.appels

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Appel
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
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
    var selectedCandidatureId by remember { mutableStateOf(existingAppelData?.candidatureId ?: linkedCandidatureId) }
    var selectedContactId by remember { mutableStateOf(existingAppelData?.contactId ?: "") }

    // On déduit automatiquement l'entreprise depuis la candidature
    val entrepriseIdFromCandidature = selectedCandidatureId?.let {
        FakeDataProvider.candidatures.find { c -> c.id == it }?.companyId
    }

    val effectiveCompanyId = entrepriseIdFromCandidature ?: linkedCompanyId

    val fields = listOf(
        FormField("dateTime", "Date et heure de l'appel", FieldType.DATE, isRequired = true),
        FormField("subject", "Objet de l'appel", FieldType.SUGGESTION_TEXT, options = listOf("Appel de suivi", "Prise de contact", "Demande d'informations"), isRequired = true),
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
