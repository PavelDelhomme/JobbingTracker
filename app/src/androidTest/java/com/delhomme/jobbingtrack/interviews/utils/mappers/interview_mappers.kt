package com.delhomme.jobbingtrack.interviews.utils.mappers


import com.delhomme.jobbingtrack.data.local.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.data.classes.interviews.Interview
import com.delhomme.jobbingtrack.data.classes.interviews.InterviewStyle
import com.delhomme.jobbingtrack.data.classes.interviews.InterviewType

fun InterviewWithContacts.toDomain(): Interview {
    val e = this.interview
    return Interview(
        id                  = e.id,
        userId              = e.userId,
        applicationId       = e.applicationId,
        companyId           = e.companyId,
        dateTime            = e.dateTime,
        durationMinutes     = e.durationMinutes,
        location            = e.location,
        contacts_ids        = contacts.map { it.id },
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
