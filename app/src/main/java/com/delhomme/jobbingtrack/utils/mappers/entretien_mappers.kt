package com.delhomme.jobbingtrack.utils.mappers

import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts
import com.delhomme.jobbingtrack.data.classes.Entretien
import com.delhomme.jobbingtrack.data.classes.EntretienStyle
import com.delhomme.jobbingtrack.data.classes.EntretienType

fun EntretienWithContacts.toDomain(): Entretien {
    val e = this.entretien
    return Entretien(
        id                  = e.id,
        userId              = e.userId,
        candidatureId       = e.candidatureId,
        companyId           = e.companyId,
        dateTime            = e.dateTime,
        durationMinutes     = e.durationMinutes,
        location            = e.location,
        contacts            = contacts.map { it.id },
        style               = e.style?.let { EntretienStyle.valueOf(it) },
        type                = e.type?.let { EntretienType.valueOf(it) },
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
