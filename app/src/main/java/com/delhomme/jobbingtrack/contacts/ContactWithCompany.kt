package com.delhomme.jobbingtrack.contacts

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.datas.entities.companies.CompanyEntity
import com.delhomme.jobbingtrack.datas.entities.contacts.ContactEntity


data class ContactWithCompany(
    @Embedded val contact: ContactEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity
)

