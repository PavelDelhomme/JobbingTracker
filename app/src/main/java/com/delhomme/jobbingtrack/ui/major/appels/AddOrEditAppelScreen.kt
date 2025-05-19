package com.delhomme.jobbingtrack.ui.major.appels

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import androidx.navigation.NavController
import com.delhomme.jobbingtrack.data.fake.FakeDataProvider.entreprises
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.data.local.entities.AppelEntity
import com.delhomme.jobbingtrack.data.viewmodel.AppelViewModel
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.RelanceViewModel
import com.delhomme.jobbingtrack.ui.components.EntitySelectorField
import java.util.UUID


@Composable
fun AddOrEditAppelScreen(
    appelId: String? = null,
    linkedCandidatureId: String? = null,
    linkedCompanyId: String? = null,
    linkedContactId: String? = null,
    linkedRelanceId: String? = null,
    appelVm: AppelViewModel = viewModel(),
    candVm: CandidatureViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
    relanceVm: RelanceViewModel = viewModel(),
    onCancel: () -> Unit
) {

    // 1) Observe les listes depuis tes ViewModels
    val appels       by appelVm.appels.observeAsState(emptyList())
    val candidats by candVm.candidatures.observeAsState(emptyList())
    val contacts     by contactVm.contacts.observeAsState(emptyList())
    val relances    by relanceVm.relances.observeAsState(emptyList())

    // 2) Si on édite, on récupère l'existant
    val existing = appels.find { it.id == appelId }

    // 3) Etats pour les selecteurs
    var selCandId by remember { mutableStateOf(existing?.candidatureId ?: linkedCandidatureId) }
    var selContactId     by remember { mutableStateOf(existing?.contactId  ?: linkedContactId) }
    var selRelanceId by remember { mutableStateOf(existing?.relanceId ?: linkedRelanceId) }

    // 4) Calcul de l'entreprise
    val compFromCand = selCandId
        ?.let { id -> candidats.find { it.id == id }?.companyId }
    val effectiveCompanyId = compFromCand ?: linkedCompanyId.orEmpty();

    // 5) Définir les champs du formulaire
    val fields = listOf(
        FormField("dateTime", "Date et heure de l'appel", FieldType.DATE, isRequired = true),
        FormField("subject",  "Objet de l'appel",               FieldType.SUGGESTION_TEXT,
            options = listOf("Appel de suivi","Prise de contact","Demande d'informations"),
            isRequired = true),
        FormField("notes", "Notes sur l'appel", FieldType.MULTILINE_TEXT)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Sélecteur de candidature (optionnel)
        if (linkedCandidatureId == null) {
            EntitySelectorField(
                label = "Candidature (opt.)",
                selectedEntityId = selCandId,
                allEntities = candidats,
                getEntityLabel = { it.title },
                onEntitySelected = { selCandId = it.id },
                allowCreation = false
            )
        }
        // Sélecteur de relance (optionnel)
        if (linkedRelanceId == null) {
            EntitySelectorField(
                label = "Relance (opt.)",
                selectedEntityId = selRelanceId,
                allEntities = relances,
                getEntityLabel = { it.notes.toString() },
                onEntitySelected = { selRelanceId = it.id },
                allowCreation = false
            )
        }

        // Affichage de l’entreprise (non éditable)
        OutlinedTextField(
            value    = entreprises.find { it.id == effectiveCompanyId }?.name.orEmpty(),
            onValueChange = {},
            label = { Text("Entreprise") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Contact optionnel avec création rapide
        EntitySelectorField(
            label = "Contact (optionnel)",
            selectedEntityId = selContactId,
            allEntities = contacts.filter { it.entrepriseId == effectiveCompanyId },
            getEntityLabel = { "${it.firstName} ${it.lastName}" },
            onEntitySelected = { selContactId = it.id },
            allowCreation = true,
            onCreateEntity = { fullName ->
                // Appeler ContactViewModel pour créer un nouveau contact
                // contactVm.createContact(...)
            }
        )

        ReusableForm(
            fields = fields,
            initialValues = existing?.let {
                mutableMapOf(
                    "dateTime" to it.dateTime.toString(),
                    "subject"  to it.subject,
                    "notes" to (it.notes         ?: "")
                )
            } ?: emptyMap(),
            onSubmit = { form ->
                val id = existing?.id ?: UUID.randomUUID().toString()
                val entity = AppelEntity(
                    id           = id,
                    subject      = form["subject"]!!,
                    companyId    = effectiveCompanyId,
                    contactId    = selCandId?.ifBlank { null },
                    candidatureId= selCandId,
                    relanceId    = selRelanceId?.ifBlank { null },
                    dateTime     = form["dateTime"]!!.toLong(),
                    notes        = form["notes"]?.takeIf(String::isNotBlank),
                    syncHash     = existing?.syncHash ?: "app-$id",
                    isArchived   = existing?.isArchived ?: false,
                    isDeleted    = existing?.isDeleted  ?: false
                )
                appelVm.save(entity)
                onCancel()
            },
            onCancel = onCancel
        )
    }
}
