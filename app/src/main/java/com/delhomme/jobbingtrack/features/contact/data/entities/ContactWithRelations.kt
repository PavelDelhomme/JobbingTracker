package com.delhomme.jobbingtrack.features.contact.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.core.common.relations.WithRelations
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.CVEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity

data class ContactWithRelations(
    @Embedded override val mainEntity: ContactEntity,

    @Relation(parentColumn = "companyId", entityColumn = "id")
    override val companies: List<CompanyEntity> = emptyList(),

    @Relation(parentColumn = "department_type_id", entityColumn = "id")
    val departmentType: DepartmentTypeEntity? = null,

    @Relation(parentColumn = "position_type_id", entityColumn = "id")
    val positionType: PositionTypeEntity? = null,

    // Propriétés liées aux ids stockés dans les listes
    override val applications: List<ApplicationEntity> = emptyList(),
    override val followUps: List<FollowUpEntity> = emptyList(),
    override val calls: List<CallEntity> = emptyList(),
    override val interviews: List<InterviewEntity> = emptyList(),

    // Propriétés non utilisées pour cette entité
    override val contacts: List<ContactEntity> = emptyList(),
    override val events: List<EventEntity> = emptyList(),
    override val cvs: List<CVEntity> = emptyList()
) : WithRelations<ContactEntity>