package com.delhomme.jobbingtrack.features.interview.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "interviews")
@TypeConverters(Converters::class)
data class InterviewEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    override val userId: String,

    val applicationId: String,
    val companyId: String,
    val dateTime: Long,
    val durationMinutes: Int?,
    val location: String?,
    val preInterviewNotes: String?,
    val interviewNotes: String?,
    val postInterviewNotes: String?,
    val returnDate: Long?,
    val testsNeeded: Boolean,
    val testsDeadline: Long?,
    val styleId: String?,
    val typeId: String?,
    val statusId: String?
) : BaseEntity() {
    // Constructeur secondaire pour la migration depuis l'ancien format
    constructor(
        id: String = UUID.randomUUID().toString(),
        applicationId: String,
        companyId: String,
        dateTime: Long,
        durationMinutes: Int?,
        location: String?,
        preInterviewNotes: String?,
        interviewNotes: String?,
        postInterviewNotes: String?,
        returnDate: Long?,
        testsNeeded: Boolean,
        testsDeadline: Long?,
        styleId: String?,
        typeId: String?,
        statusId: String?,
        base: CommonEntityFields
    ) : this(
        id = id,
        userId = base.userId,
        applicationId = applicationId,
        companyId = companyId,
        dateTime = dateTime,
        durationMinutes = durationMinutes,
        location = location,
        preInterviewNotes = preInterviewNotes,
        interviewNotes = interviewNotes,
        postInterviewNotes = postInterviewNotes,
        returnDate = returnDate,
        testsNeeded = testsNeeded,
        testsDeadline = testsDeadline,
        styleId = styleId,
        typeId = typeId,
        statusId = statusId
    ) {
        // Copier les métadonnées depuis CommonEntityFields
        this.createdAt = base.createdAt
        this.updatedAt = base.updatedAt
        this.isDeleted = base.isDeleted
        this.isArchived = base.isArchived
        this.deletedAt = base.deletedAt
        this.archivedAt = base.archivedAt
        this.syncHash = base.syncHash
        this.entityHash = base.syncHash
    }
}

// Entités liées
@Entity(tableName = "interview_styles")
@TypeConverters(Converters::class)
data class InterviewStyleEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    override val userId: String,
    val label: String
) : BaseEntity()

@Entity(tableName = "interview_types")
@TypeConverters(Converters::class)
data class InterviewTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    override val userId: String,
    val label: String
) : BaseEntity()

@Entity(tableName = "interview_status")
@TypeConverters(Converters::class)
data class InterviewStatusEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    override val userId: String,
    val label: String
) : BaseEntity()