package com.delhomme.jobbingtrack.features.call.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.core.common.relations.WithRelations
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.CVEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity

data class CallWithRelations(
    @Embedded override val mainEntity: CallEntity,
    @Relation(parentColumn = "company_id", entityColumn = "id")
    override val companies: List<CompanyEntity> = emptyList(),
    @Relation(parentColumn = "contact_ids", entityColumn = "id")
    override val contacts: List<ContactEntity> = emptyList(),
    @Relation(parentColumn = "application_id", entityColumn = "id")
    override val applications: List<ApplicationEntity> = emptyList(),
    @Relation(parentColumn = "follow_up_id", entityColumn = "id")
    override val followUps: List<FollowUpEntity> = emptyList(),

    // Propriété non utilisée
    override val calls: List<CallEntity> = emptyList(),
    override val interviews: List<InterviewEntity> = emptyList(),
    override val events: List<EventEntity> = emptyList(),
    override val cvs: List<CVEntity> = emptyList()
) : WithRelations<CallEntity>