package com.delhomme.jobbingtrack.core.utils


import com.delhomme.jobbingtrack.features.calendar.data.entities.EventEntity
import com.delhomme.jobbingtrack.features.calendar.domain.model.Event
import com.delhomme.jobbingtrack.features.call.data.entities.CallWithCompany
import com.delhomme.jobbingtrack.features.call.domain.model.Call
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewStyleEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewTypeEntity
import com.delhomme.jobbingtrack.features.interview.data.entities.InterviewWithContacts
import com.delhomme.jobbingtrack.features.interview.domain.model.Interview
import com.delhomme.jobbingtrack.features.interview.domain.model.InterviewStyle
import com.delhomme.jobbingtrack.features.interview.domain.model.InterviewType


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
        dateTime = entity.call.timestamp,
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



fun EventEntity.toDomain(): Event {
    return Event(
        id = id,
        title = title,
        description = description,
        startDate = startDate ?: 0,
        endDate = endDate ?: 0,
        type = typeId,
        userId = base.userId,
        syncHash = base.syncHash,
        isArchived = base.isArchived,
        isDeleted = base.isDeleted,
        createdAt = base.createdAt,
        updatedAt = base.updatedAt,
        deletedAt = base.deletedAt ?: 0,
        archivedAt = base.archivedAt ?: 0,
        relatedObjectId = relatedObjectId,
    )
}



fun CallWithCompany.toDomain(): Call {
    return Call(
        id = this.call.id,
        subject = this.call.subject,
        companyId = this.call.companyId,
        contactId = this.call.contactId,
        applicationId = this.call.applicationId,
        dateTime = this.call.timestamp,
        notes = this.call.notes,
        syncHash = this.call.base.syncHash,
        followUpId = this.call.followUpId,
        userId = this.call.base.userId,
        isArchived = this.call.base.isArchived,
        isDeleted = this.call.base.isDeleted,
        createdAt = this.call.base.createdAt,
        updatedAt = this.call.base.updatedAt,
        deletedAt = this.call.base.deletedAt,
        archivedAt = this.call.base.archivedAt
    )
}
