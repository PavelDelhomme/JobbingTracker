package com.delhomme.jobbingtrack.interviews.utils.mappers

import com.delhomme.jobbingtrack.commons.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.interviews.Interview
import com.delhomme.jobbingtrack.interviews.enumes.InterviewStyle
import com.delhomme.jobbingtrack.interviews.enumes.InterviewType
import kotlin.collections.map


fun InterviewWithContacts.toDomain(
    style: InterviewStyle?,
    type: InterviewType?
): Interview {
    val e = this.interview

    return Interview(
        id = e.id,
        userId = e.base.userId,
        syncHash = e.base.syncHash,
        isArchived = e.base.isArchived,
        isDeleted = e.base.isDeleted,
        createdAt = e.base.createdAt,
        updatedAt = e.base.updatedAt,
        deletedAt = e.base.deletedAt,
        archivedAt = e.base.archivedAt,
        applicationId = e.applicationId,
        companyId = e.companyId,
        dateTime = e.dateTime,
        durationMinutes = e.durationMinutes,
        location = e.location,
        contacts_ids = this.contacts.map { it.id }, // ou it.base.id si nécessaire
        style = style,
        type = type,
        preInterviewNotes = e.preInterviewNotes,
        interviewNotes = e.interviewNotes,
        postInterviewNotes = e.postInterviewNotes,
        returnDate = e.returnDate,
        testsNeeded = e.testsNeeded,
        testsDeadline = e.testsDeadline
    )
}