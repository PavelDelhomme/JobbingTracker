package com.delhomme.jobbingtrack.features.user.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.core.common.relations.WithRelations
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.cvs.data.entities.CVEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity
import com.delhomme.jobbingtrack.features.profil.data.entities.ProfilEntity


data class UserWithRelations(
    @Embedded override val mainEntity: UserEntity,
    @Relation(parentColumn = "id", entityColumn = "userId")
    override val cvs: List<CVEntity>,

    @Relation(parentColumn = "id", entityColumn = "userId", entity = ProfilEntity::class)
    val profile: ProfilEntity? = null,


    // Propriétés non utilisées
    override val contacts: List<ContactEntity> = emptyList(),
    override val events: List<EventEntity> = emptyList(),
    override val calls: List<CallEntity> = emptyList(),
    override val interviews: List<InterviewEntity> = emptyList(),
    override val followUps: List<FollowUpEntity> = emptyList(),
    override val applications: List<ApplicationEntity> = emptyList(),
    override val companies: List<CompanyEntity> = emptyList(),
) : WithRelations<UserEntity>