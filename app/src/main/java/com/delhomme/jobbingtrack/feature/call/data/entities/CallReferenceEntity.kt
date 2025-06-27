package com.delhomme.jobbingtrack.feature.call.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


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
            CallContactCrossRef::class,
            parentColumn = "callId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)
