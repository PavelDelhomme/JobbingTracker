package com.delhomme.jobbingtrack.contacts.ui


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delhomme.jobbingtrack.data.viewmodel.Contact.ContactViewModel
import com.delhomme.jobbingtrack.ui.components.forms.selectors.EntitySelectorField
import com.delhomme.jobbingtrack.ui.components.forms.ReusableForm
import com.delhomme.jobbingtrack.data.forms.FieldType
import com.delhomme.jobbingtrack.data.forms.FormField
import com.delhomme.jobbingtrack.data.local.entities.contact.ContactEntity
import com.delhomme.jobbingtrack.data.local.entities.company.CompanyEntity
import com.delhomme.jobbingtrack.data.viewmodel.Application.ApplicationViewModel
import com.delhomme.jobbingtrack.data.viewmodel.Company.CompanyViewModel
import com.delhomme.jobbingtrack.utils.resolveCompanyId
import java.util.UUID

@Composable
fun AddOrEditContactScreen(
    contactId: String? = null,
    linkedApplicationId: String? = null,
    linkedCompanyId: String? = null,
    userId: String,
    onCancel: () -> Unit,
    contactVm: ContactViewModel               = viewModel(),
    entrepriseVm: CompanyViewModel         = viewModel(),
    candidatureVm: ApplicationViewModel       = viewModel()
) {
    // 1) Charger les listes
    val allContacts by contactVm.allForUser(userId).observeAsState(emptyList())
    val allEntreprises by entrepriseVm.allForUser(userId).observeAsState(emptyList())
    val allCandidats   by candidatureVm.allForUser(userId).observeAsState(emptyList())

    // 2) Chercher l’existant si on édite
    val existing = contactId?.let { id -> allContacts.find { it.id == id } }

    // 3) État local pour l’entreprise liée
    var selCompanyId      by remember { mutableStateOf(existing?.companyId ?: linkedCompanyId.orEmpty()) }
    var selCandidatureId  by remember { mutableStateOf(linkedApplicationId ?: existing?.applicationId.orEmpty()) }

    var finalCompanyId = resolveCompanyId(
        existingContact = existing,
        applications = allCandidats,
        followUps = emptyList(),
        linkedApplicationId = selCandidatureId,
        fallbackCompanyId = selCompanyId
    )


    // 4) Les champs du formulaire
    val fields = listOf(
        FormField("firstName", "Prénom",             FieldType.TEXT,      isRequired = true),
        FormField("lastName",  "Nom",                FieldType.TEXT,      isRequired = true),
        FormField("phone",     "Téléphone",          FieldType.PHONE),
        FormField("email",     "Email",              FieldType.EMAIL),
        FormField("position",  "Poste",              FieldType.TEXT),
        FormField("department","Département",        FieldType.TEXT),
        FormField("notes",     "Notes",              FieldType.MULTILINE_TEXT)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Sélecteur d’entreprise
        EntitySelectorField(
            label            = "Entreprise liée",
            selectedEntityId = finalCompanyId,
            allEntities      = allEntreprises,
            getEntityLabel   = { it.name },
            onEntitySelected = { finalCompanyId = it.id },
            allowCreation    = true,
            onCreateEntity   = { name ->
                // si vous voulez permettre la création inline
                val newId = UUID.randomUUID().toString()
                entrepriseVm.save(
                    CompanyEntity(
                        id        = newId,
                        name      = name,
                        type      = null,
                        phone     = null,
                        email     = null,
                        hrEmail   = null,
                        address   = null,
                        notes     = null,
                        syncHash  = "ent-$newId",
                        userId    = userId
                    )
                )
                finalCompanyId = newId
            }
        )

        // Champ “Candidature” (optionnel)
        EntitySelectorField(
            label            = "Lier à une candidature (opt.)",
            selectedEntityId = selCandidatureId,
            allEntities      = allCandidats.filter { it.companyId == finalCompanyId },
            getEntityLabel   = { it.title },
            onEntitySelected = { selCandidatureId = it.id },
            allowCreation    = false
        )


        // Formulaire Réutilisable
        ReusableForm(
            fields = fields,
            initialValues = existing?.run {
                mapOf(
                    "firstName"  to (firstName ?: ""),
                    "lastName"   to (lastName  ?: ""),
                    "phone"      to (phone     ?: ""),
                    "email"      to (email     ?: ""),
                    "position"   to (position  ?: ""),
                    "department" to (department?: ""),
                    "notes"      to (notes     ?: "")
                )
            } ?: emptyMap(),
            onSubmit = { form ->
                val id = existing?.id ?: UUID.randomUUID().toString()
                val hash = existing?.syncHash ?: "ct-$id"
                val entity = ContactEntity(
                    id           = id,
                    firstName    = form["firstName"],
                    lastName     = form["lastName"],
                    phone        = form["phone"],
                    email        = form["email"],
                    position     = form["position"],
                    department   = form["department"],
                    companyId = finalCompanyId.toString(),
                    applicationId = selCandidatureId,
                    notes        = form["notes"],
                    syncHash     = hash,
                    isArchived   = existing?.isArchived ?: false,
                    isDeleted    = existing?.isDeleted ?: false,
                    userId       = userId,
                )

                contactVm.save(entity)
                onCancel()
            },
            onCancel = onCancel
        )
    }
}
