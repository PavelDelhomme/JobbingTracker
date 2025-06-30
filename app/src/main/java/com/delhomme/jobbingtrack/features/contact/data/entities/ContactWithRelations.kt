package com.delhomme.jobbingtrack.features.contact.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.core.common.relations.WithRelations
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.CVEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity

data class ContactWithRelations(
    @Embedded override val mainEntity: ContactEntity,
    @Relation(parentColumn = "companyIds", entityColumn = "id")
    override val companies: List<CompanyEntity> = emptyList(),

    @Relation(parentColumn = "applicationIds", entityColumn = "id")
    override val applications: List<ApplicationEntity> = emptyList(),

    @Relation(parentColumn = "callIds", entityColumn = "id")
    override val calls: List<CallEntity> = emptyList(),

    @Relation(parentColumn = "interviewIds", entityColumn = "id")
    override val interviews: List<InterviewEntity> = emptyList(),

    @Relation(parentColumn = "followUpIds", entityColumn = "id")
    override val followUps: List<FollowUpEntity> = emptyList(),

    // Propriétés non utilisées
    override val contacts: List<ContactEntity> = emptyList(),
    override val events: List<EventEntity> = emptyList(),
    override val cvs: List<CVEntity> = emptyList()
) : WithRelations<ContactEntity>