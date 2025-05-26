package com.delhomme.jobbingtrack.ui.major.entreprises

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.ui.components.forms.ReusableForm
import com.delhomme.jobbingtrack.data.local.entities.EntrepriseEntity
import com.delhomme.jobbingtrack.data.viewmodel.EntrepriseViewModel
import java.util.UUID

@Composable
fun AddOrEditEntrepriseScreen(
    entrepriseId: String? = null,
    userId: String? = null,
    viewModel: EntrepriseViewModel = viewModel(),
    onCancel: () -> Unit
) {
    val all = viewModel.entreprises.observeAsState(emptyList()).value
    val existing = all.find { it.id == entrepriseId }

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
                val hash = existing?.syncHash ?: "ent-$id"
                val ent  = EntrepriseEntity(
                    id       = id,
                    name     = form["name"]!!,
                    type     = form["type"],
                    phone    = form["phone"],
                    email    = form["email"],
                    hrEmail  = form["hrEmail"],
                    address  = form["address"],
                    notes    = form["notes"],
                    syncHash = hash,
                    isArchived = existing?.isArchived ?: false,
                    isDeleted  = existing?.isDeleted  ?: false
                )
                viewModel.save(ent)
                onCancel()
            },
            onCancel = onCancel
        )
    }
}
