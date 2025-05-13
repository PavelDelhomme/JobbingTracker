package com.delhomme.jobbingtrack.ui.major.contacts

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.data.classes.toFormMap
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.data.forms.FormSuggestions
import com.delhomme.jobbingtrack.ui.components.EntitySelectorField
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import com.delhomme.jobbingtrack.utils.getEntityById

@Composable
fun AddOrEditContactScreen(
    navController: NavController? = null,
    existingContactData: Contact? = null,
    linkedEntrepriseId: String? = null,
    onSave: (Map<String, String>) -> Unit,
    onCancel: (() -> Unit)? = null,
) {
    val entreprises = FakeDataProvider.entreprises
    var selectedCompanyId by remember { mutableStateOf(existingContactData?.entrepriseId ?: linkedEntrepriseId) }

    val fields = listOf(
        FormField("firstName", "Prénom", FieldType.TEXT, isRequired = true),
        FormField("lastName", "Nom", FieldType.TEXT, isRequired = true),
        FormField("phone", "Téléphone", FieldType.PHONE),
        FormField("email", "Email", FieldType.EMAIL),
        FormField("position", "Poste", FieldType.DROPDOWN, options = FormSuggestions.contactPositions),
        FormField("department", "Service", FieldType.TEXT),
        FormField("notes", "Notes", FieldType.MULTILINE_TEXT)
    )

    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        EntitySelectorField(
            label = "Entreprise liée",
            selectedEntityId = selectedCompanyId,
            allEntities = entreprises,
            getEntityLabel = { it.name },
            onEntitySelected = { selectedCompanyId = it.id },
            allowCreation = true,
            onCreateEntity = { name ->
                val newEntreprise = FakeDataProvider.addEntrepriseIfNotExists(name)
                selectedCompanyId = newEntreprise.id
            }
        )

        ReusableForm(
            fields = fields,
            initialValues = existingContactData?.toFormMap(),
            onSubmit = { formData ->
                onSave(formData + mapOf("companyId" to (selectedCompanyId ?: "")))
                navController?.popBackStack()
            },
            onCancel = onCancel
        )
    }
}
