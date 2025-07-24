package com.delhomme.jobbingtrack.features.interview.domain.model

import com.delhomme.jobbingtrack.core.common.entities.HasIdProvider


data class Interview(
    // Data d'héritage de HasIdProvider
    override val id: String,
    val userId: String,
    val syncHash: String,
    var isArchived: Boolean = false,
    var isDeleted: Boolean = false,
    var createdAt: Long,
    var updatedAt: Long,
    var deletedAt: Long? = null,
    var archivedAt: Long? = null,

    // Data propre
    val applicationId: String,
    val companyId: String,
    val dateTime: Long,
    val durationMinutes: Int?,
    val location: String?,
    val contactsIds: List<String>,
    val style: InterviewStyle?,
    val type: InterviewType?,
    val preInterviewNotes: String?,
    val interviewNotes: String?,
    val postInterviewNotes: String?,
    val returnDate: Long?,
    val testsNeeded: Boolean,
    val testsDeadline: Long?,
) : HasIdProvider
