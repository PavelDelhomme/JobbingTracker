package com.delhomme.jobbingtrack.features.followup.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.core.common.relations.WithRelations
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.CVEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity

data class FollowUpWithRelations(
    @Embedded override val mainEntity: FollowUpEntity,
    @Relation(parentColumn = "application_id", entityColumn = "id")
    override val applications: List<ApplicationEntity> = emptyList(),
    @Relation(parentColumn = "company_id", entityColumn = "id")
    override val companies: List<CompanyEntity> = emptyList(),
    @Relation(parentColumn = "contact_id", entityColumn = "id")
    override val contacts: List<ContactEntity> = emptyList(),

    // Relations pour les types/status/platforms
    @Relation(parentColumn = "type_id", entityColumn = "id")
    val type: FollowUpTypeEntity? = null,
    @Relation(parentColumn = "platform_id", entityColumn = "id")
    val platform: FollowUpPlatformEntity? = null,
    @Relation(parentColumn = "status_id", entityColumn = "id")
    val status: FollowUpStatusEntity? = null,

    // Propriétés non utilisées pour cette entité
    override val calls: List<CallEntity> = emptyList(),
    override val interviews: List<InterviewEntity> = emptyList(),
    override val events: List<EventEntity> = emptyList(),
    override val followUps: List<FollowUpEntity> = emptyList(),
    override val cvs: List<CVEntity> = emptyList()
) : WithRelations<FollowUpEntity>