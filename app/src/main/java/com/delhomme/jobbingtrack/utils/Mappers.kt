package com.delhomme.jobbingtrack.utils.mappers

import com.delhomme.jobbingtrack.calls.CallWithCompany
import com.delhomme.jobbingtrack.datas.entities.interviews.*
import com.delhomme.jobbingtrack.datas.enumes.InterviewStyle
import com.delhomme.jobbingtrack.datas.enumes.InterviewType
import com.delhomme.jobbingtrack.datas.models.Call
import com.delhomme.jobbingtrack.datas.models.Interview

// Mappers pour les entités d'entretiens
fun mapToInterviewStyle(entity: InterviewStyleEntity): InterviewStyle {
    return InterviewStyle(
        id = entity.id,
        label = entity.label,
        userId = entity.base.userId,
        syncHash = entity.base.syncHash,
        isArchived = entity.base.isArchived,
        isDeleted = entity.base.isDeleted,
        createdAt = entity.base.createdAt,
        updatedAt = entity.base.updatedAt,
        deletedAt = entity.base.deletedAt,
        archivedAt = entity.base.archivedAt
    )
}

fun mapToInterviewType(entity: InterviewTypeEntity): InterviewType {
    return InterviewType(
        id = entity.id,
        label = entity.label,
        userId = entity.base.userId,
        syncHash = entity.base.syncHash,
        isArchived = entity.base.isArchived,
        isDeleted = entity.base.isDeleted,
        createdAt = entity.base.createdAt,
        updatedAt = entity.base.updatedAt,
        deletedAt = entity.base.deletedAt,
        archivedAt = entity.base.archivedAt
    )
}

fun mapToInterview(
    entity: InterviewWithContacts,
    style: InterviewStyle?,
    type: InterviewType?
): Interview {
    val e = entity.interview
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
        contacts_ids = entity.contacts.map { it.id },
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

// Mapper pour les appels (si nécessaire)
fun mapToCall(entity: CallWithCompany): Call {
    return Call(
        id = entity.call.id,
        subject = entity.call.subject,
        companyId = entity.call.companyId,
        contactId = entity.call.contactId,
        applicationId = entity.call.applicationId,
        dateTime = entity.call.dateTime,
        notes = entity.call.notes,
        syncHash = entity.call.base.syncHash,
        followUpId = entity.call.followUpId,
        userId = entity.call.base.userId,
        isArchived = entity.call.base.isArchived,
        isDeleted = entity.call.base.isDeleted,
        createdAt = entity.call.base.createdAt,
        updatedAt = entity.call.base.updatedAt,
        deletedAt = entity.call.base.deletedAt!!,
        archivedAt = entity.call.base.archivedAt!!
    )
}
