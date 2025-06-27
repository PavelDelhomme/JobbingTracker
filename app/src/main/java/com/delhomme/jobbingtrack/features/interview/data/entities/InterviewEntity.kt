package com.delhomme.jobbingtrack.features.interview.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.delhomme.jobbingtrack.core.common.entities.CommonEntityFields
import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider
import com.delhomme.jobbingtrack.core.utils.Converters
import java.util.UUID


@TypeConverters(Converters::class)
@Entity(tableName = "interviews")
data class InterviewEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
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
    val statusId: String?,
    @Embedded val base: CommonEntityFields
) : HasIdProvider
