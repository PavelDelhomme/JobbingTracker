package com.delhomme.jobbingtrack.ui.major.entretiens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.delhomme.jobbingtrack.data.classes.Contact
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.data.forms.FormSuggestions
import com.delhomme.jobbingtrack.data.local.EntretienEntity
import com.delhomme.jobbingtrack.data.viewmodel.CandidatureViewModel
import com.delhomme.jobbingtrack.data.viewmodel.ContactViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntrepriseViewModel
import com.delhomme.jobbingtrack.data.viewmodel.EntretienViewModel
import com.delhomme.jobbingtrack.ui.components.ContactSelectorField
import com.delhomme.jobbingtrack.ui.components.EntitySelectorField
import com.delhomme.jobbingtrack.ui.components.ModernDateTimePickerField
import com.delhomme.jobbingtrack.ui.components.ReusableForm
import com.delhomme.jobbingtrack.utils.toFieldMap
import java.util.UUID


@SuppressLint("UnrememberedMutableState")
@Composable
fun AddOrEditEntretienScreen(
    entretienId: String? = null,
    linkedCandidatureId: String? = null,
    linkedCompanyId: String? = null,
    onCancel: () -> Unit,
    entVm: EntretienViewModel = viewModel(),
    candVm: CandidatureViewModel = viewModel(),
    entpVm: EntrepriseViewModel = viewModel(),
    contactVm: ContactViewModel = viewModel(),
) {
    // 1) Charger l’entretien + ses contacts
    val allWithContacts by entVm.entretiens.observeAsState(emptyList())
    val existingWith = allWithContacts.find { it.entretien.id == entretienId }
    val existingEnt = existingWith?.entretien

    // 2) États locaux
    var selCandId      by remember { mutableStateOf(existingEnt?.candidatureId ?: linkedCandidatureId ?: "") }
    var selEntpId      by remember { mutableStateOf(existingEnt?.companyId ?: linkedCompanyId ?: "") }
    var selContacts    by remember { mutableStateOf(existingWith?.contacts ?: emptyList()) }
    var dateTime by remember { mutableStateOf(existingEnt?.dateTime ?: System.currentTimeMillis()) }

    // 3) Listes Reactives
    val candidats  by candVm.candidatures.observeAsState(emptyList())
    val entreprises by entpVm.entreprises.observeAsState(emptyList())
    val contacts   by contactVm.contacts.observeAsState(emptyList())

    // 4) Calcul du companyId
    val companyId = candidats
        .firstOrNull { it.id == selCandId }
        ?.companyId
        ?: selEntpId

    // 5) Vos champs de formulaire
    val fields = listOf(
        FormField("location", "Lieu de l'entretien", FieldType.TEXT),
        FormField("style", "Style d'entretien", FieldType.DROPDOWN, isRequired = true, options = FormSuggestions.entretienStyles),
        FormField("type", "Type d'entretien", FieldType.DROPDOWN, isRequired = true, options = FormSuggestions.entretienTypes),
        FormField("preInterviewNotes", "Notes avant entretien", FieldType.MULTILINE_TEXT),
        FormField("interviewNotes", "Notes pendant entretien", FieldType.MULTILINE_TEXT),
        FormField("postInterviewNotes", "Notes après entretien", FieldType.MULTILINE_TEXT),
        FormField("returnDate", "Date de retour attendu", FieldType.DATE),
        FormField("testsNeeded", "Tests requis ?", FieldType.BOOLEAN),
        FormField("testsDeadline", "Date limite pour les tests", FieldType.DATE),
        FormField("durationMinutes", "Durée (min)", FieldType.NUMBER)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Date & heure
        ModernDateTimePickerField(
            label = "Date & heure",
            initialMillis = dateTime,
            onDateTimeSelected = { dateTime = it }
        )

        EntitySelectorField(
            label = "Candidature",
            selectedEntityId = selCandId,
            allEntities = candidats,
            getEntityLabel = { it.title },
            onEntitySelected = { selCandId = it.id },
            allowCreation = false
        )

        // Si pas de candidature, choisir directement l'entreprise
        if (selCandId.isBlank()) {
            EntitySelectorField(
                label = "Entreprise",
                selectedEntityId = selEntpId,
                allEntities = entreprises,
                getEntityLabel = { it.name },
                onEntitySelected = { selEntpId = it.id },
                allowCreation = false
            )
        }

        // Affichage non-éditable de l’entreprise
        OutlinedTextField(
            value    = entreprises.firstOrNull { it.id == companyId }?.name.orEmpty(),
            onValueChange = {},
            label = { Text("Entreprise finale") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Multi-sélecteur de contacts
        ContactSelectorField(
            label = "Contacts (optionnel)",
            allContacts = contacts.filter { it.entrepriseId == companyId },
            selectedContacts = selContacts,
            onContactsChanged = { selContacts = it }
        )

        ReusableForm(
            fields = fields,
            initialValues = existingEnt?.toFieldMap() ?: emptyMap(),
            onSubmit = { form ->
                val id   = existingEnt?.id ?: UUID.randomUUID().toString()
                val hash = existingEnt?.syncHash ?: "ent-$id"

                val ent = EntretienEntity(
                        id                = id,
                        candidatureId     = selCandId,
                        companyId         = companyId,
                        dateTime          = dateTime,
                        durationMinutes   = form["durationMinutes"]?.toIntOrNull() ?: existingEnt?.durationMinutes,
                        location          = form["location"],
                        style             = form["style"],
                        type              = form["type"],
                        preInterviewNotes = form["preInterviewNotes"],
                        interviewNotes    = form["interviewNotes"],
                        postInterviewNotes= form["postInterviewNotes"],
                        returnDate        = form["returnDate"]?.toLongOrNull(),
                        testsNeeded       = form["testsNeeded"].toBoolean(),
                        testsDeadline     = form["testsDeadline"]?.toLongOrNull(),
                        syncHash          = hash,
                        isArchived        = existingEnt?.isArchived == true,
                        isDeleted         = existingEnt?.isDeleted == true
                    )
                entVm.save(ent, selContacts.map { it.id })
                onCancel()
            },
            onCancel = onCancel
        )
    }
}