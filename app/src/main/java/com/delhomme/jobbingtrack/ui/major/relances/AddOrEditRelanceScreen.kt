package com.delhomme.jobbingtrack.ui.major.relances

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
import com.delhomme.jobbingtrack.data.classes.Relance
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.data.forms.FormSuggestions
import com.delhomme.jobbingtrack.utils.toFieldMap

@Composable
fun AddOrEditRelanceScreen(
    navController: NavController? = null,
    existingRelanceData: Relance? = null,
    linkedCandidatureId: String? = null,
    linkedCompanyId: String? = null,
    onSave: (Map<String, String>) -> Unit,
    onCancel: (() -> Unit)? = null,
) {
    val fields = listOf(
        FormField("date", "Date de relance", FieldType.DATE, isRequired = true),
        FormField("candidatureId", "ID Candidature", FieldType.TEXT, isRequired = true, initialValue = linkedCandidatureId, readOnly = linkedCandidatureId != null),
        FormField("companyId", "ID Entreprise", FieldType.TEXT, isRequired = true, initialValue = linkedCompanyId, readOnly = linkedCompanyId != null),
        FormField("contactId", "ID Contact (optionnel)", FieldType.TEXT),
        FormField(
            "type",
            "Type de relance",
            FieldType.DROPDOWN,
            isRequired = true,
            options = FormSuggestions.relanceTypes,
            onNewOptionAdded = { FormSuggestions.relanceTypes.add(it) },
            onOptionRemoved = { FormSuggestions.relanceTypes.remove(it) },
            onOptionRenamed = { old, new ->
                val index = FormSuggestions.relanceTypes.indexOf(old)
                if (index != -1) FormSuggestions.relanceTypes[index] = new
            }
        ),
        FormField("responseStatus", "Statut réponse (En attente, Positif, Négatif, Aucun retour)", FieldType.TEXT),
        FormField("notes", "Notes", FieldType.MULTILINE_TEXT)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ReusableForm(
            fields = fields,
            initialValues = existingRelanceData?.toFieldMap(),
            onSubmit = { onSave(it) },
            onCancel = onCancel
        )
    }
}