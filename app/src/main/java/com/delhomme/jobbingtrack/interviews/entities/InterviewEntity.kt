package com.delhomme.jobbingtrack.interviews.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.commons.fields.CommonEntityFields
import com.delhomme.jobbingtrack.commons.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "interviews")
data class InterviewEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val applicationId: String,
    val companyId: String,
    val dateTime: Long,
    val durationMinutes: Int?,
    val location: String?,
    val style: String?,
    val type: String?,
    val preInterviewNotes: String?,
    val interviewNotes: String?,
    val postInterviewNotes: String?,
    val returnDate: Long?,
    val testsNeeded: Boolean,
    val testsDeadline: Long?,
    val styleId: String?,
    val typeId: String?,
    val contactsIds: List<String> = emptyList(),
    @Embedded val base: CommonEntityFields
) : HasIdProvider
