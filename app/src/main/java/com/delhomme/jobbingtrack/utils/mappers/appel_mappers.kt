package com.delhomme.jobbingtrack.utils.mappers

import com.delhomme.jobbingtrack.data.local.entities.AppelWithEntreprises
import com.delhomme.jobbingtrack.data.classes.Appel

fun AppelWithEntreprises.toDomain(): Appel {
    return Appel(
        id = this.appel.id,
        subject = this.appel.subject,
        companyId = this.appel.companyId,
        contactId = this.appel.contactId,
        candidatureId = this.appel.candidatureId,
        dateTime = this.appel.dateTime,
        notes = this.appel.notes,
        syncHash = this.appel.syncHash,
        relanceId = this.appel.relanceId,
        userId = this.appel.userId,
        isArchived = this.appel.isArchived,
        isDeleted = this.appel.isDeleted,
        createdAt = this.appel.createdAt,
        updatedAt = this.appel.updatedAt,
        deletedAt = this.appel.deletedAt!!,
        archivedAt = this.appel.archivedAt!!
    )
}