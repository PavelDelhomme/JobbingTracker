package com.delhomme.jobbingtrack.interviews.utils.mappers

import com.delhomme.jobbingtrack.commons.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.interviews.Interview
import com.delhomme.jobbingtrack.interviews.enumes.InterviewStyle
import com.delhomme.jobbingtrack.interviews.enumes.InterviewType
import kotlin.collections.map


fun InterviewWithContacts.toDomain(): Interview {
    val e = this.interview.toDomain()
    return Interview(
        id                  = e.id,
        userId              = e.userId,
        applicationId       = e.applicationId,
        companyId           = e.companyId,
        dateTime            = e.dateTime,
        durationMinutes     = e.durationMinutes,
        location            = e.location,
        contacts_ids        = contacts.map { it.base.id },
        style               = e.style?.let { InterviewStyle.valueOf(it) },
        type                = e.type?.let { InterviewType.valueOf(it) },
        preInterviewNotes   = e.preInterviewNotes,
        interviewNotes      = e.interviewNotes,
        postInterviewNotes  = e.postInterviewNotes,
        returnDate          = e.returnDate,
        testsNeeded         = e.testsNeeded,
        testsDeadline       = e.testsDeadline,
        syncHash            = e.syncHash,
        isArchived          = e.isArchived,
        isDeleted           = e.isDeleted,
        createdAt           = e.createdAt,
        updatedAt           = e.updatedAt,
        deletedAt           = e.deletedAt ?: 0L,
        archivedAt          = e.archivedAt ?: 0L
    )
}
