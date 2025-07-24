package com.delhomme.jobbingtrack.features.interview.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.model.BaseEntity
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID

@Entity(tableName = "interviews")
@TypeConverters(Converters::class)
data class InterviewEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,

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
) : BaseEntity()

// Entités liées
@Entity(tableName = "interview_styles")
@TypeConverters(Converters::class)
data class InterviewStyleEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,
    val label: String
) : BaseEntity()

@Entity(tableName = "interview_types")
@TypeConverters(Converters::class)
data class InterviewTypeEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,
    val label: String
) : BaseEntity()

@Entity(tableName = "interview_status")
@TypeConverters(Converters::class)
data class InterviewStatusEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") override var userId: String,
    val label: String
) : BaseEntity()