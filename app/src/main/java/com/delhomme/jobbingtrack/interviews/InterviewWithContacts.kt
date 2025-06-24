package com.delhomme.jobbingtrack.interviews

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.delhomme.jobbingtrack.commons.entities.InterviewContactCrossRef
import com.delhomme.jobbingtrack.contacts.ContactEntity


data class InterviewWithContacts(
    @Embedded val interview: InterviewEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            InterviewContactCrossRef::class,
            parentColumn = "interviewId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)