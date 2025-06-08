package com.delhomme.jobbingtrack.commons.logic

import com.delhomme.jobbingtrack.applications.Application
import com.delhomme.jobbingtrack.events.Event
import java.util.UUID


object EventFactory {

    fun fromApplication(a: Application): Event = Event(
        id = UUID.randomUUID().toString(),
        userId = a.userId,
        relatedObjectId = a.id,
        title = a.title,
        description = a.notes,
        startDate = a.applicationDate,
        endDate = a.applicationDate,
        syncHash = "evt-application-${UUID.randomUUID()}",
        type = "Candidatures",
        createdAt = a.createdAt,
        updatedAt = a.updatedAt,
        deletedAt = a.deletedAt,
        archivedAt = a.archivedAt,
    )

    fun fromFollowUp(f: FollowUp): Event = Event(
        id = UUID.randomUUID().toString(),
        userId = f.userId,
        relatedObjectId = f.id,
        title = "Relance ${f.typeId ?: "Inconnu"} ${f.companyId} ${f.applicationId}",
        description = f.notes,
        startDate = f.date,
        endDate = f.date,
        syncHash = "evt-followup-${UUID.randomUUID()}",
        type = "Relances",
        createdAt = f.createdAt,
        updatedAt = f.updatedAt,
        deletedAt = f.deletedAt,
        archivedAt = f.archivedAt
    )

    fun fromCall(c: Call): Event = Event(
        id = UUID.randomUUID().toString(),
        userId = c.userId,
        relatedObjectId = c.id,
        title = c.subject,
        description = c.notes,
        startDate = c.dateTime,
        endDate = c.dateTime,
        syncHash = "evt-call-${UUID.randomUUID()}",
        type = "Appels",
        createdAt = c.createdAt,
        updatedAt = c.updatedAt,
        deletedAt = c.deletedAt,
        archivedAt = c.archivedAt
    )

    fun fromInterview(i: Interview): Event = Event(
        id = UUID.randomUUID().toString(),
        userId = i.userId,
        relatedObjectId = i.id,
        title = "Entretien ${i.type ?: "?"}",
        description = i.preInterviewNotes,
        startDate = i.dateTime,
        endDate = i.dateTime + ((i.durationMinutes ?: 30) * 60 * 1000L),
        syncHash = "evt-interview-${UUID.randomUUID()}",
        type = "Entretiens",
        createdAt = i.createdAt,
        updatedAt = i.updatedAt,
        deletedAt = i.deletedAt,
        archivedAt = i.archivedAt
    )
}
