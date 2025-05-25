package com.delhomme.jobbingtrack.utils

import com.delhomme.jobbingtrack.data.local.entities.*

fun resolveCompanyId(
    existingAppel: AppelEntity? = null,
    existingRelance: RelanceEntity? = null,
    existingEntretien: EntretienEntity? = null,
    existingContact: ContactEntity? = null,
    candidatures: List<CandidatureEntity>,
    relances: List<RelanceEntity>,
    linkedCandidatureId: String? = null,
    linkedRelanceId: String? = null,
    fallbackCompanyId: String? = null
): String? {
    // 1. Priorité : appel ou entretien ou relance ou contact existant
    listOfNotNull(
        existingAppel?.companyId,
        existingEntretien?.companyId,
        existingRelance?.companyId,
        existingContact?.entrepriseId
    ).firstOrNull()?.takeIf { it.isNotBlank() }?.let { return it }

    // 2. Via une candidature liée (manuelle ou déduite via appel / relance)
    val candidatureId = listOfNotNull(
        existingAppel?.candidatureId,
        existingRelance?.candidatureId,
        existingEntretien?.candidatureId,
        existingContact?.candidatureId,
        linkedCandidatureId,
        linkedRelanceId?.let { id -> relances.find { it.id == id }?.candidatureId }
    ).firstOrNull()

    candidatureId?.let { id ->
        candidatures.find { it.id == id }?.companyId?.takeIf { it.isNotBlank() }?.let { return it }
    }

    // 3. Fallback direct
    return fallbackCompanyId?.takeIf { it.isNotBlank() }
}