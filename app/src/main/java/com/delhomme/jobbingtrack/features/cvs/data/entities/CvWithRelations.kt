package com.delhomme.jobbingtrack.features.cvs.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.delhomme.jobbingtrack.core.common.relations.WithRelations
import com.delhomme.jobbingtrack.features.application.data.entities.ApplicationEntity
import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import com.delhomme.jobbingtrack.features.call.data.entities.CallEntity
import com.delhomme.jobbingtrack.features.company.data.entities.CompanyEntity
import com.delhomme.jobbingtrack.features.contact.data.entities.ContactEntity
import com.delhomme.jobbingtrack.features.followup.data.entities.FollowUpEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewEntity
import com.delhomme.jobbingtrack.features.user.data.entities.UserEntity


data class CvWithRelations(
    @Embedded override val mainEntity: CVEntity,
    @Relation(parentColumn = "userId", entityColumn = "id") val user: UserEntity,

    // Relations spécifiques au CV
    @Relation(parentColumn = "id", entityColumn = "cvId", entity = EducationEntity::class)
    val educations: List<EducationEntity> = emptyList(),
    @Relation(parentColumn = "id", entityColumn = "cvId", entity = ExperienceEntity::class)
    val experiences: List<ExperienceEntity> = emptyList(),
    @Relation(parentColumn = "id", entityColumn = "cvId", entity = LanguageEntity::class)
    val languages: List<LanguageEntity> = emptyList(),
    @Relation(parentColumn = "id", entityColumn = "cvId", entity = ProjectEntity::class)
    val projects: List<ProjectEntity> = emptyList(),
    @Relation(parentColumn = "id", entityColumn = "cvId", entity = SkillEntity::class)
    val skills: List<SkillEntity> = emptyList(),
    @Relation(parentColumn = "id", entityColumn = "cvId", entity = CollaboratorEntity::class)
    val collaborators: List<CollaboratorEntity> = emptyList(),

    // Propriétés non utilisées
    override val applications: List<ApplicationEntity> = emptyList(), // ou autre relation si besoin
    override val contacts: List<ContactEntity> = emptyList(),
    override val calls: List<CallEntity> = emptyList(),
    override val followUps: List<FollowUpEntity> = emptyList(),
    override val interviews: List<InterviewEntity> = emptyList(),
    override val companies: List<CompanyEntity> = emptyList(),
    override val events: List<EventEntity> = emptyList(),
    override val cvs: List<CVEntity> = emptyList()
) : WithRelations<CVEntity>
