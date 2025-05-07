package com.delhomme.jobbingtrack.data.logic

import com.delhomme.jobbingtrack.data.classes.*
import java.util.UUID

object EventFactory {

    fun fromCandidature(c: Candidature): Evenement = Evenement(
        id = UUID.randomUUID().toString(),
        relatedObjectId = c.id,
        title = c.title,
        description = c.notes,
        startDate = c.applicationDate,
        endDate = c.applicationDate,
        syncHash = "evt-candidature-${UUID.randomUUID()}",
        type = "Candidatures"
    )

    fun fromRelance(r: Relance): Evenement = Evenement(
        id = UUID.randomUUID().toString(),
        relatedObjectId = r.id,
        title = "Relance ${r.type?.name ?: "Inconnu"} ${r.companyId} ${r.candidatureId}",
        description = r.notes,
        startDate = r.date,
        endDate = r.date,
        syncHash = "evt-relance-${UUID.randomUUID()}",
        type = "Relances"
    )

    fun fromAppel(a: Appel): Evenement = Evenement(
        id = UUID.randomUUID().toString(),
        relatedObjectId = a.id,
        title = a.subject,
        description = a.notes,
        startDate = a.dateTime,
        endDate = a.dateTime,
        syncHash = "evt-appel-${UUID.randomUUID()}",
        type = "Appels"
    )

    fun fromEntretien(e: Entretien): Evenement = Evenement(
        id = UUID.randomUUID().toString(),
        relatedObjectId = e.id,
        title = "Entretien ${e.type?.name ?: "?"}",
        description = e.preInterviewNotes,
        startDate = e.dateTime,
        endDate = e.dateTime + ((e.durationMinutes ?: 30) * 60 * 1000L),
        syncHash = "evt-entretien-${UUID.randomUUID()}",
        type = "Entretiens"
    )
}
