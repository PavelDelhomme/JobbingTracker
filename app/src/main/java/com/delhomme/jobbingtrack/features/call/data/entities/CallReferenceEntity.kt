package com.delhomme.jobbingtrack.features.call.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.delhomme.jobbingtrack.features.call.data.dao.CallWithContactsCrossRef
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity

data class CallWithCompany(
    @Embedded val call: CallEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity?
)

data class CallWithContacts(
    @Embedded val call: CallEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CallWithContactsCrossRef::class,
            parentColumn = "call_id",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)