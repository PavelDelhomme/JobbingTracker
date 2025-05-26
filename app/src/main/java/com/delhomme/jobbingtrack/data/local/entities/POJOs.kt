package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class EntretienWithContacts(
    @Embedded val entretien: EntretienEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            EntretienContactCrossRef::class,
            parentColumn = "entretienId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)

data class AppelWithEntreprises(
    @Embedded val appel: AppelEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val entreprise: EntrepriseEntity
)