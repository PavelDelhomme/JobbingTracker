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

    @Relation(parentColumn = "typeId", entityColumn = "id")
    val eventType: EventTypeEntity? = null,

    @Relation(parentColumn = "applicationId", entityColumn = "id")
    override val applications: List<ApplicationEntity> = emptyList(),

    @Relation(parentColumn = "companyId", entityColumn = "id")
    override val companies: List<CompanyEntity> = emptyList(),

    @Relation(parentColumn = "contactId", entityColumn = "id")
    override val contacts: List<ContactEntity> = emptyList(),

    @Relation(parentColumn = "followUpId", entityColumn = "id")
    override val followUps: List<FollowUpEntity> = emptyList(),

    @Relation(parentColumn = "interviewId", entityColumn = "id")
    override val interviews: List<InterviewEntity> = emptyList(),

    // Propriétés non utilisées pour cette entité
    override val calls: List<CallEntity> = emptyList(),
    override val events: List<EventEntity> = emptyList(),
    override val cvs: List<CVEntity> = emptyList()
) : WithRelations<EventEntity>