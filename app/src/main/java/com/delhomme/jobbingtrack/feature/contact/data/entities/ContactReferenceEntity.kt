package com.delhomme.jobbingtrack.feature.contact.data.entities

import androidx.room.Embedded
import androidx.room.Relation


data class ContactWithCompany(
    @Embedded val contact: ContactEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity
)

