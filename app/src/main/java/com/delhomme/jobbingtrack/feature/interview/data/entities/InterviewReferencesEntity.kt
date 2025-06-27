package com.delhomme.jobbingtrack.feature.interview.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


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