package com.delhomme.jobbingtrack.datas.mappers


import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewStyleEntity
import com.delhomme.jobbingtrack.datas.entities.interviews.InterviewWithContacts
import com.delhomme.jobbingtrack.datas.enumes.InterviewStyle
import com.delhomme.jobbingtrack.datas.enumes.InterviewType
import com.delhomme.jobbingtrack.datas.models.Interview


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

fun InterviewStyleEntity.toDomain(): InterviewStyle {
    return InterviewStyle(
        id = this.id,
        label = this.label,
        userId = this.base.userId,
        syncHash = this.base.syncHash,
        isArchived = this.base.isArchived,
        isDeleted = this.base.isDeleted,
        createdAt = this.base.createdAt,
        updatedAt = this.base.updatedAt,
        deletedAt = this.base.deletedAt,
        archivedAt = this.base.archivedAt
    )
}
