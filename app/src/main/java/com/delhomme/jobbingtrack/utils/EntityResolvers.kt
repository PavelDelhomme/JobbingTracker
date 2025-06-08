package com.delhomme.jobbingtrack.utils

import com.delhomme.jobbingtrack.data.local.entities.application.ApplicationEntity
import com.delhomme.jobbingtrack.data.local.entities.call.CallEntity
import com.delhomme.jobbingtrack.data.local.entities.contact.ContactEntity
import com.delhomme.jobbingtrack.data.local.entities.followup.FollowUpEntity
import com.delhomme.jobbingtrack.data.local.entities.interview.InterviewEntity

fun resolveCompanyId(
    existingCall: CallEntity? = null,
    existingFollowUp: FollowUpEntity? = null,
    existingInterview: InterviewEntity? = null,
    existingContact: ContactEntity? = null,
    applications: List<ApplicationEntity>,
    followUps: List<FollowUpEntity>,
    linkedApplicationId: String? = null,
    linkedFollowUpId: String? = null,
    fallbackCompanyId: String? = null
): String? {
    // 1. Priorité : appel ou entretien ou relance ou contact existant
    listOfNotNull(
        existingCall?.companyId,
        existingInterview?.companyId,
        existingFollowUp?.companyId,
        existingContact?.companyId
    ).firstOrNull()?.takeIf { it.isNotBlank() }?.let { return it }

    // 2. Via une candidature liée (manuelle ou déduite via appel / relance)
    val candidatureId = listOfNotNull(
        existingCall?.applicationId,
        existingFollowUp?.applicationId,
        existingInterview?.applicationId,
        existingContact?.applicationIds?.firstOrNull { id ->
            applications.any { it.id == id }
        },
        linkedApplicationId,
        linkedFollowUpId?.let { id -> followUps.find { it.id == id }?.applicationId }
    ).firstOrNull()

    candidatureId?.let { id ->
        applications.find { it.id == id }?.companyId?.takeIf { it.isNotBlank() }?.let { return it }
    }

    // 3. Fallback direct
    return fallbackCompanyId?.takeIf { it.isNotBlank() }
}