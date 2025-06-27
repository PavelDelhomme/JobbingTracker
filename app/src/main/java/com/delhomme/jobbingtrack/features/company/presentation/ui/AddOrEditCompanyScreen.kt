package com.delhomme.jobbingtrack.features.company.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.company.presentation.viewmodel.CompanyViewModel
import com.delhomme.jobbingtrack.ui.shared.FieldType
import com.delhomme.jobbingtrack.ui.shared.FormField
import com.delhomme.jobbingtrack.ui.shared.ReusableForm
import java.util.UUID


@Composable
fun AddOrEditCompanyScreen(
    companyId: String? = null,
    userId: String? = null,
    viewModel: CompanyViewModel = hiltViewModel(),
    onCancel: () -> Unit
) {
    val all = viewModel.allForUser(userId = userId.toString()).observeAsState(emptyList()).value
    val existing = all.find { it.id == companyId }

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
            initialValues = existing?.let {
                mapOf(
                    "name"    to it.name,
                    "type"    to (it.type ?: ""),
                    "phone"   to (it.phone ?: ""),
                    "email"   to (it.email ?: ""),
                    "hrEmail" to (it.hrEmail ?: ""),
                    "address" to (it.address ?: ""),
                    "notes"   to (it.notes ?: "")
                )
            } ?: emptyMap(),
            onSubmit = { form ->
                val id   = existing?.id ?: UUID.randomUUID().toString()
                val hash = existing?.base?.syncHash ?: "ent-$id"
                val ent  = CompanyEntity(
                    id       = id,
                    name     = form["name"]!!,
                    type     = form["type"],
                    phone    = form["phone"],
                    email    = form["email"],
                    hrEmail  = form["hrEmail"],
                    address  = form["address"],
                    notes    = form["notes"],
                    base     = existing?.base ?: CommonEntityFields(
                        userId = userId ?: "",
                        syncHash = hash
                    )
                )
                viewModel.save(ent)
                onCancel()
            },
            onCancel = onCancel
        )
    }
}
