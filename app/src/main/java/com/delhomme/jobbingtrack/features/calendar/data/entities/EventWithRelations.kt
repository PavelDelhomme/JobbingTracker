package com.delhomme.jobbingtrack.features.calendar.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.core.common.relations.WithRelations
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.CVEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity

data class EventWithRelations(
    @Embedded override val mainEntity: EventEntity,
    @Relation(parentColumn="relatedObjectId", entityColumn="id", entity= ApplicationEntity::class)
    override val applications: List<ApplicationEntity> = emptyList(),
    @Relation(parentColumn="relatedObjectId", entityColumn="id", entity= CallEntity::class)
    override val calls: List<CallEntity> = emptyList(),
    @Relation(parentColumn = "relatedObjectId", entityColumn = "id", entity = InterviewEntity::class)
    override val interviews: List<InterviewEntity> = emptyList(),
    @Relation(parentColumn = "relatedObjectId", entityColumn = "id", entity = FollowUpEntity::class)
    override val followUps: List<FollowUpEntity> = emptyList(),

    // Propriétés non utilisées
    override val contacts: List<ContactEntity> = emptyList(),
    override val companies: List<CompanyEntity> = emptyList(),
    override val events: List<EventEntity> = emptyList(),
    override val cvs: List<CVEntity> = emptyList()
) : WithRelations<EventEntity>