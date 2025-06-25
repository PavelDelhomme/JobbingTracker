package com.delhomme.jobbingtrack.calls

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.delhomme.jobbingtrack.commons.entities.CallContactCrossRef
import com.delhomme.jobbingtrack.contacts.ContactEntity


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
