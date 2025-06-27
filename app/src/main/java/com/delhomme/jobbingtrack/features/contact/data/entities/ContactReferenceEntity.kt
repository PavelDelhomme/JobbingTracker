package com.delhomme.jobbingtrack.features.contact.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity


data class ContactWithCompany(
    @Embedded val contact: ContactEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity
)

