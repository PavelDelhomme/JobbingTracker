package com.delhomme.jobbingtrack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delhomme.jobbingtrack.data.interfaces.HasIdProvider
import java.util.UUID

@Entity(tableName = "interviews")
data class InterviewEntity(
    @PrimaryKey override val id: String = UUID.randomUUID().toString(),
    val userId: String,
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
    val syncHash: String,
    val isArchived: Boolean = false,
    val isDeleted: Boolean = false,
    var createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis(),
    var deletedAt: Long? = null,
    var archivedAt: Long? = null
) : HasIdProvider
