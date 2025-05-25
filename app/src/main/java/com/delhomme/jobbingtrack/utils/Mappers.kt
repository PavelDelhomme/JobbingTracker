package com.delhomme.jobbingtrack.utils

import com.delhomme.jobbingtrack.data.local.entities.EntretienWithContacts

fun EntretienWithContacts.toDomain(): com.delhomme.jobbingtrack.data.classes.Entretien {
    val e = this.entretien
    return com.delhomme.jobbingtrack.data.classes.Entretien(
        id                  = e.id,
        userId              = e.userId,
        candidatureId       = e.candidatureId,
        companyId           = e.companyId,
        dateTime            = e.dateTime,
        durationMinutes     = e.durationMinutes,
        location            = e.location,
        // on extrait juste les ids de contact ici
        contacts            = contacts.map { it.id },
        style               = e.style?.let { com.delhomme.jobbingtrack.data.classes.EntretienStyle.valueOf(it) },
        type                = e.type?.let { com.delhomme.jobbingtrack.data.classes.EntretienType.valueOf(it) },
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

fun AppelWithEntreprises.toDomain(): com.delhomme.jobbingtrack.data.classes.Appel {

}