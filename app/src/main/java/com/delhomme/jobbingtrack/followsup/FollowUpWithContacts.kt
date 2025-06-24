package com.delhomme.jobbingtrack.followsup

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.delhomme.jobbingtrack.datas.entities.contacts.ContactEntity
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpContactCrossRef
import com.delhomme.jobbingtrack.datas.entities.followsups.FollowUpEntity


data class FollowUpWithContacts(
    @Embedded val followUp: FollowUpEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            FollowUpContactCrossRef::class,
            parentColumn = "followUpId",
            entityColumn = "contactId"
        )
    )
    val contacts: List<ContactEntity>
)
